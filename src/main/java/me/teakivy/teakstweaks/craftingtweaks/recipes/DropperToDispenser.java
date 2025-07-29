package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class DropperToDispenser extends AbstractCraftingTweak {

    public DropperToDispenser() {
        super(TTCraftingTweak.DROPPER_TO_DISPENSER, Material.DISPENSER);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe bowRecipe = new ShapelessRecipe(Key.get("dispenser_bow"), new ItemStack(Material.DISPENSER));
        bowRecipe.addIngredient(Material.DROPPER);
        bowRecipe.addIngredient(Material.BOW);
        addRecipe(bowRecipe);

        ShapedRecipe craftRecipe = new ShapedRecipe(Key.get("dispenser_craft"), new ItemStack(Material.DISPENSER));
        craftRecipe.shape(" ls", "los", " ls");
        craftRecipe.setIngredient('o', Material.DROPPER);
        craftRecipe.setIngredient('l', Material.STICK);
        craftRecipe.setIngredient('s', Material.STRING);
        addRecipe(craftRecipe);
    }
}
