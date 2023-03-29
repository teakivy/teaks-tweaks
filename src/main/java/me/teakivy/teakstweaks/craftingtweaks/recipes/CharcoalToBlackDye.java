package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CharcoalToBlackDye extends AbstractRecipe {

    public CharcoalToBlackDye() {
        super("Charcoal To Black Dye", "charcoal-to-black-dye", Material.CHARCOAL, "Allows you to craft Charcoal into Black Dye.");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "black_dye_charcoal");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.BLACK_DYE));
        recipe.addIngredient(Material.CHARCOAL);

        Bukkit.addRecipe(recipe);
    }
}
