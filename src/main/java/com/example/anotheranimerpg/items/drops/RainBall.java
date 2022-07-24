package com.example.anotheranimerpg.items.drops;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class RainBall extends Item {

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(new TextComponent("§bSimple rainy ball of slime"));
        } else {
            pTooltipComponents.add(new TextComponent("press §eSHIFT§r for more information"));
        }
    }

    public RainBall() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }
}
