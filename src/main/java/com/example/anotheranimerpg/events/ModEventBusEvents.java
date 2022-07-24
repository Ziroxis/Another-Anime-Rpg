package com.example.anotheranimerpg.events;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.entities.mobs.MantisShrimpEntity;
import com.example.anotheranimerpg.entities.mobs.slimes.SlimeEntity;
import com.example.anotheranimerpg.init.ModEntities;
import net.minecraft.world.entity.monster.Slime;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.MANTIS_SHRIMP.get(), MantisShrimpEntity.setAttributes());
        event.put(ModEntities.SLIME.get(), SlimeEntity.setAttributes());
        event.put(ModEntities.EARTH_SLIME.get(), SlimeEntity.setAttributes());
        event.put(ModEntities.FIRE_SLIME.get(), SlimeEntity.setAttributes());
        event.put(ModEntities.WIND_SLIME.get(), SlimeEntity.setAttributes());
        event.put(ModEntities.RAIN_SLIME.get(), SlimeEntity.setAttributes());
        event.put(ModEntities.LIGHTNING_SLIME.get(), SlimeEntity.setAttributes());

    }
}
