package com.example.anotheranimerpg.client.renderer.models;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.entities.mobs.slimes.SlimeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SlimeModel<T extends SlimeEntity> extends EntityModel<T> {

    private final ModelPart AAR_Slime;
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Main.MODID, "slime"), "main");
    public SlimeModel(ModelPart root) {
        this.AAR_Slime = root.getChild("AAR_Slime");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition AAR_Slime = partdefinition.addOrReplaceChild("AAR_Slime", CubeListBuilder.create().texOffs(30, 32).addBox(-9.0F, -15.0F, -7.0F, 1.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(8.0F, -15.0F, -7.0F, 1.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(58, 30).addBox(-8.0F, -15.0F, 7.0F, 16.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 0).addBox(-8.0F, -15.0F, -9.0F, 16.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(-8.0F, -16.0F, -7.0F, 16.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-3.0F, -18.0F, -2.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        AAR_Slime.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
