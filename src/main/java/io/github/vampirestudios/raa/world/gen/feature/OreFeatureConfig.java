package io.github.vampirestudios.raa.world.gen.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import io.github.vampirestudios.raa.api.RAARegistery;
import io.github.vampirestudios.raa.utils.RegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.predicate.block.BlockPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OreFeatureConfig implements FeatureConfig {
    public final OreFeatureConfig.Target target;
    public final int size;
    public final BlockState state;

    public OreFeatureConfig(Target target, BlockState state, int size) {
        this.size = size;
        this.state = state;
        this.target = target;
    }

    public static OreFeatureConfig deserialize(Dynamic<?> dynamic) {
        int size = dynamic.get("size").asInt(0);
        OreFeatureConfig.Target target = RAARegistery.TARGET_REGISTRY.get(new Identifier(dynamic.get("target").asString("")));
        BlockState blockState = dynamic.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new OreFeatureConfig(target, blockState, size);
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("size"),
                ops.createInt(this.size), ops.createString("target"), ops.createString(this.target.getId().toString()),
                ops.createString("state"), BlockState.serialize(ops, this.state).getValue())));
    }

    public static class Target {

        public static final Target STONE = RegistryUtils.registerOreTarget("stone", new BlockPredicate(Blocks.STONE), Blocks.STONE);
        public static final Target ANDESITE = RegistryUtils.registerOreTarget("andesite", new BlockPredicate(Blocks.ANDESITE), Blocks.ANDESITE);
        public static final Target DIORITE = RegistryUtils.registerOreTarget("diorite", new BlockPredicate(Blocks.DIORITE), Blocks.DIORITE);
        public static final Target GRANITE = RegistryUtils.registerOreTarget("granite", new BlockPredicate(Blocks.GRANITE), Blocks.GRANITE);
        public static final Target GRASS_BLOCK = RegistryUtils.registerOreTarget("grass_block", new BlockPredicate(Blocks.GRASS_BLOCK), Blocks.GRASS_BLOCK);
        public static final Target GRAVEL = RegistryUtils.registerOreTarget("gravel", new BlockPredicate(Blocks.GRAVEL), Blocks.GRAVEL);
        public static final Target DIRT = RegistryUtils.registerOreTarget("dirt", new BlockPredicate(Blocks.DIRT), Blocks.DIRT);
        public static final Target COARSE_DIRT = RegistryUtils.registerOreTarget("coarse_dirt", new BlockPredicate(Blocks.COARSE_DIRT), Blocks.COARSE_DIRT);
        public static final Target PODZOL = RegistryUtils.registerOreTarget("podzol", new BlockPredicate(Blocks.PODZOL), Blocks.PODZOL);
        public static final Target CLAY = RegistryUtils.registerOreTarget("clay", new BlockPredicate(Blocks.CLAY), Blocks.CLAY);
        public static final Target SAND = RegistryUtils.registerOreTarget("sand", (blockState_1) -> {
            if (blockState_1 == null) {
                return false;
            } else {
                Block block_1 = blockState_1.getBlock();
                return block_1 == Blocks.SAND;
            }
        }, Blocks.SAND);
        public static final Target SANDSTONE = RegistryUtils.registerOreTarget("sandstone", (blockState_1) -> {
            if (blockState_1 == null) {
                return false;
            } else {
                Block block_1 = blockState_1.getBlock();
                return block_1 == Blocks.SANDSTONE;
            }
        }, Blocks.SANDSTONE);
        public static final Target RED_SAND = RegistryUtils.registerOreTarget("red_sand", (blockState_1) -> {
            if (blockState_1 == null) {
                return false;
            } else {
                Block block_1 = blockState_1.getBlock();
                return block_1 == Blocks.RED_SAND;
            }
        }, Blocks.SAND);
        public static final Target RED_SANDSTONE = RegistryUtils.registerOreTarget("red_sandstone", (blockState_1) -> {
            if (blockState_1 == null) {
                return false;
            } else {
                Block block_1 = blockState_1.getBlock();
                return block_1 == Blocks.RED_SANDSTONE;
            }
        }, Blocks.SANDSTONE);
        public static final Target NETHERRACK = RegistryUtils.registerOreTarget("netherrack", new BlockPredicate(Blocks.NETHERRACK), Blocks.NETHERRACK);
        public static final Target END_STONE = RegistryUtils.registerOreTarget("end_stone", new BlockPredicate(Blocks.END_STONE), Blocks.END_STONE);
        public static final Target DOES_NOT_APPEAR = RegistryUtils.registerOreTarget("does_not_appear", new BlockPredicate(null), null);

        private final Identifier name;
        private final Predicate<BlockState> predicate;
        private final Block block;

        public Target(Identifier name, Predicate<BlockState> predicate, Block block) {
            this.name = name;
            this.predicate = predicate;
            this.block = block;
        }

        public static List<Target> getValues() {
            return RAARegistery.TARGET_REGISTRY.stream().collect(Collectors.toList());
        }

        public Identifier getId() {
            return this.name;
        }

        public Predicate<BlockState> getCondition() {
            return this.predicate;
        }

        public Block getBlock() {
            return block;
        }

    }

}