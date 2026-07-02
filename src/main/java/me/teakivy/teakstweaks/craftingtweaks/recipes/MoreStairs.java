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
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Iterator;

public class MoreStairs extends AbstractCraftingTweak {

    public MoreStairs() {
        super(TTCraftingTweak.MORE_STAIRS, Material.OAK_STAIRS);
    }

    @Override
    public void registerRecipes() {
        Tag.STAIRS.getValues().forEach(stair -> {
            Material base = ItemUtils.getBaseBlock(stair, "_STAIRS");
            if (base != null) {
                newStairsRecipe(base, stair);
            }
        });
    }

    public void newStairsRecipe(Material input, Material output) {
        Bukkit.removeRecipe(NamespacedKey.minecraft(output.toString().toLowerCase()));
        ShapedRecipe recipe = new ShapedRecipe(Key.get(output.name().toLowerCase() + "_more_stairs"), new ItemStack(output, 8));
        recipe.shape("x  ", "xx ", "xxx");
        recipe.setIngredient('x', input);
        addRecipe(recipe);
    }
}
