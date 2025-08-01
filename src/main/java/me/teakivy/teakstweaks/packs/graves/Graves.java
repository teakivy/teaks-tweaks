package me.teakivy.teakstweaks.packs.graves;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.XPUtils;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import me.teakivy.teakstweaks.utils.customitems.TItem;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
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

public class Graves extends BasePack {

    public Graves() {
        super(TTPack.GRAVES, Material.STONE_BRICK_WALL);
    }

    @Override
    public List<CustomItem> registerItems() {
        List<CustomItem> items = new ArrayList<>();

        ItemStack graveKey = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta keyMeta = graveKey.getItemMeta();
        MiniMessage mm = MiniMessage.miniMessage();
        keyMeta.displayName(mm.deserialize(TranslationManager.getString(Config.getLanguage(), "graves.key.item_name")));
        graveKey.addUnsafeEnchantment(Enchantment.CHANNELING, 1);
        keyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        List<Component> lore = new ArrayList<>();
        lore.add(mm.deserialize(TranslationManager.getString(Config.getLanguage(), "graves.key.item_lore")));
        keyMeta.lore(lore);
        graveKey.setItemMeta(keyMeta);

        CustomItem key = new CustomItem("grave_key", graveKey);
        items.add(key);

        return items;
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) throws IOException {
        Entity entity = event.getRightClicked();
        if (!entity.getScoreboardTags().contains("grave")) return;

        if (ownsGrave(entity, event.getPlayer())) {
            event.setCancelled(true);
            entity.remove();
            PersistentDataContainer data = entity.getPersistentDataContainer();
            if (data.has(Key.get("grave_owner_items"), PersistentDataType.STRING)) {
                String ownerItems = data.get(Key.get("grave_owner_items"), PersistentDataType.STRING);
                if (ownerItems != null) {
                    for (ItemStack item : GraveCreator.deserializeItems(ownerItems)) {
                        Item itemE = entity.getWorld().dropItem(entity.getLocation().add(0, 2, 0), item);
                        if (event.getPlayer().isSneaking()) {
                            PersistentDataContainer iData = itemE.getPersistentDataContainer();
                            iData.set(Key.get("grave_item_owner_uuid"), PersistentDataType.STRING, event.getPlayer().getUniqueId().toString());
                        }
                        itemE.setPickupDelay(0);
                        itemE.setVelocity(new Vector(0, 0, 0));
                    }
                }
            }
            if (data.has(Key.get("grave_owner_xp"), PersistentDataType.INTEGER)) {
                if (data.get(Key.get("grave_owner_xp"), PersistentDataType.INTEGER) > 0) {
                    ExperienceOrb orb = (ExperienceOrb) entity.getWorld().spawnEntity(entity.getLocation().add(0, 2, 0), EntityType.EXPERIENCE_ORB);
                    orb.setExperience(data.get(Key.get("grave_owner_xp"), PersistentDataType.INTEGER));
                }
            }
            return;
        } else {
            event.setCancelled(true);
            if (getConfig().getBoolean("allow-robbing") || holdingKey(event.getPlayer())) {
                entity.remove();
                if (holdingKey(event.getPlayer()) && !getConfig().getBoolean("allow-robbing") && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    event.getPlayer().getInventory().remove(TItem.GRAVE_KEY.getItem());
                }
                PersistentDataContainer data = entity.getPersistentDataContainer();
                if (data.has(Key.get("grave_owner_items"), PersistentDataType.STRING)) {
                    String ownerItems = data.get(Key.get("grave_owner_items"), PersistentDataType.STRING);
                    if (ownerItems != null) {
                        for (ItemStack item : GraveCreator.deserializeItems(ownerItems)) {
                            Item itemE = entity.getWorld().dropItem(entity.getLocation().add(0, 2, 0), item);
                            itemE.setPickupDelay(0);
                            itemE.setVelocity(new Vector(0, 0, 0));
                        }
                    }
                }
                if (data.has(Key.get("grave_owner_xp"), PersistentDataType.INTEGER)) {
                    if (data.get(Key.get("grave_owner_xp"), PersistentDataType.INTEGER) > 0) {
                        ExperienceOrb orb = (ExperienceOrb) entity.getWorld().spawnEntity(entity.getLocation().add(0, 2, 0), EntityType.EXPERIENCE_ORB);
                        orb.setExperience(data.get(Key.get("grave_owner_xp"), PersistentDataType.INTEGER));
                    }
                }
                return;
            } else {
                event.getPlayer().sendMessage(getText("cant_rob_grave"));
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getScoreboardTags().contains("grave")) event.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(InventoryPickupItemEvent event) {
        if (!(event.getInventory().getHolder() instanceof Player player)) return;
        if (event.getItem().getPersistentDataContainer().has(Key.get("grave_item_owner_uuid"), PersistentDataType.STRING)) {
            UUID playerUUID = player.getUniqueId();
            UUID itemUUID = UUID.fromString(event.getItem().getPersistentDataContainer().get(Key.get("grave_item_owner_uuid"), PersistentDataType.STRING));
            if (playerUUID != itemUUID) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws IOException {
        if (!getConfig().getBoolean("generate-in-lava")) {
            if (event.getEntity().getLocation().getBlock().getType() == Material.LAVA || event.getEntity().getLocation().getBlock().getType() == Material.LAVA_CAULDRON) {
                return;
            }
        }
        if (event.getEntity().getGameMode() == GameMode.SPECTATOR) return;

        Player player = event.getEntity();

        if (player.getLevel() < getConfig().getInt("level-threshold")) return;

        if (!getConfig().getBoolean("creative-drops")) {
            if (event.getEntity().getGameMode() == GameMode.CREATIVE) {
                event.getEntity().getInventory().clear();
                event.getEntity().setExp(0);
                event.getEntity().setLevel(0);
            }
        }

        if (!getConfig().getBoolean("allow-empty-graves")) {
            if (event.getDrops().isEmpty()) return;
        }

        if (!getConfig().getBoolean("creative-graves")) {
            if (event.getEntity().getGameMode() == GameMode.CREATIVE) return;
        }

        Location loc = GraveCreator.findGraveLocation(player.getLocation());
        if (loc == null) return;
        if (!GraveCreator.getAirTypes().contains(loc.getBlock().getType())) {
            loc.setY(loc.getWorld().getMaxHeight());
            loc = GraveCreator.findGraveLocation(loc);
        }
        int xp = 0;
        if (getConfig().getBoolean("hold-xp")) {
            xp = getConfig().getBoolean("keep-all-xp") ?
                    XPUtils.getPlayerExp(player) :
                    event.getDroppedExp();
        }

        GraveCreator.createGrave(loc, player, xp);

        if (getConfig().getBoolean("locatable")) {
            Component lastGrave = getText("last_grave",
                            insert("x", (int) Math.floor(loc.getX())),
                            insert("y", (int) Math.floor(loc.getY())),
                            insert("z", (int) Math.floor(loc.getZ())),
                            insert("world", loc.getWorld().getName()));
            player.sendMessage(lastGrave);
            PersistentDataContainer playerData = player.getPersistentDataContainer();
            playerData.set(Key.get("graves_last"), PersistentDataType.STRING, MiniMessage.miniMessage().serialize(lastGrave));
        }
        if (getConfig().getBoolean("hold-xp")) {
            event.setDroppedExp(0);
        }
        event.getDrops().clear();
    }

    public boolean ownsGrave(Entity entity, Player player) {
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if (data.has(Key.get("grave_owner_uuid"), PersistentDataType.STRING)) {
            String ownerUUID = data.get(Key.get("grave_owner_uuid"), PersistentDataType.STRING);
            return player.getUniqueId().toString().equals(ownerUUID);
        } else {
            return false;
        }
    }

    public boolean holdingKey(Player player) {
        ItemStack graveKey = TItem.GRAVE_KEY.getItem();
        return player.getInventory().getItem(EquipmentSlot.HAND).isSimilar(graveKey);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (holdingKey(event.getPlayer())) event.setCancelled(true);
    }

}
