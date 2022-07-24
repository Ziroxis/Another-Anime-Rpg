package com.example.anotheranimerpg.entities.mobs.slimes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class FireSlimeEntity extends SlimeEntity{
    public FireSlimeEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    //Wither skeleton code
    public boolean doHurtTarget(Entity target) {
        if (!super.doHurtTarget(target)) {
            return false;
        } else {
            if (target instanceof LivingEntity) {
                //((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.WITHER, 200), this);
                target.setSecondsOnFire(5);
            }

            return true;
        }
    }
}
