package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableGildedBlackstone extends AbstractRecipe {

    public CraftableGildedBlackstone() {
        super("Craftable Gilded Blackstone");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "craftable_gilded_blackstone");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.GILDED_BLACKSTONE, 1));

        recipe.addIngredient(Material.BLACKSTONE);
        recipe.addIngredient(Material.GOLD_INGOT);
        Bukkit.addRecipe(recipe);
    }
}
