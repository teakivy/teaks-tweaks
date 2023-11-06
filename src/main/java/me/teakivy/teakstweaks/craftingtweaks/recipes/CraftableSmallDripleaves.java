package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableSmallDripleaves extends AbstractRecipe {

    public CraftableSmallDripleaves() {
        super("craftable-small-dripleaf", Material.SMALL_DRIPLEAF);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("craftable_dripleaf"), new ItemStack(Material.SMALL_DRIPLEAF));
        recipe.addIngredient(Material.BIG_DRIPLEAF);
        Bukkit.addRecipe(recipe);
    }
}
