package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableGildedBlackstone extends AbstractRecipe {

    public CraftableGildedBlackstone() {
        super("craftable-gilded-blackstone", Material.GILDED_BLACKSTONE);
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = Key.get("craftable_gilded_blackstone");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.GILDED_BLACKSTONE, 1));

        recipe.addIngredient(Material.BLACKSTONE);
        recipe.addIngredient(Material.GOLD_INGOT);
        Bukkit.addRecipe(recipe);
    }
}
