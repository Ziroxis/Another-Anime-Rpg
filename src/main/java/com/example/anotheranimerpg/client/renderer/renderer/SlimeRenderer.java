package com.example.anotheranimerpg.client.renderer.renderer;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.client.renderer.models.SlimeModel;
import com.example.anotheranimerpg.entities.mobs.slimes.SlimeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeRenderer extends MobRenderer<SlimeEntity, SlimeModel<SlimeEntity>> {
    public SlimeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SlimeModel<>(renderManager.bakeLayer(SlimeModel.LAYER_LOCATION)), 1F);
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(SlimeEntity p_114482_) {
        return new ResourceLocation(Main.MODID, "textures/entity/slimes/slime.png");
    }
}
