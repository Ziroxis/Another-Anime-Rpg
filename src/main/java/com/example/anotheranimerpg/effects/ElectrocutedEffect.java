package com.example.anotheranimerpg.effects;

import com.example.anotheranimerpg.init.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

public class ElectrocutedEffect extends MobEffect {

    public ElectrocutedEffect()
    {
        super(MobEffectCategory.HARMFUL, 16777045);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "2727e176-e9e8-4523-92f8-22619308d9ee", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "323ffb58-0b57-434e-bdfc-354670e22d5f", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, "f8b2474d-4cdb-42b0-a868-327443a2a505", 100, AttributeModifier.Operation.ADDITION);

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
