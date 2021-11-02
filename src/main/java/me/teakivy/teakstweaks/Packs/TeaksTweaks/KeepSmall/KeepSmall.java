package me.teakivy.teakstweaks.Packs.TeaksTweaks.KeepSmall;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.UUID;

public class KeepSmall implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        try {
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                Entity entity1 = getEntityByUniqueId(entity.getUniqueId());
                if (entity1 == null) return;
                if (entity1.getCustomName() == null) return;
                if (entity1.getCustomName().equalsIgnoreCase("keep small") || entity1.getCustomName().equalsIgnoreCase("keep_small")) {
                    if (!main.getConfig().getBoolean("packs.keep-small.smallify")) return;
                    if (entity1 instanceof Ageable) {
                        Ageable ageable = (Ageable) entity1;
                        ageable.setAge(-Integer.MAX_VALUE);
                    }
                    entity.setCustomName("Smallified");
                }
                if (entity1.getCustomName().equalsIgnoreCase("grow")) {
                    if (!main.getConfig().getBoolean("packs.keep-small.grow")) return;
                    if (entity1 instanceof Ageable) {
                        Ageable ageable = (Ageable) entity1;
                        ageable.setAge(1);
                    }
                    entity.setCustomName("Grown");
                }
            }, 10L);
        } catch (NoClassDefFoundError err) {
            return;
        }
    }
    public Entity getEntityByUniqueId(UUID uniqueId) {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getUniqueId().equals(uniqueId))
                    return entity;
            }
        }

        return null;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}


