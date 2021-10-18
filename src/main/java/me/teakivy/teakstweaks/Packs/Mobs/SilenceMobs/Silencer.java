package me.teakivy.teakstweaks.Packs.Mobs.SilenceMobs;

import me.teakivy.teakstweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Silencer implements Listener {

    static Main main = Main.getPlugin(Main.class);

    ArrayList<EntityType> minecartTypes = new ArrayList<>();

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        if (!main.getConfig().getBoolean("packs.silence-mobs.enabled")) return;
        if (minecartTypes.isEmpty()) {
            minecartTypes.add(EntityType.MINECART);
            minecartTypes.add(EntityType.MINECART_FURNACE);
            minecartTypes.add(EntityType.MINECART_CHEST);
            minecartTypes.add(EntityType.MINECART_COMMAND);
            minecartTypes.add(EntityType.MINECART_TNT);
            minecartTypes.add(EntityType.MINECART_HOPPER);
            minecartTypes.add(EntityType.MINECART_MOB_SPAWNER);
        }
        Entity entity = event.getRightClicked();
        if (minecartTypes.contains(entity.getType())) {
            if (!main.getConfig().getBoolean("packs.silence-mobs.allow-minecarts")) return;
            ItemStack nametag = event.getPlayer().getInventory().getItem(event.getHand());
            if (nametag.getType() != Material.NAME_TAG) return;
            if (!nametag.hasItemMeta()) return;
            if (!Objects.requireNonNull(nametag.getItemMeta()).getDisplayName().equalsIgnoreCase("silence me") && !Objects.requireNonNull(nametag.getItemMeta()).getDisplayName().equalsIgnoreCase("silence_me")) return;
            entity.setSilent(true);
            entity.setCustomName("silenced");
            event.setCancelled(true);
            if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                event.getPlayer().getInventory().getItem(event.getHand()).setAmount(nametag.getAmount() - 1);
            }
            return;
        }
        try {
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                Entity entity1 = getEntityByUniqueId(entity.getUniqueId());
                if (entity1 == null) return;
                if (entity1.getCustomName() == null) return;
                if (entity1.getCustomName().equalsIgnoreCase("silence me") || entity1.getCustomName().equalsIgnoreCase("Silence me") || entity1.getCustomName().equalsIgnoreCase("Silence Me") || entity1.getCustomName().equalsIgnoreCase("silence_me")) {
                    entity.setSilent(true);
                    entity.setCustomName("silenced");
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


