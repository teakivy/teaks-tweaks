package me.teakivy.vanillatweaks.Packs.DoubleShulkerShells;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DoubleShulkers implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void shulkerKill(EntityDeathEvent event) {
        if (!main.getConfig().getBoolean("packs.double-shulker-shells.enabled")) return;
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.SHULKER) {
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.SHULKER_SHELL, 2));
        }
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
