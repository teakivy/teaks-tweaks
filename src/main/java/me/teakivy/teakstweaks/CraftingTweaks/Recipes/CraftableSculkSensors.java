package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableSculkSensors extends AbstractRecipe {

    public CraftableSculkSensors() {
        super("Craftable Sculk Sensors");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "craftable_sculk_sensor");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.SCULK_SENSOR));

        recipe.shape("| |", "(o(", "xcx");
        recipe.setIngredient('|', Material.TWISTING_VINES);
        recipe.setIngredient('(', Material.WARPED_WART_BLOCK);
        recipe.setIngredient('o', Material.ENDER_EYE);
        recipe.setIngredient('x', Material.OBSIDIAN);
        recipe.setIngredient('c', Material.CRYING_OBSIDIAN);


        Bukkit.addRecipe(recipe);
    }
}
