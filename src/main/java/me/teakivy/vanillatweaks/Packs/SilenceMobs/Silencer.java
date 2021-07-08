package me.teakivy.vanillatweaks.Packs.SilenceMobs;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.UUID;

public class Silencer implements Listener {

    static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        if (!main.getConfig().getBoolean("packs.silence-mobs.enabled")) return;
        Entity entity = event.getRightClicked();
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
            @Override
            public void run() {
                if (entity == null) return;
                Entity entity1 = getEntityByUniqueId(entity.getUniqueId());
                if (entity1 == null) return;
                if (entity1.getCustomName() == null) return;
                if (entity1.getCustomName().equalsIgnoreCase("silence me") || entity1.getCustomName().equalsIgnoreCase("Silence me") || entity1.getCustomName().equalsIgnoreCase("Silence Me") || entity1.getCustomName().equalsIgnoreCase("silence_me")) {

                    entity.setSilent(true);

                    entity.setCustomName("silenced");
                }
            }
        }, 10L);
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
}


