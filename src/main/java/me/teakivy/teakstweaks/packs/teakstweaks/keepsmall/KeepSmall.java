package me.teakivy.teakstweaks.packs.teakstweaks.keepsmall;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.Objects;
import java.util.UUID;

public class KeepSmall extends BasePack {

    public KeepSmall() {
        super("Keep Small", "keep-small", PackType.TEAKSTWEAKS, Material.STONE_BUTTON, "Keep a mob in its child form (if it has one)");
    }

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            Entity entity = event.getRightClicked();

            if (entity.customName() == null) return;
            String name = Objects.requireNonNull(entity.customName()).toString().toLowerCase();

            if (name.contains("keep") && name.contains("small")) {

                if (entity instanceof Ageable) {
                    Ageable ageable = (Ageable) entity;
                    ageable.setAge(-Integer.MAX_VALUE);

                    ageable.customName(Component.text("Smallified"));
                }

                if (entity.getType() == EntityType.FROG) {
                    entity.remove();

                    entity = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.TADPOLE);

                    Ageable ageable = (Ageable) entity;
                    ageable.setAge(-Integer.MAX_VALUE);

                    ageable.customName(Component.text("Smallified"));
                }
            }

            if (name.contains("grow")) {
                if (entity instanceof Ageable) {
                    Ageable ageable = (Ageable) entity;
                    ageable.setAge(Integer.MAX_VALUE);

                    ageable.customName(Component.text("Grown"));
                }

                if (entity.getType() == EntityType.TADPOLE) {
                    entity.remove();

                    entity = entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FROG);

                    Ageable ageable = (Ageable) entity;
                    ageable.setAge(Integer.MAX_VALUE);

                    ageable.customName(Component.text("Grown"));
                }
            }
        }, 10L);
    }

//    @EventHandler
//    public void onSilenceOld(PlayerInteractAtEntityEvent event) {
//        Entity entity = event.getRightClicked();
//        try {
//            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
//                Entity entity1 = getEntityByUniqueId(entity.getUniqueId());
//                if (entity1 == null) return;
//                if (entity1.getCustomName() == null) return;
//                if (entity1.getCustomName().equalsIgnoreCase("keep small") || entity1.getCustomName().equalsIgnoreCase("keep_small")) {
//                    if (!getConfig().getBoolean("smallify")) return;
//                    if (entity1 instanceof Ageable) {
//                        Ageable ageable = (Ageable) entity1;
//                        ageable.setAge(-Integer.MAX_VALUE);
//                    }
//                    entity.setCustomName("Smallified");
//                }
//                if (entity1.getCustomName().equalsIgnoreCase("grow")) {
//                    if (!getConfig().getBoolean("grow")) return;
//                    if (entity1 instanceof Ageable) {
//                        Ageable ageable = (Ageable) entity1;
//                        ageable.setAge(1);
//                    }
//                    entity.setCustomName("Grown");
//                }
//            }, 10L);
//        } catch (NoClassDefFoundError err) {
//            return;
//        }
//    }
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


