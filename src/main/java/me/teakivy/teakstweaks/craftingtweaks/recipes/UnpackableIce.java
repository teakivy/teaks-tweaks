package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class UnpackableIce extends AbstractCraftingTweak {

    public UnpackableIce() {
        super(TTCraftingTweak.UNPACKABLE_ICE, Material.PACKED_ICE);
    }

    @Override
    public void registerRecipes() {
        ShapelessRecipe iceRecipe = new ShapelessRecipe(Key.get("ice_unpackables"),
                new ItemStack(Material.ICE, 9));
        iceRecipe.addIngredient(Material.PACKED_ICE);
        addRecipe(iceRecipe);

        ShapelessRecipe packedIceRecipe = new ShapelessRecipe(Key.get("packed_ice_unpackables"),
                new ItemStack(Material.PACKED_ICE, 9));
        packedIceRecipe.addIngredient(Material.BLUE_ICE);
        addRecipe(packedIceRecipe);
    }
}
