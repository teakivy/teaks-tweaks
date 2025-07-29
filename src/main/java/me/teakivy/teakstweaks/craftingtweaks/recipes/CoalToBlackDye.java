package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CoalToBlackDye extends AbstractCraftingTweak {

     public CoalToBlackDye() {
         super(TTCraftingTweak.COAL_TO_BLACK_DYE, Material.COAL);
     }

    @Override
    public void registerRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("black_dye_coal"), new ItemStack(Material.BLACK_DYE));
        recipe.addIngredient(Material.COAL);
        addRecipe(recipe);
    }
}
