package me.teakivy.vanillatweaks.Packs.Items.PlayerHeadDrops;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class HeadDrop implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!main.getConfig().getBoolean("packs.player-head-drops.enabled")) return;
        Player player = event.getEntity();
        if (player.isDead()) {
            if (player.getKiller() != null) {
                Player killer = player.getKiller();
                event.getDrops().add(getHead(player, killer.getName()));
            }
        }
    }

    public static ItemStack getHead(Player player, String killer) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        if (main.getConfig().getBoolean("packs.player-head-drops.display-killer"))
            lore.add("Killed by " + killer);
        skull.setLore(lore);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);
        return item;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}
