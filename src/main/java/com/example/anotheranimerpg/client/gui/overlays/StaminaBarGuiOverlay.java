package com.example.anotheranimerpg.client.gui.overlays;

import com.example.anotheranimerpg.api.Beapi;
import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.data.entity.EntityStatsProvider;
import com.example.anotheranimerpg.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class StaminaBarGuiOverlay
{
    private static final ResourceLocation staminaBar = new ResourceLocation(Main.MODID + ":textures/gui/overlay/energy_bar.png");

    private static final int tex_width = 165;
    private static final int tex_height = 20;

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event)
    {
        Minecraft minecraft = Minecraft.getInstance();

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            AbstractClientPlayer player = Minecraft.getInstance().player;
            IEntityStats props = EntityStatsProvider.get(player);
            minecraft.getTextureManager().getTexture(staminaBar);

            //GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 160, 0, 0, 0,tex_width, tex_height, 0); // -> empty

            float staminaRatio = (float) (props.getStamina() / props.getMaxStamina());
            int set_width = (int) (tex_width * staminaRatio);
            //GuiUtils.drawTexturedModalRect(event.getMatrixStack(), 160, 60, 0, 0, set_width, tex_height, 0); // -> fill

            String stamina = "Stamina: " + props.getStamina();
            Beapi.drawStringWithBorder(Minecraft.getInstance().font, event.getMatrixStack(), stamina, 100, 10, Color.BLUE.getRGB());
            String maxStamina = "/" + props.getMaxStamina();
            Beapi.drawStringWithBorder(Minecraft.getInstance().font, event.getMatrixStack(), maxStamina, 160, 10, Color.BLUE.getRGB());

        }
    }
}
