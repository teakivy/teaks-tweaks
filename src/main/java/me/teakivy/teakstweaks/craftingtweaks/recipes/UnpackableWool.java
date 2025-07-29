package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
        List<Material> woolList = new ArrayList<>();
        woolList.add(Material.WHITE_WOOL);
        woolList.add(Material.ORANGE_WOOL);
        woolList.add(Material.MAGENTA_WOOL);
        woolList.add(Material.LIGHT_BLUE_WOOL);
        woolList.add(Material.YELLOW_WOOL);
        woolList.add(Material.LIME_WOOL);
        woolList.add(Material.PINK_WOOL);
        woolList.add(Material.GRAY_WOOL);
        woolList.add(Material.LIGHT_GRAY_WOOL);
        woolList.add(Material.CYAN_WOOL);
        woolList.add(Material.PURPLE_WOOL);
        woolList.add(Material.BLUE_WOOL);
        woolList.add(Material.BROWN_WOOL);
        woolList.add(Material.GREEN_WOOL);
        woolList.add(Material.RED_WOOL);
        woolList.add(Material.BLACK_WOOL);
        RecipeChoice wool = new RecipeChoice.MaterialChoice(woolList);

        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("wool_unpackables"),
                new ItemStack(Material.STRING, 4));
        recipe.addIngredient(wool);
        addRecipe(recipe);
    }
}
