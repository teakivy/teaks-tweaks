package me.teakivy.vanillatweaks.Packs.Survival.Graves;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GraveEvents implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) throws IOException {
        Entity entity = event.getRightClicked();
        if (!entity.getScoreboardTags().contains("vt_grave")) return;

        if (ownsGrave(entity, event.getPlayer())) {
            PersistentDataContainer data = entity.getPersistentDataContainer();
            if (data.has(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_items"), PersistentDataType.STRING)) {
                String ownerItems = data.get(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_items"), PersistentDataType.STRING);
                if (ownerItems != null) {
                    for (ItemStack item : new GraveCreator().deserializeItems(ownerItems, entity.getScoreboardTags().contains("vt_base64"))) {
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
        } else {
            if (main.getConfig().getBoolean("packs.graves.allow-robbing") || holdingKey(event.getPlayer())) {
                if (holdingKey(event.getPlayer()) && !main.getConfig().getBoolean("packs.graves.allow-robbing") && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    event.getPlayer().getInventory().remove(getGraveKey());
                }
                PersistentDataContainer data = entity.getPersistentDataContainer();
                if (data.has(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_items"), PersistentDataType.STRING)) {
                    String ownerItems = data.get(new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_items"), PersistentDataType.STRING);
                    if (ownerItems != null) {
                        for (ItemStack item : new GraveCreator().deserializeItems(ownerItems, entity.getScoreboardTags().contains("vt_base64"))) {
                            Item itemE = entity.getWorld().dropItem(entity.getLocation().add(0, 2, 0), item);
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
            } else {
                event.getPlayer().sendMessage(MessageHandler.getMessage("pack.graves.cant-rob-grave"));
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getScoreboardTags().contains("vt_grave")) event.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(InventoryPickupItemEvent event) {
        if (!(event.getInventory().getHolder() instanceof Player)) return;
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
            String lastGrave = MessageHandler.getMessage("pack.graves.last-grave")
                    .replace("%grave_location_x%", String.valueOf((int) Math.floor(loc.getX())))
                    .replace("%grave_location_y%", String.valueOf((int) Math.floor(loc.getY())))
                    .replace("%grave_location_z%", String.valueOf((int) Math.floor(loc.getZ())))
                    .replace("%grave_location_world%", loc.getWorld().getName());
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

    public boolean holdingKey(Player player) {
        ItemStack graveKey = getGraveKey();
        return player.getInventory().getItem(EquipmentSlot.HAND).isSimilar(graveKey);
    }

    public static ItemStack getGraveKey() {
        ItemStack graveKey = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta keyMeta = graveKey.getItemMeta();
        keyMeta.setDisplayName(ChatColor.GOLD + "Grave Key");
        graveKey.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
        keyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Right-click any grave with this key to open it.");
        keyMeta.setLore(lore);
        graveKey.setItemMeta(keyMeta);
        return graveKey;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (holdingKey(event.getPlayer())) event.setCancelled(true);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
