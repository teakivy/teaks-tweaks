package me.teakivy.teakstweaks.packs.experimental.chunkloaders;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Loader extends BasePack {

    public Loader() {
        super("chunk-loaders", PackType.EXPERIMENTAL, Material.LODESTONE);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!event.getPlayer().hasPermission(permission)) {
            return;
        }

        Item item = event.getItemDrop();
        if (item.getItemStack().getType() == Material.valueOf(config.getString("cost"))) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Block block = item.getLocation().add(0, -1, 0).getBlock();
                    if (block.getType() == Material.LODESTONE) {
                        if (block.getChunk().isForceLoaded()) {
                            this.cancel();
                            return;
                        }
                        block.getLocation().getChunk().setForceLoaded(true);
                        AreaEffectCloud marker = (AreaEffectCloud) block.getWorld().spawnEntity(block.getLocation().add(.5, 1, .5), EntityType.AREA_EFFECT_CLOUD);

                        marker.setDuration(Integer.MAX_VALUE);
                        marker.setParticle(Particle.FLAME);
                        marker.setWaitTime(0);
                        marker.setColor(Color.WHITE);
                        marker.setRadius(.001F);
                        marker.addScoreboardTag("loaded");

                        if (Config.getBoolean("packs.chunk-loaders.show-particles")) {
                            marker.getWorld().spawnParticle(Particle.FLAME, item.getLocation(), 100, 0, 0, 0, .5);
                        }
                        item.remove();
                    }
                    if (event.getItemDrop().isDead() || event.getItemDrop().getItemStack().getAmount() != 1) this.cancel();
                }
            }.runTaskTimer(teaksTweaks, 1L, 20L);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.LODESTONE) {
            Block block = event.getBlock();
            for (Entity nearbyEntity : block.getWorld().getNearbyEntities(block.getLocation().add(.5, 1, .5), .1, .1, .1)) {
                if (nearbyEntity.getScoreboardTags().contains("loaded") && nearbyEntity.getType() == EntityType.AREA_EFFECT_CLOUD) {
                    block.getLocation().getChunk().setForceLoaded(false);
                    nearbyEntity.remove();
                    return;
                }
            }
        }
    }

}
