package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class UnpackableNetherWart extends AbstractRecipe {

    public UnpackableNetherWart() {
        super("Unpackable Nether Wart");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "nether_wart_unpackables");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.NETHER_WART, 9));
        recipe.addIngredient(Material.NETHER_WART_BLOCK);

        Bukkit.addRecipe(recipe);
    }
}
