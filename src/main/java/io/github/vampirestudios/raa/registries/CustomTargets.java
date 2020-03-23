package io.github.vampirestudios.raa.registries;

import io.github.vampirestudios.raa.utils.RegistryUtils;
import io.github.vampirestudios.raa.world.gen.feature.OreFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.predicate.block.BlockPredicate;

public class CustomTargets {

    public static OreFeatureConfig.Target STONE = RegistryUtils.registerOreTarget("stone", new BlockPredicate(Blocks.STONE), Blocks.STONE);
    public static OreFeatureConfig.Target ANDESITE = RegistryUtils.registerOreTarget("andesite", new BlockPredicate(Blocks.ANDESITE), Blocks.ANDESITE);
    public static OreFeatureConfig.Target DIORITE = RegistryUtils.registerOreTarget("diorite", new BlockPredicate(Blocks.DIORITE), Blocks.DIORITE);
    public static OreFeatureConfig.Target GRANITE = RegistryUtils.registerOreTarget("granite", new BlockPredicate(Blocks.GRANITE), Blocks.GRANITE);
    public static OreFeatureConfig.Target GRASS_BLOCK = RegistryUtils.registerOreTarget("grass_block", new BlockPredicate(Blocks.GRASS_BLOCK), Blocks.GRASS_BLOCK);
    public static OreFeatureConfig.Target GRAVEL = RegistryUtils.registerOreTarget("gravel", new BlockPredicate(Blocks.GRAVEL), Blocks.GRAVEL);
    public static OreFeatureConfig.Target DIRT = RegistryUtils.registerOreTarget("dirt", new BlockPredicate(Blocks.DIRT), Blocks.DIRT);
    public static OreFeatureConfig.Target COARSE_DIRT = RegistryUtils.registerOreTarget("coarse_dirt", new BlockPredicate(Blocks.COARSE_DIRT), Blocks.COARSE_DIRT);
    public static OreFeatureConfig.Target PODZOL = RegistryUtils.registerOreTarget("podzol", new BlockPredicate(Blocks.PODZOL), Blocks.PODZOL);
    public static OreFeatureConfig.Target CLAY = RegistryUtils.registerOreTarget("clay", new BlockPredicate(Blocks.CLAY), Blocks.CLAY);
    public static OreFeatureConfig.Target SAND = RegistryUtils.registerOreTarget("sand", (blockState_1) -> {
        if (blockState_1 == null) {
            return false;
        } else {
            Block block_1 = blockState_1.getBlock();
            return block_1 == Blocks.SAND;
        }
    }, Blocks.SAND);
    public static OreFeatureConfig.Target SANDSTONE = RegistryUtils.registerOreTarget("sandstone", (blockState_1) -> {
        if (blockState_1 == null) {
            return false;
        } else {
            Block block_1 = blockState_1.getBlock();
            return block_1 == Blocks.SANDSTONE;
        }
    }, Blocks.SANDSTONE);
    public static OreFeatureConfig.Target RED_SAND = RegistryUtils.registerOreTarget("red_sand", (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            Block block = blockState.getBlock();
            return block == Blocks.RED_SAND;
        }
    }, Blocks.SAND);
    public static OreFeatureConfig.Target RED_SANDSTONE = RegistryUtils.registerOreTarget("red_sandstone", (blockState_1) -> {
        if (blockState_1 == null) {
            return false;
        } else {
            Block block_1 = blockState_1.getBlock();
            return block_1 == Blocks.RED_SANDSTONE;
        }
    }, Blocks.SANDSTONE);
    public static OreFeatureConfig.Target NETHERRACK = RegistryUtils.registerOreTarget("netherrack", new BlockPredicate(Blocks.NETHERRACK), Blocks.NETHERRACK);
    public static OreFeatureConfig.Target END_STONE = RegistryUtils.registerOreTarget("end_stone", new BlockPredicate(Blocks.END_STONE), Blocks.END_STONE);
    public static OreFeatureConfig.Target DOES_NOT_APPEAR = RegistryUtils.registerOreTarget("does_not_appear", new BlockPredicate(null), null);

}
