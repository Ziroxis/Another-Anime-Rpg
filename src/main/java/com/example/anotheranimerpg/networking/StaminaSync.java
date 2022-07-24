package com.example.anotheranimerpg.networking;

import com.example.anotheranimerpg.data.entity.EntityStatsProvider;
import com.example.anotheranimerpg.data.entity.IEntityStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StaminaSync {
    private int stamina;

    public StaminaSync(int stamina)
    {
        this.stamina = stamina;
    }

    public static void encode(StaminaSync msg, FriendlyByteBuf buf)
    {
        buf.writeInt(msg.stamina);
    }

    public static StaminaSync decode(FriendlyByteBuf buf)
    {
        int data = buf.readInt();
        return new StaminaSync(data);
    }

    public static void handle(StaminaSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            IEntityStats playercap = mc.player.getCapability(EntityStatsProvider.ENTITY_STATS).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            playercap.setStamina((int) msg.stamina);
        });
        ctx.get().setPacketHandled(true);
    }
}
