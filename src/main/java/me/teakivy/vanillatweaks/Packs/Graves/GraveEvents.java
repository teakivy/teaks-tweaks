package me.teakivy.vanillatweaks.Packs.Graves;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.UUID;

public class GraveEvents implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (!entity.getScoreboardTags().contains("vt_grave")) return;

        if (ownsGrave(entity, event.getPlayer())) {
            PersistentDataContainer data = entity.getPersistentDataContainer();
            if (data.has(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_items"), PersistentDataType.STRING)) {
                String ownerItems = data.get(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_items"), PersistentDataType.STRING);
                if (ownerItems != null) {
                    for (ItemStack item : new GraveCreator().deserializeItems(ownerItems)) {
                        Item itemE = entity.getWorld().dropItem(entity.getLocation().add(0, 2, 0), item);
                        if (event.getPlayer().isSneaking()) {
                            PersistentDataContainer iData = itemE.getPersistentDataContainer();
                            iData.set(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_item_owner_uuid"), PersistentDataType.STRING, event.getPlayer().getUniqueId().toString());
                        }
                        itemE.setPickupDelay(0);
                        itemE.setVelocity(new Vector(0, 0, 0));
                    }
                }
            }
            if (data.has(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_xp"), PersistentDataType.INTEGER)) {
                if (data.get(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_xp"), PersistentDataType.INTEGER) > 0) {
                    ExperienceOrb orb = (ExperienceOrb) entity.getWorld().spawnEntity(entity.getLocation().add(0, 2, 0), EntityType.EXPERIENCE_ORB);
                    orb.setExperience(data.get(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_xp"), PersistentDataType.INTEGER));
                }
            }
            event.setCancelled(true);
            entity.remove();
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(InventoryPickupItemEvent event) {
        Player player = (Player) event.getInventory().getHolder();
        if (event.getItem().getPersistentDataContainer().has(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_item_owner_uuid"), PersistentDataType.STRING)) {
            UUID playerUUID = player.getUniqueId();
            UUID itemUUID = UUID.fromString(event.getItem().getPersistentDataContainer().get(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_item_owner_uuid"), PersistentDataType.STRING));
            if (playerUUID != itemUUID) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        GraveCreator gc = new GraveCreator();
        Location loc = gc.findGraveLocation(player.getLocation());
        int xp = 0;
        if (main.getConfig().getBoolean("packs.graves.hold-xp")) {
            xp = event.getDroppedExp();
        }

        gc.createGrave(loc, player, xp);

        if (main.getConfig().getBoolean("packs.graves.locatable")) {
            String lastGrave = ChatColor.GOLD + "Your last grave is at " + ChatColor.YELLOW + "(" + (int) Math.floor(loc.getX()) + ", " + (int) Math.floor(loc.getY()) + ", " + (int) Math.floor(loc.getZ()) + ")" + ChatColor.GOLD + " in " + loc.getWorld().getName() + ".";
            player.sendMessage(lastGrave);
            PersistentDataContainer playerData = player.getPersistentDataContainer();
            playerData.set(new NamespacedKey(main, "vt_graves_last"), PersistentDataType.STRING, lastGrave);
        }
        if (main.getConfig().getBoolean("packs.graves.hold-xp")) {
            event.setDroppedExp(0);
        }
        event.getDrops().clear();
    }

    public boolean ownsGrave(Entity entity, Player player) {
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if (data.has(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_uuid"), PersistentDataType.STRING)) {
            String ownerUUID = data.get(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_uuid"), PersistentDataType.STRING);
            return player.getUniqueId().toString().equals(ownerUUID);
        } else {
            return false;
        }
    }

}
