package io.github.vampirestudios.raa.items.material;

import io.github.vampirestudios.raa.generation.materials.Material;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.apache.commons.lang3.text.WordUtils;

public class RAAAxeItem extends AxeItem {

    private Material material;

    public RAAAxeItem(Material material, ToolMaterial toolMaterial_1, float attackDamage, float attackSpeed, Settings item$Settings_1) {
        super(toolMaterial_1, attackDamage, attackSpeed, item$Settings_1);
        this.material = material;
    }

    @Override
    public Text getName(ItemStack itemStack_1) {
        return new TranslatableText("text.raa.item.axe", new LiteralText(WordUtils.capitalize(material.getName())));
    }

}
