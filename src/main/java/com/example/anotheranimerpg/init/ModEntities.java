package com.example.anotheranimerpg.init;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.entities.mobs.MantisShrimpEntity;
import com.example.anotheranimerpg.entities.mobs.slimes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);

    public static final RegistryObject<EntityType<MantisShrimpEntity>> MANTIS_SHRIMP =
            ENTITY_TYPES.register("mantis_shrimp",
                    () -> EntityType.Builder.of(MantisShrimpEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(Main.MODID, "mantis_shrimp").toString()));
    public static final RegistryObject<EntityType<SlimeEntity>> SLIME =
            ENTITY_TYPES.register("slime",
                    () -> EntityType.Builder.of(SlimeEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(Main.MODID, "slime").toString()));
    public static final RegistryObject<EntityType<EarthSlimeEntity>> EARTH_SLIME =
            ENTITY_TYPES.register("earth_slime",
                    () -> EntityType.Builder.of(EarthSlimeEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(Main.MODID, "earth_slime").toString()));
    public static final RegistryObject<EntityType<RainSlimeEntity>> RAIN_SLIME =
            ENTITY_TYPES.register("rain_slime",
                    () -> EntityType.Builder.of(RainSlimeEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(Main.MODID, "rain_slime").toString()));
    public static final RegistryObject<EntityType<LightningSlimeEntity>> LIGHTNING_SLIME =
            ENTITY_TYPES.register("lightning_slime",
                    () -> EntityType.Builder.of(LightningSlimeEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(Main.MODID, "lightning_slime").toString()));
    public static final RegistryObject<EntityType<FireSlimeEntity>> FIRE_SLIME =
            ENTITY_TYPES.register("fire_slime",
                    () -> EntityType.Builder.of(FireSlimeEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(Main.MODID, "fire_slime").toString()));
    public static final RegistryObject<EntityType<WindSlimeEntity>> WIND_SLIME =
            ENTITY_TYPES.register("wind_slime",
                    () -> EntityType.Builder.of(WindSlimeEntity::new, MobCategory.CREATURE)
                            .sized(1f, 1f)
                            .build(new ResourceLocation(Main.MODID, "wind_slime").toString()));



    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
