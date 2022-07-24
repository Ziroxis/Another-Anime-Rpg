package com.example.anotheranimerpg.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketHandler {

    private static int packet = 0;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("anotheranimerpg", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static <MSG> void registerPacket(Class<MSG> messageType, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer)
    {
        INSTANCE.registerMessage(packet++, messageType, encoder, decoder, messageConsumer);
    }

    public static <MSG> void sendToServer(MSG msg)
    {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendTo(MSG msg, Player player)
    {
        if(!(player instanceof ServerPlayer))
            return;

        INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), msg);
    }

    public static <MSG> void sendToAll(MSG msg)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

    public static <MSG> void sendToAllTrackingAndSelf(MSG msg, LivingEntity tracked)
    {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> tracked), msg);
    }

    public static <MSG> void sendToAllAround(MSG msg, LivingEntity sender)
    {
        try
        {
            INSTANCE.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(sender.getX(), sender.getY(), sender.getZ(), 250, sender.level.dimension())), msg);
            if (sender instanceof ServerPlayer)
                sendTo(msg, (ServerPlayer) sender);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
}
