package me.teakivy.teakstweaks.Packs.Survival.UnlockAllRecipes;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.Keyed;
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
        super("Unlock All Recipes", "unlock-all-recipes");

        recipes.clear();
        Iterator<Recipe> it = main.getServer().recipeIterator();
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
            player.discoverRecipe(recipe);
        }
    }

}
