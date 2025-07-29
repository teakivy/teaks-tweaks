package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public class CraftableCoralBlocks2x2 extends AbstractCraftingTweak {

    public CraftableCoralBlocks2x2() {
        super(TTCraftingTweak.CRAFTABLE_CORAL_BLOCKS_2X2, Material.TUBE_CORAL_BLOCK);
    }

    @Override
    public void registerRecipes() {
        newCoralRecipe("TUBE");
        newCoralRecipe("BRAIN");
        newCoralRecipe("BUBBLE");
        newCoralRecipe("FIRE");
        newCoralRecipe("HORN");
    }

    private void newCoralRecipe(String type) {
        RecipeChoice choice = new RecipeChoice.MaterialChoice(List.of(
                Material.valueOf(type + "_CORAL"),
                Material.valueOf(type + "_CORAL_FAN")));

        ShapedRecipe recipe = new ShapedRecipe(Key.get(type.toLowerCase() + "_coral_2x2"),
                new ItemStack(Material.valueOf(type.toUpperCase() + "_CORAL_BLOCK")));
        recipe.shape("xx", "xx");
        recipe.setIngredient('x', choice);
        addRecipe(recipe);
    }
}
