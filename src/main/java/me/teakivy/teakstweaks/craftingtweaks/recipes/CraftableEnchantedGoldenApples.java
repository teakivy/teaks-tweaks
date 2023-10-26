package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableEnchantedGoldenApples extends AbstractRecipe {

    public CraftableEnchantedGoldenApples() {
        super("craftable-enchanted-golden-apples", Material.ENCHANTED_GOLDEN_APPLE);
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = Key.get("enchanted_golden_apples_apples");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));

        recipe.shape("xxx", "x@x", "xxx");
        recipe.setIngredient('x', Material.GOLD_BLOCK);
        recipe.setIngredient('@', Material.APPLE);

        Bukkit.addRecipe(recipe);
    }

}
