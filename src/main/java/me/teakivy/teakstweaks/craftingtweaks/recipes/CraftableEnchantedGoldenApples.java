package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableEnchantedGoldenApples extends AbstractCraftingTweak {

    public CraftableEnchantedGoldenApples() {
        super(TTCraftingTweak.CRAFTABLE_ENCHANTED_GOLDEN_APPLES, Material.ENCHANTED_GOLDEN_APPLE);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("enchanted_golden_apples_apples"),
                new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        recipe.shape("xxx", "x@x", "xxx");
        recipe.setIngredient('x', Material.GOLD_BLOCK);
        recipe.setIngredient('@', Material.APPLE);
        addRecipe(recipe);
    }
}
