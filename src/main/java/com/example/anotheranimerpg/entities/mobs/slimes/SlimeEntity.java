package com.example.anotheranimerpg.entities.mobs.slimes;

import com.example.anotheranimerpg.entities.mobs.MantisShrimpEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.Random;

public class SlimeEntity extends Monster {


    public SlimeEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.1D));
        this.goalSelector.addGoal(7, new MeleeAttackGoal(this, (double)4F, false));
        this.goalSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier setAttributes()
    {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 1).build();
    }

    public static boolean canSlimeSpawn(EntityType<MantisShrimpEntity> entity, LevelAccessor levelAccess, MobSpawnType spawnType,
                                        BlockPos pos, Random random) {
        //return levelAccess.getBlockState(pos.below()).is(BlockTags.SAND);
        return true;
    }
}
