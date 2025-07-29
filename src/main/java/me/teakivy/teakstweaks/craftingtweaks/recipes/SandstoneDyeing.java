package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class SandstoneDyeing extends AbstractCraftingTweak {

    public SandstoneDyeing() {
        super(TTCraftingTweak.SANDSTONE_DYEING, Material.RED_SANDSTONE);
    }

    @Override
    public void registerRecipes() {
        newRedRecipe(Material.SANDSTONE, Material.RED_SANDSTONE);
        newRedRecipe(Material.SMOOTH_SANDSTONE, Material.SMOOTH_RED_SANDSTONE);
        newRedRecipe(Material.SAND, Material.RED_SAND);
    }

    public void newRedRecipe(Material input, Material output) {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get(output.toString().toLowerCase() + "_sandstone_dyeing"),
                new ItemStack(output, 2));
        recipe.addIngredient(Material.RED_DYE);
        recipe.addIngredient(2, input);
        addRecipe(recipe);
    }
}
