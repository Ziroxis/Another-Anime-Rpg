package com.example.anotheranimerpg.effects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 11141120);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        if (entity.tickCount % 20 == 0)
        {
            entity.hurt(DamageSource.MAGIC, amplifier + 1);
            super.applyEffectTick(entity, amplifier);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
