package com.example.anotheranimerpg.init;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.effects.BleedingEffect;
import com.example.anotheranimerpg.effects.EarthedEffect;
import com.example.anotheranimerpg.effects.ElectrocutedEffect;
import com.example.anotheranimerpg.effects.FreezeEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MODID);

    public static final RegistryObject<MobEffect> EARTHED = MOB_EFFECTS.register("earthed", EarthedEffect::new);
    public static final RegistryObject<MobEffect> BLEEDING = MOB_EFFECTS.register("bleeding", BleedingEffect::new);
    public static final RegistryObject<MobEffect> ELECTROCUTE = MOB_EFFECTS.register("electrocute", ElectrocutedEffect::new);
    public static final RegistryObject<MobEffect> FREEZE = MOB_EFFECTS.register("freeze", FreezeEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
