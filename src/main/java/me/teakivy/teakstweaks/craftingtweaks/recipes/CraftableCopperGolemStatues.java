package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class CraftableCopperGolemStatues extends AbstractCraftingTweak {

     public CraftableCopperGolemStatues() {
         super(TTCraftingTweak.CRAFTABLE_COPPER_GOLEM_STATUES, Material.COPPER_GOLEM_STATUE);
     }

    @Override
    public void registerRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("craftable_copper_golem_statues"), new ItemStack(Material.COPPER_GOLEM_STATUE));
        recipe.addIngredient(Material.CARVED_PUMPKIN);
        recipe.addIngredient(Material.COPPER_INGOT);
        addRecipe(recipe);
    }
}
