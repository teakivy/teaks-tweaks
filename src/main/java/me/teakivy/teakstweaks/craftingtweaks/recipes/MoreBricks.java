package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreBricks extends AbstractRecipe {

    public MoreBricks() {
        super("More Bricks", "more-bricks");
    }

    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "bricks_bricks");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BRICKS, 4));

        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.BRICK);
        Bukkit.addRecipe(recipe);
    }

}
