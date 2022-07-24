package com.example.anotheranimerpg.items;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class CustomArrow extends Arrow {




    private IntOpenHashSet piercingIgnoreEntityIds;
    @Nullable
    private List<Entity> piercedAndKilledEntities;
    private int knockback;
    private final SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    boolean hasEffect = false;
    MobEffect effects;
    int amplifier;
    int durationEffect;
    boolean onFire;
    int durationFire;

    public CustomArrow(Level p_36866_, LivingEntity p_36867_, boolean hasEffect, boolean onFire, int durationFire)
    {
        super(p_36866_, p_36867_);
        this.hasEffect = hasEffect;
        this.onFire = onFire;
        this.durationFire = durationFire;
    }

    public CustomArrow(Level p_36866_, LivingEntity p_36867_, MobEffect effects, int durationEffect, int amplifier)
    {
        super(p_36866_, p_36867_);
        this.hasEffect = true;
        this.effects = effects;
        this.amplifier = amplifier;
        this.durationEffect = durationEffect;
    }

    public CustomArrow(Level p_36866_, LivingEntity p_36867_, MobEffect effects, int durationEffect, int amplifier, boolean onFire, int durationFire) {
        super(p_36866_, p_36867_);
        this.hasEffect = true;
        this.effects = effects;
        this.amplifier = amplifier;
        this.durationEffect = durationEffect;
        this.onFire = onFire;
        this.durationFire = durationFire;
    }


    @Override
    protected void onHitEntity(@NotNull EntityHitResult hit) {
        super.onHitEntity(hit);
        Entity entity = hit.getEntity();
        float f = (float)this.getDeltaMovement().length();
        double baseDamage = 2.0D;
        int i = Mth.ceil(Mth.clamp((double)f * baseDamage, 0.0D, 2.147483647E9D));

        if (this.getPierceLevel() > 0) {
            if (this.piercingIgnoreEntityIds == null) {
                this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
            }

            if (this.piercedAndKilledEntities == null) {
                this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
            }

            if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
                this.discard();
                return;
            }

            this.piercingIgnoreEntityIds.add(entity.getId());
        }

        if (this.isCritArrow()) {
            if (entity instanceof LivingEntity critEntity) 
            {
                if (onFire)
                    critEntity.setSecondsOnFire(durationFire);
                
                if (!(critEntity.hasEffect(effects)) & hasEffect) {
                    critEntity.addEffect(new MobEffectInstance(effects, durationEffect, amplifier));
                }
            }
            long j = (long)this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(j + (long)i, 2147483647L);
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DamageSource.arrow(this, this);
        } else {
            damagesource = DamageSource.arrow(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).setLastHurtMob(entity);
            }
        }

        boolean flag = entity.getType() == EntityType.ENDERMAN;
        int k = entity.getRemainingFireTicks();
        if (this.isOnFire() && !flag) {
            entity.setSecondsOnFire(5);
        }

        if (entity.hurt(damagesource, (float)i)) {
            if (flag) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                
                if (!this.level.isClientSide && this.getPierceLevel() <= 0) {
                    livingentity.setArrowCount(livingentity.getArrowCount() + 1);
                }

                if (this.knockback > 0) {
                    Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
                    if (vec3.lengthSqr() > 0.0D) {
                        livingentity.push(vec3.x, 0.1D, vec3.z);
                    }
                }

                if (!this.level.isClientSide && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
                }

                this.doPostHurtEffects(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
                    ((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }

                if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
                    this.piercedAndKilledEntities.add(livingentity);
                }

                if (!this.level.isClientSide && entity1 instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)entity1;
                    if (this.piercedAndKilledEntities != null && this.shotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, this.piercedAndKilledEntities);
                    } else if (!entity.isAlive() && this.shotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, Arrays.asList(entity));
                    }
                }
            }

            this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.discard();
            }
        } else {
            entity.setRemainingFireTicks(k);
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.setYRot(this.getYRot() + 180.0F);
            this.yRotO += 180.0F;
            if (!this.level.isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                if (this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            }
        }

    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

}
