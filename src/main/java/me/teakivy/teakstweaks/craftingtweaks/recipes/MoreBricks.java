package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreBricks extends AbstractRecipe {

    public MoreBricks() {
        super("more-bricks", Material.BRICKS);
    }

    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("bricks_bricks"), new ItemStack(Material.BRICKS, 4));
        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.BRICK);
        Bukkit.addRecipe(recipe);
    }
}
