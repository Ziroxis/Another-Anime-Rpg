package com.example.anotheranimerpg.world.gen;

import com.example.anotheranimerpg.Main;
import com.example.anotheranimerpg.init.ModEntities;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ModEntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event) {
        event.getSpawns().getSpawner(MobCategory.MONSTER).clear();
        //event.getSpawns().getSpawner(MobCategory.CREATURE).clear();

        //SLIMES
        addEntityToAllOverworldBiomes(event, ModEntities.SLIME.get(), 70, 4, 6);
        addEntityToSpecificBiomes(event, ModEntities.RAIN_SLIME.get(), 50, 2, 4, Biomes.SWAMP);
        addEntityToSpecificBiomes(event, ModEntities.LIGHTNING_SLIME.get(), 50, 2, 4, Biomes.STONY_PEAKS);
        addEntityToSpecificBiomes(event, ModEntities.FIRE_SLIME.get(), 50, 2, 4, Biomes.JUNGLE);
        addEntityToSpecificBiomes(event, ModEntities.WIND_SLIME.get(), 50, 2, 4, Biomes.DESERT);
        addEntityToSpecificBiomes(event, ModEntities.EARTH_SLIME.get(), 50, 2, 4, Biomes.BADLANDS);

        //ANIMALS
        addEntityToSpecificBiomes(event, ModEntities.MANTIS_SHRIMP.get(), 100, 2, 4, Biomes.BEACH);
    }

    private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
                                                        int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(!isBiomeSelected) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
        }
    }

    @SafeVarargs
    private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(isBiomeSelected) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllOverworldBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                      int weight, int minCount, int maxCount) {
        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllBiomesNoNether(BiomeLoadingEvent event, EntityType<?> type,
                                                     int weight, int minCount, int maxCount) {
        if(!event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
            base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
        }
    }

    private static void addEntityToAllBiomesNoEnd(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount) {
        if(!event.getCategory().equals(Biome.BiomeCategory.THEEND)) {
            List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
            base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
        }
    }

    private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                             int weight, int minCount, int maxCount) {
        List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
        base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
    }





        /*
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(event.getName()));
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if (types.contains(BiomeDictionary.Type.BEACH))
        {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MANTIS_SHRIMP.get(), 100, 4, 6)).build();

        }
        if (types.contains(BiomeDictionary.Type.MOUNTAIN))
        {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.LIGHTNING_SLIME.get(), 100, 4, 6)).build();
        }
        if (types.contains(BiomeDictionary.Type.MESA))
        {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.FIRE_SLIME.get(), 100, 4, 6)).build();
        }
        if (types.contains(BiomeDictionary.Type.PLAINS))
        {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.SLIME.get(), 100, 4, 6)).build();
        }
        if (types.contains(BiomeDictionary.Type.SWAMP))
        {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.RAIN_SLIME.get(), 100, 4, 6)).build();
        }
        if (types.contains(BiomeDictionary.Type.SNOWY))
        {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.WIND_SLIME.get(), 100, 4, 6)).build();
        }
        if (types.contains(BiomeDictionary.Type.HILLS))
        {
            event.getSpawns().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.EARTH_SLIME.get(), 100, 4, 6)).build();

        }

         */
}

