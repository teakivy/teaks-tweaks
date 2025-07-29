package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

import java.util.Map;

public class CheaperArmorTrimTemplates extends AbstractCraftingTweak {

    public CheaperArmorTrimTemplates() {
        super(TTCraftingTweak.CHEAPER_ARMOR_TRIM_TEMPLATES, Material.HOST_ARMOR_TRIM_SMITHING_TEMPLATE);
    }

    @Override
    public void registerRecipes() {
        newArmorTrimRecipe("HOST");
        newArmorTrimRecipe("SENTRY");
        newArmorTrimRecipe("VEX");
        newArmorTrimRecipe("WILD");
        newArmorTrimRecipe("COAST");
        newArmorTrimRecipe("DUNE");
        newArmorTrimRecipe("WAYFINDER");
        newArmorTrimRecipe("RAISER");
        newArmorTrimRecipe("SHAPER");
        newArmorTrimRecipe("WARD");
        newArmorTrimRecipe("SILENCE");
        newArmorTrimRecipe("TIDE");
        newArmorTrimRecipe("SNOUT");
        newArmorTrimRecipe("RIB");
        newArmorTrimRecipe("EYE");
        newArmorTrimRecipe("SPIRE");
        newArmorTrimRecipe("FLOW");
        newArmorTrimRecipe("BOLT");
    }

    public void newArmorTrimRecipe(String type) {
        String upperCaseKey = type.toUpperCase() + "_ARMOR_TRIM_SMITHING_TEMPLATE";
        String lowerCaseKey = upperCaseKey.toLowerCase();

        try {
            NamespacedKey armorTrimKeyOriginal = NamespacedKey.minecraft(lowerCaseKey);

            Recipe recipe = Bukkit.getRecipe(armorTrimKeyOriginal);
            if(recipe instanceof ShapedRecipe originalRecipe) {
                Map<Character, RecipeChoice> originalChoiceMap = originalRecipe.getChoiceMap();

                RecipeChoice uniqueMaterial = originalChoiceMap.get('e');
                RecipeChoice armorTrimTemplate = originalChoiceMap.get('b');
                RecipeChoice diamond = originalChoiceMap.get('a');

                String[] newShape = {"MTM", "MDM", "MMM"};

                // Create the new recipe.
                NamespacedKey armorTrimKeyReplacement = Key.get(lowerCaseKey);
                ShapedRecipe newRecipe = new ShapedRecipe(armorTrimKeyReplacement, originalRecipe.getResult());
                newRecipe.shape(newShape);
                newRecipe.setIngredient('M', uniqueMaterial);
                newRecipe.setIngredient('T', armorTrimTemplate);
                newRecipe.setIngredient('D', diamond);
                Bukkit.removeRecipe(armorTrimKeyOriginal);
                addRecipe(newRecipe);
            }
        }catch(Exception e){
            Logger.error(e.toString());
            Logger.error(e.getMessage());
        }
    }


}
