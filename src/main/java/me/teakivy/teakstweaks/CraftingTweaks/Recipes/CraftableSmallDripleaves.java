package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableSmallDripleaves extends AbstractRecipe {

    public CraftableSmallDripleaves() {
        super("Craftable Small Dripleaves");
    }

    @Override
    public void registerRecipes() {

        NamespacedKey key = new NamespacedKey(main, "craftable_dripleaf");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.SMALL_DRIPLEAF, 1));

        recipe.addIngredient(Material.BIG_DRIPLEAF);
        Bukkit.addRecipe(recipe);

    }

}
