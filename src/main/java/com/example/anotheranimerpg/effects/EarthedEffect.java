package com.example.anotheranimerpg.effects;

import com.example.anotheranimerpg.init.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

public class EarthedEffect extends MobEffect {
    public EarthedEffect()
    {
        super(MobEffectCategory.HARMFUL, 16777045);
        this.addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256, AttributeModifier.Operation.ADDITION);

    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        if (entity.tickCount % 20 == 0)
        {
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
