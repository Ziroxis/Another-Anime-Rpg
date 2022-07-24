package com.example.anotheranimerpg.client.renderer.models;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.entities.mobs.MantisShrimpEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class MantisShrimpModel extends AnimatedGeoModel<MantisShrimpEntity> {
    @Override
    public ResourceLocation getModelLocation(MantisShrimpEntity object) {
        return new ResourceLocation(Main.MODID, "geo/mantis_shrimp.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MantisShrimpEntity object) {
        return new ResourceLocation(Main.MODID, "textures/entity/mantis_shrimp/mantis_shrimp.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MantisShrimpEntity animatable) {
        return new ResourceLocation(Main.MODID, "animations/mantis_shrimp.animation.json");
    }
}
