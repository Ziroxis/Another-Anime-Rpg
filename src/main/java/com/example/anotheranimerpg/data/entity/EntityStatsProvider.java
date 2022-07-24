package com.example.anotheranimerpg.data.entity;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityStatsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<EntityStatsBase> ENTITY_STATS = CapabilityManager.get(new CapabilityToken<>() {});
    private EntityStatsBase entityStats = null;
    private final LazyOptional<EntityStatsBase> optional = LazyOptional.of(this::createEntityStats);



    @Nonnull
    private EntityStatsBase createEntityStats()
    {
        if (entityStats == null)
        {
            entityStats = new EntityStatsBase();
        }
        return entityStats;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability)
    {
        if (capability == ENTITY_STATS)
        {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side)
    {
        return getCapability(capability);
    }

    @Override
    public CompoundTag serializeNBT()
    {
        CompoundTag nbt = new CompoundTag();
        createEntityStats().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt)
    {
        createEntityStats().loadNBTData(nbt);
    }

    public static IEntityStats get(final LivingEntity entity)
    {
        return entity.getCapability(ENTITY_STATS, null).orElse(new EntityStatsBase());
    }
}
