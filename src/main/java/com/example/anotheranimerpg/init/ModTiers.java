package com.example.anotheranimerpg.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModTiers implements Tier {


    WEAPON(1000, 8, 0, 1, 20, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
    SWORDMAGIC(0, 8, 0, 1, 20, () -> {
        return Ingredient.of(ItemStack.EMPTY);
    }),
    MAGICWEAPON(0, 8, 0, 1, 20, () -> {
        return Ingredient.of(ItemStack.EMPTY);
    });


    private int maxUses;
    private float efficiency;
    private float attackDamage;
    private int harvestLevel;
    private int enchantability;

    private ModTiers(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> ingredient) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }


}
