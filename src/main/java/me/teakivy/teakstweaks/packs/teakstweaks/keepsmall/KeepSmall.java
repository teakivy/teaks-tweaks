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
        super("keep-small", PackType.TEAKSTWEAKS, Material.STONE_BUTTON);
    }

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        if (!hasPermission(event.getPlayer())) return;
        Entity entity = event.getRightClicked();
        try {
            Bukkit.getScheduler().scheduleSyncDelayedTask(teaksTweaks, () -> {
                Entity entity1 = getEntityByUniqueId(entity.getUniqueId());
                if (entity1 == null) return;
                if (entity1.getCustomName() == null) return;
                if (entity1.getCustomName().toLowerCase().replace("_", " ").equalsIgnoreCase(getString("smallify.activation_name"))) {
                    if (!getConfig().getBoolean("smallify")) return;
                    if (entity1 instanceof Ageable) {
                        Ageable ageable = (Ageable) entity1;
                        ageable.setAge(-Integer.MAX_VALUE);
                    }
                    entity.setCustomName(getString("smallify.small_name"));
                }
                if (entity1.getCustomName().equalsIgnoreCase(getString("grow.activation_name"))) {
                    if (!getConfig().getBoolean("grow")) return;
                    if (entity1 instanceof Ageable) {
                        Ageable ageable = (Ageable) entity1;
                        ageable.setAge(1);
                    }
                    entity.setCustomName(getString("grow.grown_name"));
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


