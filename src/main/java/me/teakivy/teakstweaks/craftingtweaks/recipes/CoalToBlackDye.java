package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CoalToBlackDye extends AbstractRecipe {

     public CoalToBlackDye() {
         super("coal-to-black-dye", Material.COAL);
     }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(teaksTweaks, "black_dye_coal");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.BLACK_DYE));
        recipe.addIngredient(Material.COAL);


    }
}
