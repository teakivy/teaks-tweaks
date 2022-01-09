package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

public class RottenFleshToLeather {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        FurnaceRecipe recipe = new FurnaceRecipe(new NamespacedKey(main, "rotten_flesh_to_leather"), new ItemStack(Material.LEATHER), Material.ROTTEN_FLESH, 10, 100);
        Bukkit.addRecipe(recipe);
    }

}
