package fr.arthurbambou.randomlyaddinganything.registries;

import fr.arthurbambou.randomlyaddinganything.RandomlyAddingAnything;
import fr.arthurbambou.randomlyaddinganything.api.NameGenerator;
import fr.arthurbambou.randomlyaddinganything.api.enums.AppearsIn;
import fr.arthurbambou.randomlyaddinganything.api.enums.OreTypes;
import fr.arthurbambou.randomlyaddinganything.blocks.LayeredOreBlock;
import fr.arthurbambou.randomlyaddinganything.helpers.Rands;
import fr.arthurbambou.randomlyaddinganything.items.RAAAxeItem;
import fr.arthurbambou.randomlyaddinganything.items.RAAHoeItem;
import fr.arthurbambou.randomlyaddinganything.items.RAAPickaxeItem;
import fr.arthurbambou.randomlyaddinganything.items.RAAShovelItem;
import fr.arthurbambou.randomlyaddinganything.materials.CustomArmorMaterial;
import fr.arthurbambou.randomlyaddinganything.materials.CustomToolMaterial;
import fr.arthurbambou.randomlyaddinganything.materials.Material;
import fr.arthurbambou.randomlyaddinganything.materials.MaterialBuilder;
import fr.arthurbambou.randomlyaddinganything.utils.RegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Materials {
    public static final List<Material> MATERIAL_LIST = new ArrayList<>();

    public static boolean isReady = false;

    public static void init() {
        for (int a = 0; a < RandomlyAddingAnything.CONFIG.materialNumber; a++) {
            int[] RGB = new int[]{Rands.rand(256),Rands.rand(256),Rands.rand(256)};
            Random random = new Random();
            Material material = MaterialBuilder.create()
                    .oreType(Rands.values(OreTypes.values())).name(NameGenerator.generate()).color(RGB)
                    .generatesIn(Rands.values(AppearsIn.values())).overlayTexture(Rands.list(OreTypes.METAL_TEXTURES))
                    .resourceItemTexture(Rands.list(OreTypes.GEM_TEXTURES)).armor(random.nextBoolean())
                    .tools(random.nextBoolean()).weapons(random.nextBoolean()).build();
            MATERIAL_LIST.add(material);
            // Debug Only
            if (RandomlyAddingAnything.CONFIG.debug) {
                System.out.println("\nname : " + material.getName() +
                        "\noreType : " + material.getOreType().name().toLowerCase() +
                        "\nRGB color : " + RGB[0] + "," + RGB[1] + "," + RGB[2] +
                        "\nGenerate in : " + material.getGenerateIn().name().toLowerCase() +
                        "\nOverlay Texture : " + material.getOverlayTexture().toString() +
                        "\nItem Texture : " + material.getResourceItemTexture().toString());
            }
        }
        isReady = true;
    }

    public static boolean isIsReady() {
        return isReady;
    }

    public static void createMaterialResources() {
        for (Material material : MATERIAL_LIST) {
            Item repairItem;
            RegistryUtils.register(new Block(Block.Settings.copy(Blocks.IRON_BLOCK)),
                    new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_block"), RandomlyAddingAnything.ITEM_GROUP);
            RegistryUtils.register(new LayeredOreBlock(Block.Settings.copy(Blocks.IRON_ORE)),
                    new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_ore"), RandomlyAddingAnything.ITEM_GROUP);
            if (material.getOreType() == OreTypes.METAL) {
                RegistryUtils.registerItem(repairItem = new Item(new Item.Settings().group(RandomlyAddingAnything.ITEM_GROUP)), new Identifier(RandomlyAddingAnything.MOD_ID,
                        material.getName().toLowerCase() + "_ingot"));
                RegistryUtils.registerItem(new Item(new Item.Settings().group(RandomlyAddingAnything.ITEM_GROUP)), new Identifier(RandomlyAddingAnything.MOD_ID,
                        material.getName().toLowerCase() + "_nugget"));
            } else {
                RegistryUtils.registerItem(repairItem = new Item(new Item.Settings().group(RandomlyAddingAnything.ITEM_GROUP)), new Identifier(RandomlyAddingAnything.MOD_ID,
                        material.getName().toLowerCase() + "_gem"));
            }
            if (material.hasArmor()) {
                RegistryUtils.registerItem(
                        new DyeableArmorItem(new CustomArmorMaterial(Ingredient.ofItems(repairItem), material.getName()),
                                EquipmentSlot.HEAD, (new Item.Settings()).group(RandomlyAddingAnything.ITEM_GROUP)),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_helmet")
                );
                RegistryUtils.registerItem(
                        new DyeableArmorItem(new CustomArmorMaterial(Ingredient.ofItems(repairItem), material.getName()),
                                EquipmentSlot.CHEST, (new Item.Settings()).group(RandomlyAddingAnything.ITEM_GROUP)),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_chestplate")
                );
                RegistryUtils.registerItem(
                        new DyeableArmorItem(new CustomArmorMaterial(Ingredient.ofItems(repairItem), material.getName()),
                                EquipmentSlot.LEGS, (new Item.Settings()).group(RandomlyAddingAnything.ITEM_GROUP)),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_leggings")
                );
                RegistryUtils.registerItem(
                        new DyeableArmorItem(new CustomArmorMaterial(Ingredient.ofItems(repairItem), material.getName()),
                                EquipmentSlot.FEET, (new Item.Settings()).group(RandomlyAddingAnything.ITEM_GROUP)),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_boots")
                );
            }
            if (material.hasTools()) {
                RegistryUtils.registerItem(
                        new RAAPickaxeItem(
                                new CustomToolMaterial(Ingredient.ofItems(repairItem), 100, 1.0F, 1.0F, 3),
                                10, 2.0F, new Item.Settings().group(RandomlyAddingAnything.ITEM_GROUP).recipeRemainder(repairItem)
                        ),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_pickaxe")
                );
                RegistryUtils.registerItem(
                        new RAAAxeItem(
                                new CustomToolMaterial(Ingredient.ofItems(repairItem), 100, 1.0F, 1.0F, 3),
                                10, 2.0F, new Item.Settings().group(RandomlyAddingAnything.ITEM_GROUP).recipeRemainder(repairItem)
                        ),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_axe")
                );
                RegistryUtils.registerItem(
                        new RAAShovelItem(
                                new CustomToolMaterial(Ingredient.ofItems(repairItem), 100, 1.0F, 1.0F, 3),
                                10, 2.0F, new Item.Settings().group(RandomlyAddingAnything.ITEM_GROUP).recipeRemainder(repairItem)
                        ),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_shovel")
                );
                RegistryUtils.registerItem(
                        new RAAHoeItem(
                                new CustomToolMaterial(Ingredient.ofItems(repairItem), 100, 1.0F, 1.0F, 3),
                                2.0F, new Item.Settings().group(RandomlyAddingAnything.ITEM_GROUP).recipeRemainder(repairItem)
                        ),
                        new Identifier(RandomlyAddingAnything.MOD_ID, material.getName().toLowerCase() + "_hoe")
                );
            }
        }
    }

}
