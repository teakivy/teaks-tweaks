package me.teakivy.teakstweaks.Packs.Mobs.DragonDrops;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DragonDrops implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void dragonKill(EntityDeathEvent event) {
        if (!main.getConfig().getBoolean("packs.dragon-drops.enabled")) return;
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDER_DRAGON) {
            event.getDrops().clear();
            if (main.getConfig().getBoolean("packs.dragon-drops.drop-egg")) {
                event.getDrops().add(new ItemStack(Material.DRAGON_EGG));
            }
            if (main.getConfig().getBoolean("packs.dragon-drops.drop-elytra")) {
                event.getDrops().add(new ItemStack(Material.ELYTRA));
            }
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}
