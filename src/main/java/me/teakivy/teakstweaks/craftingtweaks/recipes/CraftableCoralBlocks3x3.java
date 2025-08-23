package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public class CraftableCoralBlocks3x3 extends AbstractCraftingTweak {

    public CraftableCoralBlocks3x3() {
        super(TTCraftingTweak.CRAFTABLE_CORAL_BLOCKS_3X3, Material.BRAIN_CORAL_BLOCK);
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
                Material.matchMaterial(type + "_CORAL"),
                Material.matchMaterial(type + "_CORAL_FAN")));

        ShapedRecipe recipe = new ShapedRecipe(Key.get(type.toLowerCase() + "_coral_2x2"),
                new ItemStack(Material.matchMaterial(type.toUpperCase() + "_CORAL_BLOCK")));
        recipe.shape("xxx", "xxx", "xxx");
        recipe.setIngredient('x', choice);
        addRecipe(recipe);
    }
}
