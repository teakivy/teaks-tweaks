package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class UnpackableWool extends AbstractCraftingTweak {

    public UnpackableWool() {
        super(TTCraftingTweak.UNPACKABLE_WOOL, Material.STRING);
    }

    @Override
    public void registerRecipes() {
        RecipeChoice wool = new RecipeChoice.MaterialChoice(Tag.WOOL);

        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("wool_unpackables"),
                new ItemStack(Material.STRING, 4));
        recipe.addIngredient(wool);
        addRecipe(recipe);
    }
}
