package com.example.anotheranimerpg.client;

import com.example.anotheranimerpg.client.renderer.renderer.*;
import com.example.anotheranimerpg.init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientHandler {

    public static void onSetup()
    {
        EntityRenderers.register(ModEntities.MANTIS_SHRIMP.get(), MantisShrimpRenderer::new);
        EntityRenderers.register(ModEntities.SLIME.get(), SlimeRenderer::new);
        EntityRenderers.register(ModEntities.EARTH_SLIME.get(), EarthSlimeRenderer::new);
        EntityRenderers.register(ModEntities.FIRE_SLIME.get(), FireSlimeRenderer::new);
        EntityRenderers.register(ModEntities.WIND_SLIME.get(), WindSlimeRenderer::new);
        EntityRenderers.register(ModEntities.RAIN_SLIME.get(), RainSlimeRenderer::new);
        EntityRenderers.register(ModEntities.LIGHTNING_SLIME.get(), LightningSlimeRenderer::new);

    }
}
