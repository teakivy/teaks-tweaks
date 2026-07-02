package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.ItemUtils;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreTrapdoors extends AbstractCraftingTweak {

    public MoreTrapdoors() {
        super(TTCraftingTweak.MORE_TRAPDOORS, Material.SPRUCE_TRAPDOOR);
    }

    @Override
    public void registerRecipes() {
        Tag.WOODEN_TRAPDOORS.getValues().forEach(trapdoor -> {
            Material base = ItemUtils.getBaseBlock(trapdoor, "_TRAPDOOR");
            if (base != null) {
                newTrapdoorRecipe(base, trapdoor);
            }
        });
    }

    public void newTrapdoorRecipe(Material input, Material output) {
        Bukkit.removeRecipe(NamespacedKey.minecraft(output.name()));
        ShapedRecipe recipe = new ShapedRecipe(Key.get(output.name()),
                new ItemStack(output, 12));
        recipe.shape("xxx", "xxx");
        recipe.setIngredient('x', input);
        addRecipe(recipe);
    }


}
