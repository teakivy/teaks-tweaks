package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableEnchantedGoldenApples extends AbstractRecipe {

    public CraftableEnchantedGoldenApples() {
        super("Craftable Enchanted Golden Apples");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "enchanted_golden_apples_vt_apples");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));

        recipe.shape("xxx", "x@x", "xxx");
        recipe.setIngredient('x', Material.GOLD_BLOCK);
        recipe.setIngredient('@', Material.APPLE);

        Bukkit.addRecipe(recipe);
    }

}
