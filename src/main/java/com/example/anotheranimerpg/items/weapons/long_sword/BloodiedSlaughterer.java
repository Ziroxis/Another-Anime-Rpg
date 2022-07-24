package com.example.anotheranimerpg.items.weapons.long_sword;

import com.example.anotheranimerpg.init.ModEffects;
import com.example.anotheranimerpg.init.ModTiers;
import com.example.anotheranimerpg.items.CustomWeapon;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BloodiedSlaughterer extends CustomWeapon {

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TextComponent("§4Once Beholden by a gladiator in an unknown place and time, is now used to clench the thirst of it's user"));
        } else {
            pTooltipComponents.add(new TextComponent("press §eSHIFT§r for more information"));
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
    {
        if (attacker instanceof Player)
        {
            if (!(target.hasEffect(ModEffects.BLEEDING.get())))
                target.addEffect(new MobEffectInstance(ModEffects.BLEEDING.get(), 60, 0));
            else
            {
                int amplifier = target.getEffect(ModEffects.BLEEDING.get()).getAmplifier();
                target.removeEffect(ModEffects.BLEEDING.get());
                target.addEffect(new MobEffectInstance(ModEffects.BLEEDING.get(), 80, amplifier + 1));
            }
        }
        return super.hurtEnemy(itemStack, target, attacker);
    }

    public BloodiedSlaughterer()
    {
        super(ModTiers.WEAPON, 7, 0.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1));
    }
}
