package com.example.anotheranimerpg.entities.mobs;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class MantisShrimpEntity extends Animal implements IAnimatable {
    public AnimationFactory factory = new AnimationFactory(this);
    public MantisShrimpEntity(EntityType<? extends Animal> entityType, Level level)
    {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);

    }


    public static AttributeSupplier setAttributes()
    {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.ATTACK_KNOCKBACK, 5).build();
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(7, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(7, new MeleeAttackGoal(this, (double)4F, false));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 5D));
        this.goalSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, true));

    }

    public static boolean canSpawn(EntityType<MantisShrimpEntity> entity, LevelAccessor levelAccess, MobSpawnType spawnType,
                                   BlockPos pos, Random random) {
        return levelAccess.getBlockState(pos.below()).is(BlockTags.SAND);
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mantis_shrimp.Walking1", true));
            return PlayState.CONTINUE;
        }


        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mantis_shrimp.Idle1", false));
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate1(AnimationEvent<E> event) {
        //if (this.entityData.get(STATE) > 0)
        if (this.swinging)
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mantis_shrimp.Attack1", false));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }



    /*
    public static boolean canSpawn(EntityType<MantisShrimpEntity> entityType, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random)
    {
        return checkAnimalSpawnRules(entityType, levelAccessor, spawnType, pos, random) && pos.getY() > 70;
    }

     */

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_)
    {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<MantisShrimpEntity>(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController<MantisShrimpEntity>(this, "controller1", 0, this::predicate1));
    }



    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    //TODO add sounds
    protected void playStepSound(BlockPos pos, BlockState state)
    {
        this.playSound(SoundEvents.POWDER_SNOW_FALL, 0.15f, 1.0F);
    }

}
