package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CharcoalToBlackDye extends AbstractRecipe {

    public CharcoalToBlackDye() {
        super("Charcoal To Black Dye");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "black_dye_vt_charcoal");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.BLACK_DYE));
        recipe.addIngredient(Material.CHARCOAL);

        Bukkit.addRecipe(recipe);
    }
}
