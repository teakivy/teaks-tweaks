package me.teakivy.teakstweaks.packs.survival.unlockallrecipes;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.Iterator;

public class UnlockRecipes extends BasePack {
    ArrayList<NamespacedKey> recipes = new ArrayList<>();

    public UnlockRecipes() {
        super("unlock-all-recipes", PackType.SURVIVAL, Material.KNOWLEDGE_BOOK);

        recipes.clear();
        Iterator<Recipe> it = teaksTweaks.getServer().recipeIterator();
        while (it.hasNext()) {
            Recipe rec = it.next();
            if (rec instanceof Keyed) {
                recipes.add(((Keyed) rec).getKey());
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (NamespacedKey recipe : recipes) {
            if (!player.hasDiscoveredRecipe(recipe) && !recipe.getNamespace().equals("bukkit")) player.discoverRecipe(recipe);
        }
    }

}
