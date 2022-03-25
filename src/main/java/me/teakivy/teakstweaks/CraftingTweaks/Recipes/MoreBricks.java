package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MoreBricks extends AbstractRecipe {

    public MoreBricks() {
        super("More Bricks");
    }

    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "bricks_vt_bricks");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.BRICKS, 4));

        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.BRICK);
        Bukkit.addRecipe(recipe);
    }

}
