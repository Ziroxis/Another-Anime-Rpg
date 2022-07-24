package com.example.anotheranimerpg.data.entity;

import net.minecraft.nbt.Tag;

public interface IEntityStats {

    boolean hasStamina();
    int getStamina();
    void alterStamina(int value);
    void setStamina(int value);

    int getMaxStamina();
    void alterMaxStamina(int value);
    void setMaxStamina(int value);
}
