package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class MoreSherds extends AbstractCraftingTweak {

    public MoreSherds() {
        super(TTCraftingTweak.MORE_SHERDS, Material.SHEAF_POTTERY_SHERD);
    }

    @Override
    public void registerRecipes() {
        for (Material value : Material.values()) {
            if (!value.toString().contains("SHERD")) continue;

            registerSherdRecipe(value);
        }
    }

    private void registerSherdRecipe(Material material) {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get(material.toString().toLowerCase() + "_more_sherds"),
                new ItemStack(material, 2));
        recipe.addIngredient(material);
        recipe.addIngredient(Material.BRICK);
        addRecipe(recipe);
    }
}
