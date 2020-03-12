package io.github.vampirestudios.raa.utils;

import io.github.vampirestudios.raa.RandomlyAddingAnything;
import io.github.vampirestudios.raa.api.dimension.DimensionChunkGenerators;
import io.github.vampirestudios.raa.api.dimension.DimensionSurfaceBuilders;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Utils {
    //dimension bit flags
    public static final int CORRUPTED = 1; //nether corruption, same as the old corruption feature
    public static final int DEAD = 2; //No plants or passive animals at all, very harsh
    public static final int ABANDONED = 4; //only ruins of old civilizations, no living "smart" creatures (like villagers)
    public static final int LUSH = 8; //A lush overgrowth of plants
    public static final int CIVILIZED = 16; //Villages/towns of "smart" creatures who will trade with you
    public static final int MOLTEN = 32; //Instead of water oceans, there are lava oceans.
    public static final int DRY = 64; //No oceans exist at all.
    public static final int TECTONIC = 128; //Creates lots of caves and ravines. Usually not visible on the surface.
    public static final int FROZEN = 256; //Makes the dimension frozen

    public static final int POST_APOCALYPTIC = CORRUPTED | DEAD | ABANDONED | DRY | TECTONIC; //A combination of corrupted, dead, abandoned, dry, and tectonic

    public static String toTitleCase(String lowerCase) {
        return "" + Character.toUpperCase(lowerCase.charAt(0)) + lowerCase.substring(1);
    }

    public static String nameToId(String name, Map<String, String> specialCharMap) {
        // strip name of special chars
        for (Map.Entry<String, String> specialChar : specialCharMap.entrySet()) {
            name = name.replace(specialChar.getKey(), specialChar.getValue());
        }
        return name.toLowerCase(Locale.ENGLISH);
    }

    public static Identifier addSuffixToPath(Identifier identifier, String suffix) {
        return new Identifier(identifier.getNamespace(), identifier.getPath() + suffix);
    }

    public static Identifier addPrefixToPath(Identifier identifier, String prefix) {
        return new Identifier(identifier.getNamespace(), prefix + identifier.getPath());
    }

    public static Identifier addPrefixAndSuffixToPath(Identifier identifier, String prefix, String suffix) {
        return new Identifier(identifier.getNamespace(), prefix + identifier.getPath() + suffix);
    }

    public static TernarySurfaceConfig randomSurfaceBuilderConfig() {
        Map<String, TernarySurfaceConfig> surfaceBuilders = new HashMap<>();
        surfaceBuilders.put("minecraft:gravel_config", SurfaceBuilder.GRAVEL_CONFIG);
        surfaceBuilders.put("minecraft:grass_config", SurfaceBuilder.GRASS_CONFIG);
        surfaceBuilders.put("minecraft:dirt_config", SurfaceBuilder.DIRT_CONFIG);
        surfaceBuilders.put("minecraft:stone_config", SurfaceBuilder.STONE_CONFIG);
        surfaceBuilders.put("minecraft:coarse_dirt_config", SurfaceBuilder.COARSE_DIRT_CONFIG);
        surfaceBuilders.put("minecraft:sand_config", SurfaceBuilder.SAND_CONFIG);
        surfaceBuilders.put("minecraft:grass_sand_underwater_config", SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG);
        surfaceBuilders.put("minecraft:sand_sand_underwater_config", SurfaceBuilder.SAND_SAND_UNDERWATER_CONFIG);
        surfaceBuilders.put("minecraft:badlands_config", SurfaceBuilder.BADLANDS_CONFIG);
        surfaceBuilders.put("minecraft:mycelium_config", SurfaceBuilder.MYCELIUM_CONFIG);
        surfaceBuilders.put("minecraft:nether_config", SurfaceBuilder.NETHER_CONFIG);
        surfaceBuilders.put("minecraft:soul_sand_config", SurfaceBuilder.SOUL_SAND_CONFIG);
        surfaceBuilders.put("minecraft:end_config", SurfaceBuilder.END_CONFIG);
        surfaceBuilders.put("minecraft:crimson_nylium_config", SurfaceBuilder.CRIMSON_NYLIUM_CONFIG);
        surfaceBuilders.put("minecraft:warped_nylium_config", SurfaceBuilder.WARPED_NYLIUM_CONFIG);
        return Rands.map(surfaceBuilders).getValue();
    }

    public static TernarySurfaceConfig fromIdentifierToConfig(Identifier name) {
        if (name.equals(new Identifier("gravel_config"))) return SurfaceBuilder.GRAVEL_CONFIG;
        if (name.equals(new Identifier("grass_config"))) return SurfaceBuilder.GRASS_CONFIG;
        if (name.equals(new Identifier("dirt_config"))) return SurfaceBuilder.DIRT_CONFIG;
        if (name.equals(new Identifier("stone_config"))) return SurfaceBuilder.STONE_CONFIG;
        if (name.equals(new Identifier("coarse_dirt_config"))) return SurfaceBuilder.COARSE_DIRT_CONFIG;
        if (name.equals(new Identifier("sand_config"))) return SurfaceBuilder.SAND_CONFIG;
        if (name.equals(new Identifier("grass_sand_underwater_config"))) return SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG;
        if (name.equals(new Identifier("sand_sand_underwater_config"))) return SurfaceBuilder.SAND_SAND_UNDERWATER_CONFIG;
        if (name.equals(new Identifier("badlands_config"))) return SurfaceBuilder.BADLANDS_CONFIG;
        if (name.equals(new Identifier("mycelium_config"))) return SurfaceBuilder.MYCELIUM_CONFIG;
        if (name.equals(new Identifier("nether_config"))) return SurfaceBuilder.NETHER_CONFIG;
        if (name.equals(new Identifier("soul_sand_config"))) return SurfaceBuilder.SOUL_SAND_CONFIG;
        if (name.equals(new Identifier("end_config"))) return SurfaceBuilder.END_CONFIG;

        return SurfaceBuilder.GRASS_CONFIG;
    }

    public static Identifier fromConfigToIdentifier(TernarySurfaceConfig config) {
        if (config.equals(SurfaceBuilder.GRAVEL_CONFIG)) return new Identifier("gravel_config");

        if (config.equals(SurfaceBuilder.GRASS_CONFIG)) return new Identifier("grass_config");
        if (config.equals(SurfaceBuilder.DIRT_CONFIG)) return new Identifier("dirt_config");
        if (config.equals(SurfaceBuilder.STONE_CONFIG)) return new Identifier("stone_config");
        if (config.equals(SurfaceBuilder.COARSE_DIRT_CONFIG)) return new Identifier("coarse_dirt_config");
        if (config.equals(SurfaceBuilder.SAND_CONFIG)) return new Identifier("sand_config");
        if (config.equals(SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)) return new Identifier("grass_sand_underwater_config");
        if (config.equals(SurfaceBuilder.SAND_SAND_UNDERWATER_CONFIG)) return new Identifier("sand_sand_underwater_config");
        if (config.equals(SurfaceBuilder.BADLANDS_CONFIG)) return new Identifier("badlands_config");
        if (config.equals(SurfaceBuilder.MYCELIUM_CONFIG)) return new Identifier("mycelium_config");
        if (config.equals(SurfaceBuilder.NETHER_CONFIG)) return new Identifier("nether_config");
        if (config.equals(SurfaceBuilder.SOUL_SAND_CONFIG)) return new Identifier("soul_sand_config");
        if (config.equals(SurfaceBuilder.END_CONFIG)) return new Identifier("end_config");
        if (config.equals(SurfaceBuilder.CRIMSON_NYLIUM_CONFIG)) return new Identifier("crimson_nylium_config");
        if (config.equals(SurfaceBuilder.WARPED_NYLIUM_CONFIG)) return new Identifier("warped_nylium_config");

        return new Identifier("grass_config");
    }

    public static SurfaceBuilder<?> newRandomSurfaceBuilder() {
        Map<String, SurfaceBuilder<?>> surfaceBuilders = new HashMap<>();
        surfaceBuilders.put("raa:hyper_flat", DimensionSurfaceBuilders.HYPER_FLAT.getSurfaceBuilder());
        surfaceBuilders.put("raa:patchy_desert", DimensionSurfaceBuilders.PATCHY_DESERT.getSurfaceBuilder());
        surfaceBuilders.put("raa:dark_patchy_badlands", DimensionSurfaceBuilders.DARK_PATCHY_BADLANDS.getSurfaceBuilder());
        surfaceBuilders.put("raa:patchy_badlands", DimensionSurfaceBuilders.PATCHY_BADLANDS.getSurfaceBuilder());
        surfaceBuilders.put("raa:classic_cliffs", DimensionSurfaceBuilders.CLASSIC_CLIFFS.getSurfaceBuilder());
        surfaceBuilders.put("raa:stratified_cliffs", DimensionSurfaceBuilders.STRATIFIED_CLIFFS.getSurfaceBuilder());
        surfaceBuilders.put("raa:floating_islands", DimensionSurfaceBuilders.FLOATING_ISLANDS.getSurfaceBuilder());
        surfaceBuilders.put("raa:sandy_dunes", DimensionSurfaceBuilders.SANDY_DUNES.getSurfaceBuilder());
        surfaceBuilders.put("raa:dunes", DimensionSurfaceBuilders.DUNES.getSurfaceBuilder());
        surfaceBuilders.put("raa:lazy_noise", DimensionSurfaceBuilders.LAZY_NOISE.getSurfaceBuilder());
        surfaceBuilders.put("minecraft:default", SurfaceBuilder.DEFAULT);
        return Rands.map(surfaceBuilders).getValue();
    }

    public static DimensionChunkGenerators randomCG(int chance) {
        if (chance < 15) {
            if (chance <= 5) {
                return DimensionChunkGenerators.FLAT_CAVES;
            } else if (chance <= 10) {
                return DimensionChunkGenerators.HIGH_CAVES;
            }
            return DimensionChunkGenerators.CAVE;
        } else if (chance > 15 && chance < 30) {
            if (chance <= 20) {
                return DimensionChunkGenerators.LAYERED_FLOATING;
            } else if (chance <= 25) {
                return DimensionChunkGenerators.PRE_CLASSIC_FLOATING;
            }
            return DimensionChunkGenerators.FLOATING;
        } else {
            if (chance <= 40) {
                return DimensionChunkGenerators.QUADRUPLE_AMPLIFIED;
            } else if (chance <= 50) {
                return DimensionChunkGenerators.PILLAR_WORLD;
            } else if (chance <= 60 && FabricLoader.getInstance().isModLoaded("simplexterrain")) {
                return DimensionChunkGenerators.CUSTOM_OVERWORLD;
            } else if (chance <= 70) {
                return DimensionChunkGenerators.TOTALLY_CUSTOM;
            }
            return DimensionChunkGenerators.OVERWORLD;
        }
    }

    public static boolean checkBitFlag(int toCheck, int flag) {
        return (toCheck & flag) == flag;
    }

    public static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static String generateCivsName() throws IOException {
        String civilizationName;
        Random rand = new Random();
        Identifier surnames = new Identifier(RandomlyAddingAnything.MOD_ID, "names/civilizations.txt");
        InputStream stream = MinecraftClient.getInstance().getResourceManager().getResource(surnames).getInputStream();
        Scanner scanner = new Scanner(Objects.requireNonNull(stream));
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
            builder.append(",");
        }
        String[] strings = builder.toString().split(",");
        civilizationName = strings[rand.nextInt(strings.length)];
        stream.close();
        scanner.close();
        return civilizationName;
    }

    public static void createSpawnsFile(String name, IWorld world, BlockPos pos) {
        try {
            String path;
            World world2 = world.getWorld();
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
                path = "saves/" + ((ServerWorld) world2).getSaveHandler().getWorldDir().getName() + "/DIM_raa_" + world.getDimension().getType().getSuffix().substring(4) + "/data/" + name + "_spawns.txt";
            else
                path = world.getLevelProperties().getLevelName() + "/DIM_raa_" + world.getDimension().getType().getSuffix().substring(4) + "/data/" + name + "_spawns.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(pos.getX() + "," + pos.getY() + "," + pos.getZ() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
