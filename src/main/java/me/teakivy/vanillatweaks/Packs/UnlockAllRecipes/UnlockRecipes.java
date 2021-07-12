package me.teakivy.vanillatweaks.Packs.UnlockAllRecipes;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Keyed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

public class UnlockRecipes implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Iterator<Recipe> it = main.getServer().recipeIterator();
        while (it.hasNext()) {
            Recipe rec = it.next();
            if (rec instanceof Keyed) {
                event.getPlayer().discoverRecipe(((Keyed) rec).getKey());
            }
        }
    }

}
