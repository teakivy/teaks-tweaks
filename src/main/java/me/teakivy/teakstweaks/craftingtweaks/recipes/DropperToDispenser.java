package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class DropperToDispenser extends AbstractRecipe {

    public DropperToDispenser() {
        super("Dropper To Dispenser", "dropper-to-dispenser", Material.DISPENSER, "Allows you to convert a Dropper to a Dispenser using a Bow, or by using String and Sticks.");
    }

    @Override
    public void registerRecipes() {
        NamespacedKey bowRecipeKey = new NamespacedKey(main, "dispenser_bow");

        ShapelessRecipe bowRecipe = new ShapelessRecipe(bowRecipeKey, new ItemStack(Material.DISPENSER));

        bowRecipe.addIngredient(Material.DROPPER);
        bowRecipe.addIngredient(Material.BOW);

        Bukkit.addRecipe(bowRecipe);



        NamespacedKey craftRecipeKey = new NamespacedKey(main, "dispenser_craft");

        ShapedRecipe craftRecipe = new ShapedRecipe(craftRecipeKey, new ItemStack(Material.DISPENSER));
        craftRecipe.shape(" ls", "los", " ls");

        craftRecipe.setIngredient('o', Material.DROPPER);
        craftRecipe.setIngredient('l', Material.STICK);
        craftRecipe.setIngredient('s', Material.STRING);

        Bukkit.addRecipe(craftRecipe);
    }

}
