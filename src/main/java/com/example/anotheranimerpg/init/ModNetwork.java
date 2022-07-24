package com.example.anotheranimerpg.init;

import com.example.anotheranimerpg.networking.PacketHandler;
import com.example.anotheranimerpg.networking.StaminaMaxSync;
import com.example.anotheranimerpg.networking.StaminaSync;

public class ModNetwork {

    public static void init()
    {
        PacketHandler.registerPacket(StaminaSync.class, StaminaSync::encode, StaminaSync::decode, StaminaSync::handle);
        PacketHandler.registerPacket(StaminaMaxSync.class, StaminaMaxSync::encode, StaminaMaxSync::decode, StaminaMaxSync::handle);
    }
}
