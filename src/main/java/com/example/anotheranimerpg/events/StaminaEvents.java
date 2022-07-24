package com.example.anotheranimerpg.events;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.data.entity.EntityStatsCapability;
import com.example.anotheranimerpg.data.entity.EntityStatsProvider;
import com.example.anotheranimerpg.data.entity.IEntityStats;
import com.example.anotheranimerpg.networking.PacketHandler;
import com.example.anotheranimerpg.networking.StaminaMaxSync;
import com.example.anotheranimerpg.networking.StaminaSync;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraft.client.gui.screens.controls.KeyBindsScreen;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.*;
import javax.swing.text.JTextComponent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class StaminaEvents {

    @SubscribeEvent
    public static void spawningWorldEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (!(event.getEntity() instanceof Player))
            return;
        if (event.getEntity().level.isClientSide)
            return;
        Player player = (Player) event.getPlayer();
        IEntityStats statsProps = EntityStatsProvider.get(player);
        statsProps.setStamina(100);
        statsProps.setMaxStamina(100);
        PacketHandler.sendTo(new StaminaSync(statsProps.getStamina()),  player);
        PacketHandler.sendTo(new StaminaMaxSync(statsProps.getMaxStamina()), player);
    }

    @SubscribeEvent
    public static void runningEvent(LivingEvent.LivingUpdateEvent event)
    {

        // If it's not a player or if it's not running, return
        if (event.getEntity().level.isClientSide)
            return;
        if (!(event.getEntity() instanceof Player))
            return;
        if (!(event.getEntity().isSprinting()))
            return;

        Player player = (Player) event.getEntity();
        IEntityStats statsProps = EntityStatsProvider.get(player);
        if (statsProps.getStamina() <= 0)
        {
            KeyMapping.set(Minecraft.getInstance().options.keySprint.getKey(), false);
            player.setSpeed(0.01f);
            player.setSprinting(false);
        }
        else
        {
            statsProps.alterStamina(-1);
            PacketHandler.sendTo(new StaminaSync(statsProps.getStamina()),  player);
        }
    }

    @SubscribeEvent
    public static void idleEvent(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntity().level.isClientSide)
            return;
        if (!(event.getEntity() instanceof Player))
            return;
        if (event.getEntity().isSprinting() || event.getEntity().isSwimming())
            return;

        Player player = (Player) event.getEntity();
        IEntityStats propsEntity = EntityStatsProvider.get(player);

        if (player.tickCount % 20 == 0 && propsEntity.getStamina() < propsEntity.getMaxStamina())
            propsEntity.alterStamina(1);
        PacketHandler.sendTo(new StaminaSync(propsEntity.getStamina()),  player);

    }
}
