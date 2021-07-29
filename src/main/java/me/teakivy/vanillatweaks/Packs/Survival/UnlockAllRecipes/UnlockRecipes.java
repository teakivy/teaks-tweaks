package me.teakivy.vanillatweaks.Packs.Survival.UnlockAllRecipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Keyed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

public class UnlockRecipes implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!main.getConfig().getBoolean("packs.unlock-all-recipes.enabled")) return;
        Iterator<Recipe> it = main.getServer().recipeIterator();
        while (it.hasNext()) {
            Recipe rec = it.next();
            if (rec instanceof Keyed) {
                event.getPlayer().discoverRecipe(((Keyed) rec).getKey());
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
