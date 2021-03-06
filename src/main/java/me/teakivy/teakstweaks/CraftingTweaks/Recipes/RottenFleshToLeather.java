package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.CraftingTweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class RottenFleshToLeather extends AbstractRecipe {

    public RottenFleshToLeather() {
        super("Rotten Flesh To Leather");
    }

    @Override
    public void registerRecipes() {
        FurnaceRecipe recipe = new FurnaceRecipe(new NamespacedKey(main, "rotten_flesh_to_leather"), new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH, 10, 100);
        Bukkit.addRecipe(recipe);
    }

}
