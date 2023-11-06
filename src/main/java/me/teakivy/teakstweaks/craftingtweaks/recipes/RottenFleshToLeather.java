package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class RottenFleshToLeather extends AbstractCraftingTweak {

    public RottenFleshToLeather() {
        super("rotten-flesh-to-leather", Material.LEATHER);
    }

    @Override
    public void registerRecipes() {
        FurnaceRecipe recipe = new FurnaceRecipe(Key.get("rotten_flesh_to_leather"), new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH, 10, 100);
        Bukkit.addRecipe(recipe);
    }
}
