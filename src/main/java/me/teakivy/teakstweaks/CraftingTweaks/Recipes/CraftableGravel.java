package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableGravel  extends AbstractRecipe {

    public CraftableGravel() {
        super("CraftableGravel");
    }

    @Override
    public void registerRecipes() {

        NamespacedKey key = new NamespacedKey(main, "gravel_vt_gravel");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.GRAVEL, 4));

        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.FLINT);
        Bukkit.addRecipe(recipe);

    }

}
