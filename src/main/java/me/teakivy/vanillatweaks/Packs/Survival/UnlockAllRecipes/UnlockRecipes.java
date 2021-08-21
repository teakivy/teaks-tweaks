package me.teakivy.vanillatweaks.Packs.Survival.UnlockAllRecipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.Iterator;

public class UnlockRecipes implements Listener {

    Main main = Main.getPlugin(Main.class);

    ArrayList<NamespacedKey> recipes = new ArrayList<>();

    public void register() {
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

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
