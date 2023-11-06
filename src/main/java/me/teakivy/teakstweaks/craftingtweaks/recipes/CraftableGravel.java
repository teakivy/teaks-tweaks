package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableGravel  extends AbstractRecipe {

    public CraftableGravel() {
        super("craftable-gravel", Material.GRAVEL);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("gravel_gravel"), new ItemStack(Material.GRAVEL, 4));
        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.FLINT);
        Bukkit.addRecipe(recipe);

    }
}
