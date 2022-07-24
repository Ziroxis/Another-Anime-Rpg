package com.example.anotheranimerpg.entities.mobs.slimes;

import com.example.anotheranimerpg.init.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class RainSlimeEntity extends SlimeEntity{
    public RainSlimeEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    public boolean doHurtTarget(Entity target) {
        if (!super.doHurtTarget(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
                ((LivingEntity)target).addEffect(new MobEffectInstance(ModEffects.FREEZE.get(), 800), this);
            }

            return true;
        }
    }
}
