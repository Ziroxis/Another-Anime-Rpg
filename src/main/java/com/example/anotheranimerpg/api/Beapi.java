package com.example.anotheranimerpg.api;


import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.font.FontTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.loading.VersionSupportMatrix;


import javax.annotation.Nullable;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Beapi
{

    public static void drawStringWithBorder(Font font, PoseStack matrixStack, String text, int posX, int posY, int color)
    {
        matrixStack.pushPose();
        String unformattedText = escapeTextFormattingChars(text);
        font.drawShadow(matrixStack, unformattedText, posX, posY - 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX, posY + 0.7f, 0);
        font.drawShadow(matrixStack, unformattedText, posX + 0.7f, posY, 0);
        font.drawShadow(matrixStack, unformattedText, posX - 0.7f, posY, 0);
        matrixStack.translate(0, 0, 1);
        font.draw(matrixStack, unformattedText, posX, posY, -1);
        matrixStack.popPose();
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
    }
    public static String escapeTextFormattingChars(String text)
    {
        return text.replaceAll("§[0-9a-f]", "");
    }
}
