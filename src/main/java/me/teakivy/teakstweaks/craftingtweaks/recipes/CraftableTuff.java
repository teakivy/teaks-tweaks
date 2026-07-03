package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableTuff extends AbstractCraftingTweak {

    public CraftableTuff() {
        super(TTCraftingTweak.CRAFTABLE_TUFF, Material.TUFF);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("tuff_craftable"), new ItemStack(Material.TUFF, 2));
        recipe.shape("#x", "x#");
        recipe.setIngredient('#', Material.BLACKSTONE);
        recipe.setIngredient('x', Material.QUARTZ);
        addRecipe(recipe);

        recipe = new ShapedRecipe(Key.get("tuff_craftable2"), new ItemStack(Material.TUFF, 2));
        recipe.shape("#x", "x#");
        recipe.setIngredient('x', Material.BLACKSTONE);
        recipe.setIngredient('#', Material.QUARTZ);
        addRecipe(recipe);
    }
}
