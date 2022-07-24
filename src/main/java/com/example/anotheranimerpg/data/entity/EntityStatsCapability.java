package com.example.anotheranimerpg.data.entity;

import com.example.anotheranimerpg.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityStatsCapability {


    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof Player)
        {
            if (!event.getObject().getCapability(EntityStatsProvider.ENTITY_STATS).isPresent())
            {
                event.addCapability(new ResourceLocation(Main.MODID, "properties"), new EntityStatsProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {
        if (event.isWasDeath())
        {
            event.getOriginal().getCapability(EntityStatsProvider.ENTITY_STATS).ifPresent(oldStore ->
            {
                event.getPlayer().getCapability(EntityStatsProvider.ENTITY_STATS).ifPresent(newStore ->
                {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(EntityStatsBase.class);
    }

}
