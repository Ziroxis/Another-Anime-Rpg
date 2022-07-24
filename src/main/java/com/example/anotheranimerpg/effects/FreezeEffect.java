package com.example.anotheranimerpg.effects;

import com.example.anotheranimerpg.init.ModAttributes;
import com.google.common.collect.Maps;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;

import java.util.Map;
import java.util.UUID;

public class FreezeEffect extends MobEffect {

    public FreezeEffect()
    {
        super(MobEffectCategory.HARMFUL, 5636095);
        this    .addAttributeModifier(ForgeMod.SWIM_SPEED.get(), "323ffb58-0b57-434e-bdfc-354670e22d5f", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(ModAttributes.JUMP_HEIGHT.get(), "e8cd65cb-2768-4fd8-aa54-bdcda029aaff", -256, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(Attributes.MOVEMENT_SPEED, "9acf8b46-f341-11ec-b939-0242ac120002", (double) -0.5F, AttributeModifier.Operation.ADDITION);
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
    /*
    private final Map<Attribute, AttributeModifier> attributeModifiers = Maps.newHashMap();
    @Override
    public MobEffect addAttributeModifier(Attribute p_19473_, String p_19474_, double p_19475_, AttributeModifier.Operation p_19476_) {
        AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(p_19474_), this::getDescriptionId, p_19475_, p_19476_);
        this.attributeModifiers.put(p_19473_, attributemodifier);
        return this;
    }

     */
}
