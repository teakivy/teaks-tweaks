package me.teakivy.teakstweaks.packs.sawmill;

import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.StonecuttingRecipe;

import java.util.ArrayList;
import java.util.List;

public class BaseSawmillRecipe {
    protected final Material log;
    protected final Material wood;
    protected final Material strippedLog;
    protected final Material strippedWood;
    protected final Material planks;
    protected final Material stairs;
    protected final Material slabs;
    protected final Material fence;
    protected final Material fenceGate;
    protected final Material door;
    protected final Material trapdoor;
    protected final Material pressurePlate;
    protected final Material button;
    protected final Material sign;

    public BaseSawmillRecipe() {
        this.log = null;
        this.wood = null;
        this.strippedLog = null;
        this.strippedWood = null;
        this.planks = null;
        this.stairs = null;
        this.slabs = null;
        this.fence = null;
        this.fenceGate = null;
        this.door = null;
        this.trapdoor = null;
        this.pressurePlate = null;
        this.button = null;
        this.sign = null;
    }

    public BaseSawmillRecipe(Material log, Material wood, Material strippedLog, Material strippedWood, Material planks, Material stairs, Material slabs, Material fence, Material fenceGate, Material door, Material trapdoor, Material pressurePlate, Material button, Material sign) {
        this.log = log;
        this.wood = wood;
        this.strippedLog = strippedLog;
        this.strippedWood = strippedWood;
        this.planks = planks;
        this.stairs = stairs;
        this.slabs = slabs;
        this.fence = fence;
        this.fenceGate = fenceGate;
        this.door = door;
        this.trapdoor = trapdoor;
        this.pressurePlate = pressurePlate;
        this.button = button;
        this.sign = sign;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.addAll(getLogRelatedRecipes(log));
        recipes.addAll(getLogRelatedRecipes(strippedLog));
        recipes.addAll(getLogRelatedRecipes(wood));
        recipes.addAll(getLogRelatedRecipes(strippedWood));
        recipes.addAll(getPlankRecipes());

        return recipes;
    }

    public List<Recipe> getLogRelatedRecipes(Material material) {
        List<Recipe> recipes = new ArrayList<>();

        if (this.log != material) recipes.add(getRecipe(material, this.log, 1));
        if (this.strippedLog != material) recipes.add(getRecipe(material, strippedLog, 1));
        if (this.wood != material) recipes.add(getRecipe(material, wood, 1));
        if (this.strippedWood != material) recipes.add(getRecipe(material, strippedWood, 1));
        recipes.add(getRecipe(material, planks, 4));
        recipes.add(getRecipe(material, stairs, 5));
        recipes.add(getRecipe(material, slabs, 8));
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

    public List<Recipe> getPlankRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(getRecipe(planks, stairs, 1));
        recipes.add(getRecipe(planks, slabs, 2));
        int trapdoorAmount = 3;
        if (Config.isCraftingTweakEnabled("more-trapdoors")) {
            recipes.add(getRecipe(planks, trapdoor, trapdoorAmount));
        }
        recipes.add(getRecipe(planks, pressurePlate, 1));
        recipes.add(getRecipe(planks, button, 1));

        return recipes;
    }

    public Recipe getRecipe(Material input, Material output, int amount) {
        return new StonecuttingRecipe(Key.get((output.toString() + "_" + input.toString()).toLowerCase()), new ItemStack(output, amount), input);
    }
}
