package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public class CraftableBlackstone extends AbstractCraftingTweak {

    public CraftableBlackstone() {
        super(TTCraftingTweak.CRAFTABLE_BLACKSTONE, Material.POLISHED_BLACKSTONE);
    }

    @Override
    public void registerRecipes() {
        RecipeChoice coal = new RecipeChoice.MaterialChoice(List.of(Material.COAL, Material.CHARCOAL));
        RecipeChoice basalt = new RecipeChoice.MaterialChoice(List.of(Material.BASALT, Material.SMOOTH_BASALT));

        ShapedRecipe recipe = new ShapedRecipe(Key.get("blackstone_craftables"), new ItemStack(Material.BLACKSTONE));
        recipe.shape("#x", "x#");
        recipe.setIngredient('#', coal);
        recipe.setIngredient('x', basalt);
        addRecipe(recipe);
    }
}
