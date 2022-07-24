package com.example.anotheranimerpg.world.gen;

import com.example.anotheranimerpg.Main;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event)
    {

        ModEntityGeneration.onEntitySpawn(event);
    }
}
