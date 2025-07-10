package me.teakivy.teakstweaks.packs.chunkloaders;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Loader extends BasePack {

    public Loader() {
        super("chunk-loaders", Material.LODESTONE);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!Permission.CHUNK_LOADERS.check(event.getPlayer())) return;

        Item item = event.getItemDrop();
        if (!item.getItemStack().getType().toString().equalsIgnoreCase(getConfig().getString("cost"))) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                Block block = item.getLocation().add(0, -0.5, 0).getBlock();
                if (block.getType() != Material.LODESTONE) return;
                if (block.getChunk().isForceLoaded()) {
                    this.cancel();
                    event.getPlayer().sendMessage(getText("already_loaded"));
                    return;
                }

                if (event.getItemDrop().isDead() || event.getItemDrop().getItemStack().getAmount() < 1) {
                    this.cancel();
                    return;
                }

                block.getLocation().getChunk().setForceLoaded(true);
                Marker marker = (Marker) block.getWorld().spawnEntity(block.getLocation().add(.5, 1, .5), EntityType.MARKER);
                marker.addScoreboardTag("loaded");

                if (Config.getBoolean("packs.chunk-loaders.show-particles")) {
                    marker.getWorld().spawnParticle(Particle.FLAME, item.getLocation(), 100, 0, 0, 0, .5);
                }

                ItemStack newItem = item.getItemStack().clone();
                newItem.setAmount(newItem.getAmount() - 1);
                item.setItemStack(newItem);

                event.getPlayer().sendMessage(getText("loaded"));

                if (event.getItemDrop().isDead() || event.getItemDrop().getItemStack().getAmount() < 1) this.cancel();
            }
        }.runTaskTimer(getPlugin(), 1L, 20L);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.LODESTONE) return;
        Block block = event.getBlock();

        for (Entity nearbyEntity : block.getWorld().getNearbyEntities(block.getLocation().add(.5, 1, .5), .1, .1, .1)) {
            if (!nearbyEntity.getScoreboardTags().contains("loaded") || nearbyEntity.getType() != EntityType.MARKER) continue;

            block.getLocation().getChunk().setForceLoaded(false);
            nearbyEntity.remove();
            return;
        }
    }

}
