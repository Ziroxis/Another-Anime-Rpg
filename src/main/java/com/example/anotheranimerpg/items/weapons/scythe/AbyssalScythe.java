package com.example.anotheranimerpg.items.weapons.scythe;

import com.example.anotheranimerpg.init.ModTiers;
import com.example.anotheranimerpg.items.CustomWeapon;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffect;
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

public class AbyssalScythe extends CustomWeapon {

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TextComponent("§5An old scythe used by an old man farming, somewhere where he shouldn't have been..."));
        } else {
            pTooltipComponents.add(new TextComponent("press §eSHIFT§r for more information"));
        }
    }

    //Pushes enemies towards you and inflicts blindness
    //https://github.com/kwpugh/gobber/blob/1.18.2/src/main/java/com/kwpugh/gobber2/items/rings/ItemCustomRingAttraction.java#L86
    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker)
    {
        if (attacker instanceof Player)
        {
            if (!(target.hasEffect(MobEffects.BLINDNESS)))
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80, 0));

            target.push((attacker.getX() - target.getX()) * 0.3, (attacker.getY() - target.getY() + 1) * 0.05, (attacker.getZ() - target.getZ()) * 0.3);
        }
        return super.hurtEnemy(itemStack, target, attacker);
    }

    public AbyssalScythe()
    {
        super(ModTiers.WEAPON, 7, 0.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1));
    }
}
