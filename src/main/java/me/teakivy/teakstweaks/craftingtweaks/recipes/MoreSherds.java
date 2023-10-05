package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class MoreSherds extends AbstractRecipe {
    public MoreSherds() {
        super("more-sherds", Material.SHEAF_POTTERY_SHERD);
    }

    @Override
    public void registerRecipes() {
        for (Material value : Material.values()) {
            if (!value.toString().contains("SHERD")) continue;

            registerSherdRecipe(value);
        }
    }

    private void registerSherdRecipe(Material material) {
        NamespacedKey key = new NamespacedKey(main, material.toString().toLowerCase() + "_more_sherds");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(material, 2));

        recipe.addIngredient(material);
        recipe.addIngredient(Material.BRICK);

        Bukkit.addRecipe(recipe);
    }
}
