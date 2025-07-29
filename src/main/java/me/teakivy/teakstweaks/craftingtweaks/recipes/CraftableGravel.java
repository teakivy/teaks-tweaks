package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableGravel  extends AbstractCraftingTweak {

    public CraftableGravel() {
        super(TTCraftingTweak.CRAFTABLE_GRAVEL, Material.GRAVEL);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("gravel_gravel"), new ItemStack(Material.GRAVEL, 4));
        recipe.shape("##", "##");
        recipe.setIngredient('#', Material.FLINT);
        addRecipe(recipe);

    }
}
