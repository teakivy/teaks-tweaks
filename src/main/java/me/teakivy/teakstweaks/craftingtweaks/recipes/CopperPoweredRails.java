package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CopperPoweredRails extends AbstractCraftingTweak {

    public CopperPoweredRails() {
        super("copper-powered-rails", Material.POWERED_RAIL);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("copper_powered_rail"),
                new ItemStack(Material.POWERED_RAIL, 6));
        recipe.shape("g g", "gig", "grg");
        recipe.setIngredient('g', Material.COPPER_INGOT);
        recipe.setIngredient('i', Material.STICK);
        recipe.setIngredient('r', Material.REDSTONE);
        addRecipe(recipe);
    }
}
