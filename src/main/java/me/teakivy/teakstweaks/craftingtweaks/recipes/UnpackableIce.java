package me.teakivy.teakstweaks.craftingtweaks.recipes;

import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class UnpackableIce extends AbstractRecipe {

    public UnpackableIce() {
        super("unpackable-ice", Material.PACKED_ICE);
    }

    @Override
    public void registerRecipes() {
        NamespacedKey iceKey = Key.get("ice_unpackables");
        ShapelessRecipe iceRecipe = new ShapelessRecipe(iceKey, new ItemStack(Material.ICE, 9));
        iceRecipe.addIngredient(Material.PACKED_ICE);

        Bukkit.addRecipe(iceRecipe);


        NamespacedKey packedIceKey = Key.get("packed_ice_unpackables");
        ShapelessRecipe packedIceRecipe = new ShapelessRecipe(packedIceKey, new ItemStack(Material.PACKED_ICE, 9));
        packedIceRecipe.addIngredient(Material.BLUE_ICE);

        Bukkit.addRecipe(packedIceRecipe);
    }
}
