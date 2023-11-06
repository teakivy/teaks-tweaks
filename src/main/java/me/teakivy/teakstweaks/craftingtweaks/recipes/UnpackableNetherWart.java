package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class UnpackableNetherWart extends AbstractRecipe {

    public UnpackableNetherWart() {
        super("unpackable-nether-wart", Material.NETHER_WART);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("nether_wart_unpackables"),
                new ItemStack(Material.NETHER_WART, 9));
        recipe.addIngredient(Material.NETHER_WART_BLOCK);
        Bukkit.addRecipe(recipe);
    }
}
