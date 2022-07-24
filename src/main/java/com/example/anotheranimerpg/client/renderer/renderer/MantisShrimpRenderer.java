package com.example.anotheranimerpg.client.renderer.renderer;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.client.renderer.models.MantisShrimpModel;
import com.example.anotheranimerpg.entities.mobs.MantisShrimpEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class MantisShrimpRenderer extends GeoEntityRenderer<MantisShrimpEntity> {
    public MantisShrimpRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MantisShrimpModel());
        this.shadowRadius = 0.3f;
    }


    @Override
    public ResourceLocation getTextureLocation(MantisShrimpEntity instance) {
        return new ResourceLocation(Main.MODID, "textures/entity/mantis_shrimp/mantis_shrimp.png");
    }

    @Override
    public RenderType getRenderType(MantisShrimpEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation)
    {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
