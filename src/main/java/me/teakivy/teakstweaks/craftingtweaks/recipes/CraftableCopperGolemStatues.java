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
        registerGolemRecipe(Material.COPPER_BLOCK, Material.COPPER_GOLEM_STATUE);
        registerGolemRecipe(Material.EXPOSED_COPPER, Material.EXPOSED_COPPER_GOLEM_STATUE);
        registerGolemRecipe(Material.WEATHERED_COPPER, Material.WEATHERED_COPPER_GOLEM_STATUE);
        registerGolemRecipe(Material.OXIDIZED_COPPER, Material.OXIDIZED_COPPER_GOLEM_STATUE);

        registerGolemRecipe(Material.WAXED_COPPER_BLOCK, Material.WAXED_COPPER_GOLEM_STATUE);
        registerGolemRecipe(Material.WAXED_EXPOSED_COPPER, Material.WAXED_EXPOSED_COPPER_GOLEM_STATUE);
        registerGolemRecipe(Material.WAXED_WEATHERED_COPPER, Material.WAXED_WEATHERED_COPPER_GOLEM_STATUE);
        registerGolemRecipe(Material.WAXED_OXIDIZED_COPPER, Material.WAXED_OXIDIZED_COPPER_GOLEM_STATUE);
    }

    public void registerGolemRecipe(Material input, Material output) {
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("craftable_copper_golem_statues_" + input.toString().toLowerCase()), new ItemStack(output));
        recipe.addIngredient(Material.CARVED_PUMPKIN);
        recipe.addIngredient(input);
        addRecipe(recipe);
    }
}
