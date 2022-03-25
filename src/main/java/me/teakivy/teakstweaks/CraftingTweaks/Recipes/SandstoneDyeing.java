package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class SandstoneDyeing extends AbstractRecipe {

    public SandstoneDyeing() {
        super("Sandstone Dyeing");
    }

    @Override
    public void registerRecipes() {
        newRedRecipe("red_sandstone", Material.SANDSTONE, Material.RED_SANDSTONE);
        newRedRecipe("smooth_red_sandstone", Material.SMOOTH_SANDSTONE, Material.SMOOTH_RED_SANDSTONE);
        newRedRecipe("red_sand", Material.SAND, Material.RED_SAND);
    }

    public static void newRedRecipe(String name, Material input, Material output) {

        NamespacedKey key = new NamespacedKey(main, name + "_vt_sandstone_dyeing");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(output, 2));

        recipe.addIngredient(Material.RED_DYE);
        recipe.addIngredient(2, input);

        Bukkit.addRecipe(recipe);
    }
}
