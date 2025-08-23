package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class UniversalDyeing extends AbstractCraftingTweak {

    public UniversalDyeing() {
        super(TTCraftingTweak.UNIVERSAL_DYEING, Material.PURPLE_TERRACOTTA);
    }

    @Override
    public void registerRecipes() {
        registerNewRecipeType("CONCRETE_POWDER");
        registerNewRecipeType("GLAZED_TERRACOTTA");
        registerNewRecipeType("TERRACOTTA");
        registerNewRecipeType("WOOL");
        registerNewRecipeType("STAINED_GLASS");
        registerNewRecipeType("STAINED_GLASS_PANE");

        registerBedRecipeTypes("BED");

        newClearRecipe("TERRACOTTA");
        newClearStainedRecipe("GLASS");
        newClearStainedRecipe("GLASS_PANE");
    }

    public void registerNewRecipeType(String inputName) {
        newDyeingRecipe("black_", inputName, Material.BLACK_DYE, Material.matchMaterial("BLACK_" + inputName));
        newDyeingRecipe("blue_", inputName, Material.BLUE_DYE, Material.matchMaterial("BLUE_" + inputName));
        newDyeingRecipe("brown_", inputName, Material.BROWN_DYE, Material.matchMaterial("BROWN_" + inputName));
        newDyeingRecipe("cyan_", inputName, Material.CYAN_DYE, Material.matchMaterial("CYAN_" + inputName));
        newDyeingRecipe("gray_", inputName, Material.GRAY_DYE, Material.matchMaterial("GRAY_" + inputName));
        newDyeingRecipe("green_", inputName, Material.GREEN_DYE, Material.matchMaterial("GREEN_" + inputName));
        newDyeingRecipe("light_blue_", inputName, Material.LIGHT_BLUE_DYE, Material.matchMaterial("LIGHT_BLUE_" + inputName));
        newDyeingRecipe("light_gray_", inputName, Material.LIGHT_GRAY_DYE, Material.matchMaterial("LIGHT_GRAY_" + inputName));
        newDyeingRecipe("lime_", inputName, Material.LIME_DYE, Material.matchMaterial("LIME_" + inputName));
        newDyeingRecipe("magenta_", inputName, Material.MAGENTA_DYE, Material.matchMaterial("MAGENTA_" + inputName));
        newDyeingRecipe("orange_", inputName, Material.ORANGE_DYE, Material.matchMaterial("ORANGE_" + inputName));
        newDyeingRecipe("pink_", inputName, Material.PINK_DYE, Material.matchMaterial("PINK_" + inputName));
        newDyeingRecipe("purple_", inputName, Material.PURPLE_DYE, Material.matchMaterial("PURPLE_" + inputName));
        newDyeingRecipe("red_", inputName, Material.RED_DYE, Material.matchMaterial("RED_" + inputName));
        newDyeingRecipe("white_", inputName, Material.WHITE_DYE, Material.matchMaterial("WHITE_" + inputName));
        newDyeingRecipe("yellow_", inputName, Material.YELLOW_DYE, Material.matchMaterial("YELLOW_" + inputName));
    }

    public void newDyeingRecipe(String colorType, String inputName, Material inputDye, Material output) {
        NamespacedKey key = Key.get(colorType + inputName.toLowerCase() + "_universal");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(output, 8));

        List<Material> materials = new ArrayList<>();
        materials.add(Material.matchMaterial("BLACK_" + inputName));
        materials.add(Material.matchMaterial("BLUE_" + inputName));
        materials.add(Material.matchMaterial("BROWN_" + inputName));
        materials.add(Material.matchMaterial("CYAN_" + inputName));
        materials.add(Material.matchMaterial("GRAY_" + inputName));
        materials.add(Material.matchMaterial("GREEN_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_BLUE_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_GRAY_" + inputName));
        materials.add(Material.matchMaterial("LIME_" + inputName));
        materials.add(Material.matchMaterial("MAGENTA_" + inputName));
        materials.add(Material.matchMaterial("ORANGE_" + inputName));
        materials.add(Material.matchMaterial("PINK_" + inputName));
        materials.add(Material.matchMaterial("PURPLE_" + inputName));
        materials.add(Material.matchMaterial("RED_" + inputName));
        materials.add(Material.matchMaterial("WHITE_" + inputName));
        materials.add(Material.matchMaterial("YELLOW_" + inputName));

        recipe.shape("###", "#o#", "###");
        recipe.setIngredient('#', new RecipeChoice.MaterialChoice(materials));
        recipe.setIngredient('o', inputDye);

        addRecipe(recipe);

    }

    public void newBedRecipe(String colorType, String inputName, Material inputDye, Material output) {
        NamespacedKey key = Key.get(colorType + inputName.toLowerCase() + "_universal");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(output));


        List<Material> materials = new ArrayList<>();
        materials.add(Material.matchMaterial("BLACK_" + inputName));
        materials.add(Material.matchMaterial("BLUE_" + inputName));
        materials.add(Material.matchMaterial("BROWN_" + inputName));
        materials.add(Material.matchMaterial("CYAN_" + inputName));
        materials.add(Material.matchMaterial("GRAY_" + inputName));
        materials.add(Material.matchMaterial("GREEN_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_BLUE_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_GRAY_" + inputName));
        materials.add(Material.matchMaterial("LIME_" + inputName));
        materials.add(Material.matchMaterial("MAGENTA_" + inputName));
        materials.add(Material.matchMaterial("ORANGE_" + inputName));
        materials.add(Material.matchMaterial("PINK_" + inputName));
        materials.add(Material.matchMaterial("PURPLE_" + inputName));
        materials.add(Material.matchMaterial("RED_" + inputName));
        materials.add(Material.matchMaterial("WHITE_" + inputName));
        materials.add(Material.matchMaterial("YELLOW_" + inputName));

        recipe.addIngredient(new RecipeChoice.MaterialChoice(materials));
        recipe.addIngredient(inputDye);

        addRecipe(recipe);

    }

    public void registerBedRecipeTypes(String inputName) {
        newBedRecipe("black_", inputName, Material.BLACK_DYE, Material.matchMaterial("BLACK_" + inputName));
        newBedRecipe("blue_", inputName, Material.BLUE_DYE, Material.matchMaterial("BLUE_" + inputName));
        newBedRecipe("brown_", inputName, Material.BROWN_DYE, Material.matchMaterial("BROWN_" + inputName));
        newBedRecipe("cyan_", inputName, Material.CYAN_DYE, Material.matchMaterial("CYAN_" + inputName));
        newBedRecipe("gray_", inputName, Material.GRAY_DYE, Material.matchMaterial("GRAY_" + inputName));
        newBedRecipe("green_", inputName, Material.GREEN_DYE, Material.matchMaterial("GREEN_" + inputName));
        newBedRecipe("light_blue_", inputName, Material.LIGHT_BLUE_DYE, Material.matchMaterial("LIGHT_BLUE_" + inputName));
        newBedRecipe("light_gray_", inputName, Material.LIGHT_GRAY_DYE, Material.matchMaterial("LIGHT_GRAY_" + inputName));
        newBedRecipe("lime_", inputName, Material.LIME_DYE, Material.matchMaterial("LIME_" + inputName));
        newBedRecipe("magenta_", inputName, Material.MAGENTA_DYE, Material.matchMaterial("MAGENTA_" + inputName));
        newBedRecipe("orange_", inputName, Material.ORANGE_DYE, Material.matchMaterial("ORANGE_" + inputName));
        newBedRecipe("pink_", inputName, Material.PINK_DYE, Material.matchMaterial("PINK_" + inputName));
        newBedRecipe("purple_", inputName, Material.PURPLE_DYE, Material.matchMaterial("PURPLE_" + inputName));
        newBedRecipe("red_", inputName, Material.RED_DYE, Material.matchMaterial("RED_" + inputName));
        newBedRecipe("white_", inputName, Material.WHITE_DYE, Material.matchMaterial("WHITE_" + inputName));
        newBedRecipe("yellow_", inputName, Material.YELLOW_DYE, Material.matchMaterial("YELLOW_" + inputName));
    }


    public void newClearRecipe(String inputName) {
        NamespacedKey key = Key.get("clear_" + inputName.toLowerCase() + "_universal");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.matchMaterial(inputName)));


        List<Material> materials = new ArrayList<>();
        materials.add(Material.matchMaterial("BLACK_" + inputName));
        materials.add(Material.matchMaterial("BLUE_" + inputName));
        materials.add(Material.matchMaterial("BROWN_" + inputName));
        materials.add(Material.matchMaterial("CYAN_" + inputName));
        materials.add(Material.matchMaterial("GRAY_" + inputName));
        materials.add(Material.matchMaterial("GREEN_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_BLUE_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_GRAY_" + inputName));
        materials.add(Material.matchMaterial("LIME_" + inputName));
        materials.add(Material.matchMaterial("MAGENTA_" + inputName));
        materials.add(Material.matchMaterial("ORANGE_" + inputName));
        materials.add(Material.matchMaterial("PINK_" + inputName));
        materials.add(Material.matchMaterial("PURPLE_" + inputName));
        materials.add(Material.matchMaterial("RED_" + inputName));
        materials.add(Material.matchMaterial("WHITE_" + inputName));
        materials.add(Material.matchMaterial("YELLOW_" + inputName));

        recipe.addIngredient(new RecipeChoice.MaterialChoice(materials));
        recipe.addIngredient(Material.ICE);

        addRecipe(recipe);
    }

    public void newClearStainedRecipe(String inputName) {
        NamespacedKey key = Key.get("clear_" + inputName.toLowerCase() + "_universal");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.matchMaterial(inputName)));


        List<Material> materials = new ArrayList<>();
        materials.add(Material.matchMaterial("BLACK_STAINED_" + inputName));
        materials.add(Material.matchMaterial("BLUE_STAINED_" + inputName));
        materials.add(Material.matchMaterial("BROWN_STAINED_" + inputName));
        materials.add(Material.matchMaterial("CYAN_STAINED_" + inputName));
        materials.add(Material.matchMaterial("GRAY_STAINED_" + inputName));
        materials.add(Material.matchMaterial("GREEN_STAINED_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_BLUE_STAINED_" + inputName));
        materials.add(Material.matchMaterial("LIGHT_GRAY_STAINED_" + inputName));
        materials.add(Material.matchMaterial("LIME_STAINED_" + inputName));
        materials.add(Material.matchMaterial("MAGENTA_STAINED_" + inputName));
        materials.add(Material.matchMaterial("ORANGE_STAINED_" + inputName));
        materials.add(Material.matchMaterial("PINK_STAINED_" + inputName));
        materials.add(Material.matchMaterial("PURPLE_STAINED_" + inputName));
        materials.add(Material.matchMaterial("RED_STAINED_" + inputName));
        materials.add(Material.matchMaterial("WHITE_STAINED_" + inputName));
        materials.add(Material.matchMaterial("YELLOW_STAINED_" + inputName));

        recipe.addIngredient(new RecipeChoice.MaterialChoice(materials));
        recipe.addIngredient(Material.ICE);

        addRecipe(recipe);
    }
}
