package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public class CraftableSporeBlossoms extends AbstractRecipe {

    public CraftableSporeBlossoms() {
        super("craftable-spore-blossoms", Material.SPORE_BLOSSOM);
    }

    @Override
    public void registerRecipes() {
        RecipeChoice leaves = new RecipeChoice.MaterialChoice(List.of(Material.AZALEA_LEAVES, Material.FLOWERING_AZALEA_LEAVES));

        ShapedRecipe recipe = new ShapedRecipe(Key.get("craftable_spore_blossom"), new ItemStack(Material.SPORE_BLOSSOM, 4));
        recipe.shape("xsx", "sos", "xsx");
        recipe.setIngredient('x', Material.LILAC);
        recipe.setIngredient('s', leaves);
        recipe.setIngredient('o', Material.HONEYCOMB);
        Bukkit.addRecipe(recipe);
    }
}
