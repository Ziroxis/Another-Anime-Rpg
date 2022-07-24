package com.example.anotheranimerpg.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public abstract class CustomWeapon extends SwordItem {
    public CustomWeapon(Tier itemTier, int damage, float attackSpeed, Properties properties) {
        super(itemTier, damage, attackSpeed, properties);
    }

    @Override
    public int getEnchantmentValue()
    {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

}
