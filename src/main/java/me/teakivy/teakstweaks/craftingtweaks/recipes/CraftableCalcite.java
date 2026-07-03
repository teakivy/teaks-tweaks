package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableCalcite extends AbstractCraftingTweak {

    public CraftableCalcite() {
        super(TTCraftingTweak.CRAFTABLE_CALCITE, Material.CALCITE);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("calcite_craftable"), new ItemStack(Material.CALCITE, 2));
        recipe.shape("#x", "x#");
        recipe.setIngredient('#', Material.DIORITE);
        recipe.setIngredient('x', Material.BONE_MEAL);
        addRecipe(recipe);

        recipe = new ShapedRecipe(Key.get("calcite_craftable2"), new ItemStack(Material.CALCITE, 2));
        recipe.shape("#x", "x#");
        recipe.setIngredient('x', Material.DIORITE);
        recipe.setIngredient('#', Material.BONE_MEAL);
        addRecipe(recipe);
    }
}
