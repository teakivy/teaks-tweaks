package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public class CraftableCoral3x3 extends AbstractCraftingTweak {

    public CraftableCoral3x3() {
        super("craftable-coral-blocks-3x3", Material.BRAIN_CORAL_BLOCK);
    }

    @Override
    public void registerRecipes() {
        newCoralRecipe("TUBE");
        newCoralRecipe("BRAIN");
        newCoralRecipe("BUBBLE");
        newCoralRecipe("FIRE");
        newCoralRecipe("HORN");
    }

    private static void newCoralRecipe(String type) {
        RecipeChoice choice = new RecipeChoice.MaterialChoice(List.of(
                Material.valueOf(type + "_CORAL"),
                Material.valueOf(type + "_CORAL_FAN")));

        ShapedRecipe recipe = new ShapedRecipe(Key.get(type.toLowerCase() + "_coral_2x2"),
                new ItemStack(Material.valueOf(type.toUpperCase() + "_CORAL_BLOCK")));
        recipe.shape("xxx", "xxx", "xxx");
        recipe.setIngredient('x', choice);
        Bukkit.addRecipe(recipe);
    }
}
