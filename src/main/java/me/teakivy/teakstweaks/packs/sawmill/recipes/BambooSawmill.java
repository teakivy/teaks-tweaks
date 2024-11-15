package me.teakivy.teakstweaks.packs.sawmill.recipes;

import me.teakivy.teakstweaks.packs.sawmill.BaseSawmillRecipe;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Material;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.List;

public class BambooSawmill extends BaseSawmillRecipe {
    protected final Material block;
    protected final Material strippedBlock;
    protected final Material planks;
    protected final Material mosaic;
    protected final Material stairs;
    protected final Material mosaicStairs;
    protected final Material slabs;
    protected final Material mosaicSlabs;
    protected final Material fence;
    protected final Material fenceGate;
    protected final Material door;
    protected final Material trapdoor;
    protected final Material pressurePlate;
    protected final Material button;
    protected final Material sign;

    public BambooSawmill() {
        this.block = Material.BAMBOO_BLOCK;
        this.strippedBlock = Material.STRIPPED_BAMBOO_BLOCK;
        this.planks = Material.BAMBOO_PLANKS;
        this.mosaic = Material.BAMBOO_MOSAIC;
        this.stairs = Material.BAMBOO_STAIRS;
        this.mosaicStairs = Material.BAMBOO_MOSAIC_STAIRS;
        this.slabs = Material.BAMBOO_SLAB;
        this.mosaicSlabs = Material.BAMBOO_MOSAIC_SLAB;
        this.fence = Material.BAMBOO_FENCE;
        this.fenceGate = Material.BAMBOO_FENCE_GATE;
        this.door = Material.BAMBOO_DOOR;
        this.trapdoor = Material.BAMBOO_TRAPDOOR;
        this.pressurePlate = Material.BAMBOO_PRESSURE_PLATE;
        this.button = Material.BAMBOO_BUTTON;
        this.sign = Material.BAMBOO_SIGN;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.addAll(getBlockRelatedRecipes(block));
        recipes.addAll(getBlockRelatedRecipes(strippedBlock));
        recipes.addAll(getPlankRelatedRecipes(planks));
        recipes.addAll(getPlankRelatedRecipes(mosaic));

        return recipes;
    }

    public List<Recipe> getBlockRelatedRecipes(Material material) {
        List<Recipe> recipes = new ArrayList<>();

        if (this.block != material) recipes.add(getRecipe(material, this.block, 1));
        if (this.strippedBlock != material) recipes.add(getRecipe(material, strippedBlock, 1));
        recipes.add(getRecipe(material, this.planks, 4));
        recipes.add(getRecipe(material, mosaic, 4));
        recipes.add(getRecipe(material, stairs, 5));
        recipes.add(getRecipe(material, mosaicStairs, 5));
        recipes.add(getRecipe(material, slabs, 8));
        recipes.add(getRecipe(material, mosaicSlabs, 8));
        recipes.add(getRecipe(material, fence, 2));
        recipes.add(getRecipe(material, fenceGate, 2));
        recipes.add(getRecipe(material, door, 2));
        int trapdoorAmount = 2;
        if (Config.isCraftingTweakEnabled("more-trapdoors")) trapdoorAmount = 8;
        recipes.add(getRecipe(material, trapdoor, trapdoorAmount));
        recipes.add(getRecipe(material, pressurePlate, 4));
        recipes.add(getRecipe(material, button, 4));
        recipes.add(getRecipe(material, sign, 2));

        return recipes;
    }

    public List<Recipe> getPlankRelatedRecipes(Material material) {
        List<Recipe> recipes = new ArrayList<>();

        if (material != this.planks) recipes.add(getRecipe(material, planks, 1));
        if (material != this.mosaic) recipes.add(getRecipe(material, mosaic, 1));
        recipes.add(getRecipe(material, stairs, 1));
        recipes.add(getRecipe(material, mosaicStairs, 1));
        recipes.add(getRecipe(material, slabs, 2));
        recipes.add(getRecipe(material, mosaicSlabs, 2));
        int trapdoorAmount = 3;
        if (Config.isCraftingTweakEnabled("more-trapdoors")) {
            recipes.add(getRecipe(material, trapdoor, trapdoorAmount));
        }
        recipes.add(getRecipe(material, pressurePlate, 1));
        recipes.add(getRecipe(material, button, 1));

        return recipes;
    }
}
