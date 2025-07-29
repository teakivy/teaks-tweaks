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
        newDyeingRecipe("black_", inputName, Material.BLACK_DYE, Material.valueOf("BLACK_" + inputName));
        newDyeingRecipe("blue_", inputName, Material.BLUE_DYE, Material.valueOf("BLUE_" + inputName));
        newDyeingRecipe("brown_", inputName, Material.BROWN_DYE, Material.valueOf("BROWN_" + inputName));
        newDyeingRecipe("cyan_", inputName, Material.CYAN_DYE, Material.valueOf("CYAN_" + inputName));
        newDyeingRecipe("gray_", inputName, Material.GRAY_DYE, Material.valueOf("GRAY_" + inputName));
        newDyeingRecipe("green_", inputName, Material.GREEN_DYE, Material.valueOf("GREEN_" + inputName));
        newDyeingRecipe("light_blue_", inputName, Material.LIGHT_BLUE_DYE, Material.valueOf("LIGHT_BLUE_" + inputName));
        newDyeingRecipe("light_gray_", inputName, Material.LIGHT_GRAY_DYE, Material.valueOf("LIGHT_GRAY_" + inputName));
        newDyeingRecipe("lime_", inputName, Material.LIME_DYE, Material.valueOf("LIME_" + inputName));
        newDyeingRecipe("magenta_", inputName, Material.MAGENTA_DYE, Material.valueOf("MAGENTA_" + inputName));
        newDyeingRecipe("orange_", inputName, Material.ORANGE_DYE, Material.valueOf("ORANGE_" + inputName));
        newDyeingRecipe("pink_", inputName, Material.PINK_DYE, Material.valueOf("PINK_" + inputName));
        newDyeingRecipe("purple_", inputName, Material.PURPLE_DYE, Material.valueOf("PURPLE_" + inputName));
        newDyeingRecipe("red_", inputName, Material.RED_DYE, Material.valueOf("RED_" + inputName));
        newDyeingRecipe("white_", inputName, Material.WHITE_DYE, Material.valueOf("WHITE_" + inputName));
        newDyeingRecipe("yellow_", inputName, Material.YELLOW_DYE, Material.valueOf("YELLOW_" + inputName));
    }

    public void newDyeingRecipe(String colorType, String inputName, Material inputDye, Material output) {
        NamespacedKey key = Key.get(colorType + inputName.toLowerCase() + "_universal");

        ShapedRecipe recipe = new ShapedRecipe(key, new ItemStack(output, 8));

        List<Material> materials = new ArrayList<>();
        materials.add(Material.valueOf("BLACK_" + inputName));
        materials.add(Material.valueOf("BLUE_" + inputName));
        materials.add(Material.valueOf("BROWN_" + inputName));
        materials.add(Material.valueOf("CYAN_" + inputName));
        materials.add(Material.valueOf("GRAY_" + inputName));
        materials.add(Material.valueOf("GREEN_" + inputName));
        materials.add(Material.valueOf("LIGHT_BLUE_" + inputName));
        materials.add(Material.valueOf("LIGHT_GRAY_" + inputName));
        materials.add(Material.valueOf("LIME_" + inputName));
        materials.add(Material.valueOf("MAGENTA_" + inputName));
        materials.add(Material.valueOf("ORANGE_" + inputName));
        materials.add(Material.valueOf("PINK_" + inputName));
        materials.add(Material.valueOf("PURPLE_" + inputName));
        materials.add(Material.valueOf("RED_" + inputName));
        materials.add(Material.valueOf("WHITE_" + inputName));
        materials.add(Material.valueOf("YELLOW_" + inputName));

        recipe.shape("###", "#o#", "###");
        recipe.setIngredient('#', new RecipeChoice.MaterialChoice(materials));
        recipe.setIngredient('o', inputDye);

        addRecipe(recipe);

    }

    public void newBedRecipe(String colorType, String inputName, Material inputDye, Material output) {
        NamespacedKey key = Key.get(colorType + inputName.toLowerCase() + "_universal");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(output));


        List<Material> materials = new ArrayList<>();
        materials.add(Material.valueOf("BLACK_" + inputName));
        materials.add(Material.valueOf("BLUE_" + inputName));
        materials.add(Material.valueOf("BROWN_" + inputName));
        materials.add(Material.valueOf("CYAN_" + inputName));
        materials.add(Material.valueOf("GRAY_" + inputName));
        materials.add(Material.valueOf("GREEN_" + inputName));
        materials.add(Material.valueOf("LIGHT_BLUE_" + inputName));
        materials.add(Material.valueOf("LIGHT_GRAY_" + inputName));
        materials.add(Material.valueOf("LIME_" + inputName));
        materials.add(Material.valueOf("MAGENTA_" + inputName));
        materials.add(Material.valueOf("ORANGE_" + inputName));
        materials.add(Material.valueOf("PINK_" + inputName));
        materials.add(Material.valueOf("PURPLE_" + inputName));
        materials.add(Material.valueOf("RED_" + inputName));
        materials.add(Material.valueOf("WHITE_" + inputName));
        materials.add(Material.valueOf("YELLOW_" + inputName));

        recipe.addIngredient(new RecipeChoice.MaterialChoice(materials));
        recipe.addIngredient(inputDye);

        addRecipe(recipe);

    }

    public void registerBedRecipeTypes(String inputName) {
        newBedRecipe("black_", inputName, Material.BLACK_DYE, Material.valueOf("BLACK_" + inputName));
        newBedRecipe("blue_", inputName, Material.BLUE_DYE, Material.valueOf("BLUE_" + inputName));
        newBedRecipe("brown_", inputName, Material.BROWN_DYE, Material.valueOf("BROWN_" + inputName));
        newBedRecipe("cyan_", inputName, Material.CYAN_DYE, Material.valueOf("CYAN_" + inputName));
        newBedRecipe("gray_", inputName, Material.GRAY_DYE, Material.valueOf("GRAY_" + inputName));
        newBedRecipe("green_", inputName, Material.GREEN_DYE, Material.valueOf("GREEN_" + inputName));
        newBedRecipe("light_blue_", inputName, Material.LIGHT_BLUE_DYE, Material.valueOf("LIGHT_BLUE_" + inputName));
        newBedRecipe("light_gray_", inputName, Material.LIGHT_GRAY_DYE, Material.valueOf("LIGHT_GRAY_" + inputName));
        newBedRecipe("lime_", inputName, Material.LIME_DYE, Material.valueOf("LIME_" + inputName));
        newBedRecipe("magenta_", inputName, Material.MAGENTA_DYE, Material.valueOf("MAGENTA_" + inputName));
        newBedRecipe("orange_", inputName, Material.ORANGE_DYE, Material.valueOf("ORANGE_" + inputName));
        newBedRecipe("pink_", inputName, Material.PINK_DYE, Material.valueOf("PINK_" + inputName));
        newBedRecipe("purple_", inputName, Material.PURPLE_DYE, Material.valueOf("PURPLE_" + inputName));
        newBedRecipe("red_", inputName, Material.RED_DYE, Material.valueOf("RED_" + inputName));
        newBedRecipe("white_", inputName, Material.WHITE_DYE, Material.valueOf("WHITE_" + inputName));
        newBedRecipe("yellow_", inputName, Material.YELLOW_DYE, Material.valueOf("YELLOW_" + inputName));
    }


    public void newClearRecipe(String inputName) {
        NamespacedKey key = Key.get("clear_" + inputName.toLowerCase() + "_universal");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.valueOf(inputName)));


        List<Material> materials = new ArrayList<>();
        materials.add(Material.valueOf("BLACK_" + inputName));
        materials.add(Material.valueOf("BLUE_" + inputName));
        materials.add(Material.valueOf("BROWN_" + inputName));
        materials.add(Material.valueOf("CYAN_" + inputName));
        materials.add(Material.valueOf("GRAY_" + inputName));
        materials.add(Material.valueOf("GREEN_" + inputName));
        materials.add(Material.valueOf("LIGHT_BLUE_" + inputName));
        materials.add(Material.valueOf("LIGHT_GRAY_" + inputName));
        materials.add(Material.valueOf("LIME_" + inputName));
        materials.add(Material.valueOf("MAGENTA_" + inputName));
        materials.add(Material.valueOf("ORANGE_" + inputName));
        materials.add(Material.valueOf("PINK_" + inputName));
        materials.add(Material.valueOf("PURPLE_" + inputName));
        materials.add(Material.valueOf("RED_" + inputName));
        materials.add(Material.valueOf("WHITE_" + inputName));
        materials.add(Material.valueOf("YELLOW_" + inputName));

        recipe.addIngredient(new RecipeChoice.MaterialChoice(materials));
        recipe.addIngredient(Material.ICE);

        addRecipe(recipe);
    }

    public void newClearStainedRecipe(String inputName) {
        NamespacedKey key = Key.get("clear_" + inputName.toLowerCase() + "_universal");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.valueOf(inputName)));


        List<Material> materials = new ArrayList<>();
        materials.add(Material.valueOf("BLACK_STAINED_" + inputName));
        materials.add(Material.valueOf("BLUE_STAINED_" + inputName));
        materials.add(Material.valueOf("BROWN_STAINED_" + inputName));
        materials.add(Material.valueOf("CYAN_STAINED_" + inputName));
        materials.add(Material.valueOf("GRAY_STAINED_" + inputName));
        materials.add(Material.valueOf("GREEN_STAINED_" + inputName));
        materials.add(Material.valueOf("LIGHT_BLUE_STAINED_" + inputName));
        materials.add(Material.valueOf("LIGHT_GRAY_STAINED_" + inputName));
        materials.add(Material.valueOf("LIME_STAINED_" + inputName));
        materials.add(Material.valueOf("MAGENTA_STAINED_" + inputName));
        materials.add(Material.valueOf("ORANGE_STAINED_" + inputName));
        materials.add(Material.valueOf("PINK_STAINED_" + inputName));
        materials.add(Material.valueOf("PURPLE_STAINED_" + inputName));
        materials.add(Material.valueOf("RED_STAINED_" + inputName));
        materials.add(Material.valueOf("WHITE_STAINED_" + inputName));
        materials.add(Material.valueOf("YELLOW_STAINED_" + inputName));

        recipe.addIngredient(new RecipeChoice.MaterialChoice(materials));
        recipe.addIngredient(Material.ICE);

        addRecipe(recipe);
    }
}
