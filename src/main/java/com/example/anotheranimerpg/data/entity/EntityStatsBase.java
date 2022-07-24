package com.example.anotheranimerpg.data.entity;

import net.minecraft.nbt.*;


public class EntityStatsBase implements IEntityStats{

    private int stamina;
    private int maxStamina;

    @Override
    public boolean hasStamina() {
        return false;
    }

    @Override
    public int getStamina() {
        return this.stamina;
    }

    @Override
    public void alterStamina(int value) {
        this.stamina = this.stamina + value;
    }

    @Override
    public void setStamina(int value) {
        this.stamina = value;
    }

    @Override
    public int getMaxStamina() {
        return this.maxStamina;
    }

    @Override
    public void alterMaxStamina(int value) {
        this.maxStamina = this.maxStamina + value;
    }

    @Override
    public void setMaxStamina(int value) {
        this.maxStamina = value;
    }

    public void copyFrom(EntityStatsBase source)
    {
        stamina = source.stamina;
        maxStamina = source.maxStamina;
    }

    public void saveNBTData(CompoundTag compoundTag)
    {
        compoundTag.putInt("stamina", stamina);
        compoundTag.putInt("maxStamina", maxStamina);
    }
    public void loadNBTData(CompoundTag compoundTag)
    {
        stamina = compoundTag.getInt("stamina");
        maxStamina = compoundTag.getInt("maxStamina");
    }

}
