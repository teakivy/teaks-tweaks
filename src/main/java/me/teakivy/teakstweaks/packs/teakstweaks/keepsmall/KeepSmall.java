package me.teakivy.teakstweaks.packs.teakstweaks.keepsmall;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.UUID;

public class KeepSmall extends BasePack {

    public KeepSmall() {
        super("Keep Small", "keep-small", PackType.TEAKSTWEAKS, Material.STONE_BUTTON, "Keep a mob in its child form (if it has one)");
    }

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        try {
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                Entity entity1 = getEntityByUniqueId(entity.getUniqueId());
                if (entity1 == null) return;
                if (entity1.getCustomName() == null) return;
                if (entity1.getCustomName().equalsIgnoreCase("keep small") || entity1.getCustomName().equalsIgnoreCase("keep_small")) {
                    if (!getConfig().getBoolean("smallify")) return;
                    if (entity1 instanceof Ageable) {
                        Ageable ageable = (Ageable) entity1;
                        ageable.setAge(-Integer.MAX_VALUE);
                    }
                    entity.setCustomName("Smallified");
                }
                if (entity1.getCustomName().equalsIgnoreCase("grow")) {
                    if (!getConfig().getBoolean("grow")) return;
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
}


