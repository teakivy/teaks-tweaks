package me.teakivy.vanillatweaks.Packs.ChunkLoaders;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.ArrayList;
import java.util.List;

public class Loader implements Listener {

    Main main = Main.getPlugin(Main.class);

    List<Location> loaded = new ArrayList<>();

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!main.getConfig().getBoolean("config.dev-mode")) return;
        Item item = event.getItemDrop();
        if (item.getItemStack().getType() == Material.NETHER_STAR) {
            if (item.getLocation().add(0, -1, 0).getBlock().getType() == Material.LODESTONE) {
                item.getLocation().getChunk().setForceLoaded(true);
                loaded.add(item.getLocation().add(0, -1, 0).getBlock().getLocation());
                System.out.println("loaded: " + loaded);
            }
        }
    }

    @EventHandler
    public void onBread(BlockBreakEvent event) {
        if (!main.getConfig().getBoolean("config.dev-mode")) return;
        if (event.getBlock().getType() == Material.LODESTONE) {
            if (loaded.contains(event.getBlock().getLocation())) {
                loaded.remove(event.getBlock().getLocation());
                event.getBlock().getLocation().getChunk().setForceLoaded(false);
                System.out.println("loaded: " + loaded);
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
