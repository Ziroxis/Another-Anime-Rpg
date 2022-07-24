package com.example.anotheranimerpg;

import com.example.anotheranimerpg.client.ClientHandler;
import com.example.anotheranimerpg.client.gui.overlays.StaminaBarGuiOverlay;
import com.example.anotheranimerpg.client.renderer.renderer.MantisShrimpRenderer;
import com.example.anotheranimerpg.data.entity.EntityStatsCapability;
import com.example.anotheranimerpg.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(Main.MODID)
public class Main
{
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "anotheranimerpg";


    public Main()
    {
        IEventBus modEventBus = MinecraftForge.EVENT_BUS;
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addGenericListener(Entity.class, EntityStatsCapability::onAttachCapabilitiesPlayer);

        ModAttributes.ATTRIBUTES.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModEffects.register(eventBus);
        ModEntities.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        modEventBus.addListener(this::registerCapabilities);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }


    public void registerCapabilities(RegisterCapabilitiesEvent event)
    {
        EntityStatsCapability.onRegisterCapabilities(event);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ModNetwork.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new StaminaBarGuiOverlay());
        ModItemProperties.addCustomItemProperties();
        ClientHandler.onSetup();
    }


    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {

    }
}
