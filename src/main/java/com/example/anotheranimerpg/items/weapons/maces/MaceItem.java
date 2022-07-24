package com.example.anotheranimerpg.items.weapons.maces;

import com.example.anotheranimerpg.init.ModTiers;
import com.example.anotheranimerpg.items.CustomWeapon;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MaceItem extends CustomWeapon {
    public MaceItem() {
        super(ModTiers.WEAPON, 10, 1.0f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1));
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return false;
    }
}
