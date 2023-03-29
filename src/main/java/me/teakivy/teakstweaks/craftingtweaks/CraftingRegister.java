package me.teakivy.teakstweaks.craftingtweaks;

import me.teakivy.teakstweaks.craftingtweaks.recipes.*;

import java.util.ArrayList;
import java.util.List;

public class CraftingRegister {

    private static final List<AbstractRecipe> enabledRecipes = new ArrayList<>();

    public static void registerAll() {
        List<AbstractRecipe> recipes = new ArrayList<>();
        recipes.add(new BackToBlocks());
        recipes.add(new DoubleSlabs());
        recipes.add(new DropperToDispenser());
        recipes.add(new RottenFleshToLeather());
        recipes.add(new CharcoalToBlackDye());
        recipes.add(new CoalToBlackDye());
        recipes.add(new SandstoneDyeing());
        recipes.add(new UniversalDyeing());
        recipes.add(new StraightToShapeless());
        recipes.add(new BlackstoneCobblestone());
        recipes.add(new PowderToGlass());
        recipes.add(new MoreTrapdoors());
        recipes.add(new MoreBark());
        recipes.add(new MoreStairs());
        recipes.add(new MoreBricks());
        recipes.add(new CraftableGravel());
        recipes.add(new CraftableHorseArmor());
        recipes.add(new CraftableCoral2x2());
        recipes.add(new CraftableCoral3x3());
        recipes.add(new CraftableEnchantedGoldenApples());
        recipes.add(new CraftableNameTags());
        recipes.add(new CraftableBundlesRabbitHide());
        recipes.add(new CraftableBundlesLeather());
        recipes.add(new CraftableBlackstone());
        recipes.add(new CraftableGildedBlackstone());
        recipes.add(new CraftableSporeBlossoms());
        recipes.add(new CraftableSmallDripleaves());
        recipes.add(new CraftableSculkSensors());
        recipes.add(new UnpackableIce());
        recipes.add(new UnpackableNetherWart());
        recipes.add(new UnpackableWool());
        recipes.add(new UnpackableQuartz());
        recipes.add(new LogChests());
        recipes.add(new SmeltableRawOreBlocks());
        recipes.add(new MorePackedMud());
        try {
            for (AbstractRecipe recipe : recipes) {
                recipe.register();
            }
        } catch (Exception e) {
        }
    }

    public static void addEnabledRecipe(AbstractRecipe recipe) {
        enabledRecipes.add(recipe);
    }

    public static List<AbstractRecipe> getEnabledRecipes() {
        return enabledRecipes;
    }

}
