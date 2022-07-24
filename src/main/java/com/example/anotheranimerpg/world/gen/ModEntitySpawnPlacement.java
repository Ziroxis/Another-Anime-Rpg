package com.example.anotheranimerpg.world.gen;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.entities.mobs.MantisShrimpEntity;
import com.example.anotheranimerpg.init.ModEntities;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import software.bernie.example.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawnPlacement {

    @SubscribeEvent
    public static void onSpawnPlacement(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.MANTIS_SHRIMP.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.WORLD_SURFACE, MantisShrimpEntity::canSpawn);
            SpawnPlacements.register(ModEntities.SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Mob::checkMobSpawnRules);
            SpawnPlacements.register(ModEntities.FIRE_SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Mob::checkMobSpawnRules);
            SpawnPlacements.register(ModEntities.EARTH_SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Mob::checkMobSpawnRules);
            SpawnPlacements.register(ModEntities.RAIN_SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Mob::checkMobSpawnRules);
            SpawnPlacements.register(ModEntities.LIGHTNING_SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Mob::checkMobSpawnRules);
            SpawnPlacements.register(ModEntities.WIND_SLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, Mob::checkMobSpawnRules);

        });
    }
}
