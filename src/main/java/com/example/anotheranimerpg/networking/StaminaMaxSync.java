package com.example.anotheranimerpg.networking;

import com.example.anotheranimerpg.data.entity.EntityStatsProvider;
import com.example.anotheranimerpg.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StaminaMaxSync {
    private int staminaMax;

    public StaminaMaxSync(int staminaMax)
    {
        this.staminaMax = staminaMax;
    }

    public static void encode(StaminaMaxSync msg, FriendlyByteBuf buf)
    {
        buf.writeInt(msg.staminaMax);
    }

    public static StaminaMaxSync decode(FriendlyByteBuf buf)
    {
        int data = buf.readInt();
        return new StaminaMaxSync(data);
    }

    public static void handle(StaminaMaxSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            IEntityStats playercap = mc.player.getCapability(EntityStatsProvider.ENTITY_STATS).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            playercap.setMaxStamina((int) msg.staminaMax);
        });
        ctx.get().setPacketHandled(true);
    }
}
