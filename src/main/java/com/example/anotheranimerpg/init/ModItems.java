package com.example.anotheranimerpg.init;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.items.drops.*;
import com.example.anotheranimerpg.items.ores.MigemItem;
import com.example.anotheranimerpg.items.weapons.bows.EarthedBow;
import com.example.anotheranimerpg.items.weapons.bows.ElectrifiedBow;
import com.example.anotheranimerpg.items.weapons.bows.FiredBow;
import com.example.anotheranimerpg.items.weapons.bows.IcedBow;
import com.example.anotheranimerpg.items.weapons.long_sword.BloodiedSlaughterer;
import com.example.anotheranimerpg.items.weapons.maces.MaceItem;
import com.example.anotheranimerpg.items.weapons.scythe.AbyssalScythe;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();

    // ORES
    public static final RegistryObject<Item> MIGEM = ITEMS.register("migem", MigemItem::new);



    // DROPS
    public static final RegistryObject<Item> RAIN_BALL = ITEMS.register("rain_ball", RainBall::new);
    public static final RegistryObject<Item> LIGHTNING_BALL = ITEMS.register("lightning_ball", LightningBall::new);
    public static final RegistryObject<Item> EARTH_BALL = ITEMS.register("earth_ball", EarthBall::new);
    public static final RegistryObject<Item> WIND_BALL = ITEMS.register("wind_ball", WindBall::new);
    public static final RegistryObject<Item> FIRE_BALL = ITEMS.register("fire_ball", FireBall::new);

    //WEAPONS
    public static final RegistryObject<Item> ABYSSAL_SCYTHE = ITEMS.register("abyssal_scythe", AbyssalScythe::new);
    public static final RegistryObject<Item> BLOODIED_SLAUGHTERER = ITEMS.register("bloodied_slaughterer", BloodiedSlaughterer::new);
    public static final RegistryObject<Item> ICED_BOW = ITEMS.register("iced_bow", IcedBow::new);
    public static final RegistryObject<Item> ELECTRIFIED_BOW = ITEMS.register("electrified_bow", ElectrifiedBow::new);
    public static final RegistryObject<Item> EARTHED_BOW = ITEMS.register("earthed_bow", EarthedBow::new);
    public static final RegistryObject<Item> FIRED_BOW = ITEMS.register("fired_bow", FiredBow::new);
    public static final RegistryObject<Item> MACE = ITEMS.register("mace", MaceItem::new);

    //SPAWN EGGS
    public static final RegistryObject<Item> MANTIS_SHRIMP_EGG = ITEMS.register("mantis_shrimp_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MANTIS_SHRIMP, 0x948e8d, 0x3b3635,
            new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
