package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class UnpackableNetherWart extends AbstractCraftingTweak {

    public UnpackableNetherWart() {
        super(TTCraftingTweak.UNPACKABLE_NETHER_WART, Material.NETHER_WART);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("nether_wart_unpackables"),
                new ItemStack(Material.NETHER_WART, 9));
        recipe.addIngredient(Material.NETHER_WART_BLOCK);
        addRecipe(recipe);
    }
}
