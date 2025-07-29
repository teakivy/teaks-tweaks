package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftableSaddles extends AbstractCraftingTweak {

    public CraftableSaddles() {
        super(TTCraftingTweak.CRAFTABLE_SADDLES, Material.SADDLE);
    }

    @Override
    public void registerRecipes() {
        ShapedRecipe recipe = new ShapedRecipe(Key.get("saddle"), new ItemStack(Material.SADDLE));
        recipe.shape("lll", "s s");
        recipe.setIngredient('l', Material.LEATHER);
        recipe.setIngredient('s', Material.STRING);
        addRecipe(recipe);
    }
}
