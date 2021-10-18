package me.teakivy.teakstweaks.CraftingTweaks.Recipes;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class DropperToDispenser {

    static Main main = Main.getPlugin(Main.class);

    public static void registerRecipes() {
        NamespacedKey bowRecipeKey = new NamespacedKey(main, "dispenser_vt_bow");

        ShapelessRecipe bowRecipe = new ShapelessRecipe(bowRecipeKey, new ItemStack(Material.DISPENSER));

        bowRecipe.addIngredient(Material.DROPPER);
        bowRecipe.addIngredient(Material.BOW);

        Bukkit.addRecipe(bowRecipe);



        NamespacedKey craftRecipeKey = new NamespacedKey(main, "dispenser_vt_craft");

        ShapedRecipe craftRecipe = new ShapedRecipe(craftRecipeKey, new ItemStack(Material.DISPENSER));
        craftRecipe.shape(" ls", "los", " ls");

        craftRecipe.setIngredient('o', Material.DROPPER);
        craftRecipe.setIngredient('l', Material.STICK);
        craftRecipe.setIngredient('s', Material.STRING);

        Bukkit.addRecipe(craftRecipe);
    }

}
