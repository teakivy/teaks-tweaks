package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class MorePackedMud extends AbstractCraftingTweak {

     public MorePackedMud() {
         super(TTCraftingTweak.MORE_PACKED_MUD, Material.PACKED_MUD);
     }

    @Override
    public void registerRecipes() {
        Bukkit.removeRecipe(NamespacedKey.minecraft("packed_mud"));
        ShapelessRecipe recipe = new ShapelessRecipe(Key.get("packed_mud_more"), new ItemStack(Material.PACKED_MUD, 2));
        recipe.addIngredient(Material.MUD);
        recipe.addIngredient(Material.WHEAT);
        addRecipe(recipe);
    }
}
