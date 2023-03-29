package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class MorePackedMud extends AbstractRecipe {

     public MorePackedMud() {
         super("More Packed Mud", "more-packed-mud", Material.PACKED_MUD, "Craft 2 Packed Mud instead of 1.");
     }

    @Override
    public void registerRecipes() {
        NamespacedKey key = new NamespacedKey(main, "packed_mud_more");
        ShapelessRecipe recipe = new ShapelessRecipe(key, new ItemStack(Material.PACKED_MUD, 2));
        recipe.addIngredient(Material.MUD);
        recipe.addIngredient(Material.WHEAT);

        Bukkit.addRecipe(recipe);
    }
}
