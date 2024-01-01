package me.teakivy.teakstweaks.packs.mobs.silencemobs;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Silencer extends BasePack {

    public Silencer() {
        super("silence-mobs", PackType.MOBS, Material.SCULK_SHRIEKER);

        if (minecartTypes.isEmpty()) {
            minecartTypes.add(EntityType.MINECART);
            minecartTypes.add(EntityType.MINECART_FURNACE);
            minecartTypes.add(EntityType.MINECART_CHEST);
            minecartTypes.add(EntityType.MINECART_COMMAND);
            minecartTypes.add(EntityType.MINECART_TNT);
            minecartTypes.add(EntityType.MINECART_HOPPER);
            minecartTypes.add(EntityType.MINECART_MOB_SPAWNER);
        }
    }

    ArrayList<EntityType> minecartTypes = new ArrayList<>();

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        if (!checkPermission(event.getPlayer())) return;
        Entity entity = event.getRightClicked();

        if (minecartTypes.contains(entity.getType())) {
            if (!getConfig().getBoolean("allow-minecarts")) return;
            ItemStack nametag = event.getPlayer().getInventory().getItem(event.getHand());
            if (nametag.getType() != Material.NAME_TAG) return;
            if (!nametag.hasItemMeta()) return;
            if (!MiniMessage.miniMessage().serialize(Objects.requireNonNull(nametag.getItemMeta()).displayName())
                    .replaceAll("_", " ")
                    .replaceAll("-", " ")
                    .trim()
                    .equalsIgnoreCase(getString("activation_name")))
                return;
            entity.setSilent(true);
            entity.customName(getText("silenced_name"));
            event.setCancelled(true);

            if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                event.getPlayer().getInventory().getItem(event.getHand()).setAmount(nametag.getAmount() - 1);
            }
            return;
        }

        try {
            Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
                Entity entity1 = getEntityByUniqueId(entity.getUniqueId());
                if (entity1 == null) return;
                if (entity1.customName() == null) return;
                if (MiniMessage.miniMessage().serialize(entity1.customName()).replaceAll("_", " ")
                        .replaceAll("-", " ")
                        .trim()
                        .equalsIgnoreCase(getString("activation_name"))) {
                    entity.setSilent(true);
                    entity.customName(getText("silenced_name"));
                }
            }, 10L);
        } catch (NoClassDefFoundError ignored) {
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


