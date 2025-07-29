package me.teakivy.teakstweaks.packs.silencemobs;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
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

public class SilenceMobs extends BasePack {

    public SilenceMobs() {
        super(TTPack.SILENCE_MOBS, Material.SCULK_SHRIEKER);

        if (minecartTypes.isEmpty()) {
            minecartTypes.add(EntityType.MINECART);
            minecartTypes.add(EntityType.FURNACE_MINECART);
            minecartTypes.add(EntityType.CHEST_MINECART);
            minecartTypes.add(EntityType.COMMAND_BLOCK_MINECART);
            minecartTypes.add(EntityType.TNT_MINECART);
            minecartTypes.add(EntityType.HOPPER_MINECART);
            minecartTypes.add(EntityType.SPAWNER_MINECART);
        }
    }

    ArrayList<EntityType> minecartTypes = new ArrayList<>();

    @EventHandler
    public void onSilence(PlayerInteractAtEntityEvent event) {
        if (!Permission.SILENCE_MOBS.check(event.getPlayer())) return;
        Entity entity = event.getRightClicked();

        if (minecartTypes.contains(entity.getType())) {
            if (!getConfig().getBoolean("allow-minecarts")) return;
            ItemStack nametag = event.getPlayer().getInventory().getItem(event.getHand());
            if (nametag.getType() != Material.NAME_TAG) return;
            if (!nametag.hasItemMeta()) return;
            if (!Objects.requireNonNull(nametag.getItemMeta()).getDisplayName()
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
                if (entity1.getCustomName().replaceAll("_", " ")
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


