package com.example.anotheranimerpg.events;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.client.renderer.models.SlimeModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    private ClientModEvents() {}

    @SubscribeEvent
    public static void clientSetup(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(SlimeModel.LAYER_LOCATION, SlimeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        //event.registerEntityRenderer(ModEntities.SLIME.get(), SlimeRenderer::new);
        //event.registerEntityRenderer(ModEntities.EARTH_SLIME.get(), EarthSlimeRenderer::new);
    }
}
