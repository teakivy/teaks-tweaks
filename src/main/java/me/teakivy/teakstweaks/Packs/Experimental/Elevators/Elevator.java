package me.teakivy.teakstweaks.Packs.Experimental.Elevators;

import com.google.common.collect.Sets;
import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Elevator extends BasePack {

    public Elevator() {
        super("Elevators", "elevators");
    }

    static List<Material> elevatorMaterials = new ArrayList<>();
    private Set<UUID> prevPlayersOnGround = Sets.newHashSet();

    @Override
    public void init() {
        super.init();
        for (String block : getConfig().getStringList("elevator-blocks")) {
            elevatorMaterials.add(Material.valueOf(block));
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.valueOf(getConfig().getString("activator"))) {
            if (event.getItemDrop().getItemStack().getAmount() != 1) return;
            if (isElevator(event.getItemDrop().getLocation().add(0, -1, 0).getBlock())) return;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (elevatorMaterials.contains(event.getItemDrop().getLocation().add(0, -1, 0).getBlock().getType()) && event.getItemDrop().getItemStack().getAmount() == 1) {
                        event.getItemDrop().remove();
                        createElevator(event.getItemDrop().getLocation().add(0, -1, 0));
                    }
                    if (event.getItemDrop().isDead()) this.cancel();
                }
            }.runTaskTimer(main, 0, 20L);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!elevatorMaterials.contains(event.getBlock().getType())) return;
        if (isElevator(block)) {
            for (Entity nearbyEntity : block.getWorld().getNearbyEntities(block.getLocation().add(.5, 1, .5), .4, .8, .4)) {
                if (block.getY() + 1 == nearbyEntity.getLocation().getBlockY()) {
                    if (nearbyEntity.getScoreboardTags().contains("vt_elevator") && nearbyEntity.getType() == EntityType.AREA_EFFECT_CLOUD) {
                        nearbyEntity.remove();
                        event.getBlock().getDrops().add(new ItemStack(Material.ENDER_PEARL));
                        return;
                    }
                }
            }
        }
    }


    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()) {
            Block standingBlock = player.getLocation().add(0, -1, 0).getBlock();
            if (isElevator(standingBlock)) {
                Block elevatorSpot = findNextElevatorDown(standingBlock, standingBlock.getWorld().getMinHeight());

                if (elevatorSpot != null) {
                    player.teleport(new Location(player.getWorld(), player.getLocation().getX(), elevatorSpot.getY() + 1, player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                    if (getConfig().getBoolean("elevators.play-sound")) {
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    }
                    player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation().add(0, 1, 0), 20, -.5, -.5, -.5, 4);
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if (player.getVelocity().getY() > 0 && player.getLocation().add(0, -.3, 0).getBlock().getType() != Material.AIR) {
            Block standingBlock = player.getLocation().add(0, -1, 0).getBlock();
            if (isElevator(player.getLocation().add(0, -1, 0).getBlock())) {
                Block elevatorSpot = findNextElevatorUp(standingBlock, standingBlock.getWorld().getMaxHeight());

                if (elevatorSpot != null) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                        player.teleport(new Location(player.getWorld(), player.getLocation().getX(), elevatorSpot.getY() + 1, player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                        if (getConfig().getBoolean("play-sound")) {
                            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        }
                        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation().add(0, 1, 0), 20, -.5, -.5, -.5, 4);
                    }, 1L);
                }

            }
        }
    }

    private void createElevator(Location loc) {
        Block block = loc.getBlock();

        AreaEffectCloud marker = (AreaEffectCloud) block.getWorld().spawnEntity(block.getLocation().add(.5, 1, .5), EntityType.AREA_EFFECT_CLOUD);

        marker.setDuration(Integer.MAX_VALUE);
        marker.setParticle(Particle.SUSPENDED);
        marker.setWaitTime(0);
        marker.setColor(Color.WHITE);
        marker.setRadius(.001F);
        marker.addScoreboardTag("vt_elevator");

        loc.getWorld().spawnParticle(Particle.PORTAL, block.getLocation().add(.5, 1, .5), 200, -.5, -.5, -.5, -1);
    }

    private boolean isElevator(Block block) {
        for (Entity nearbyEntity : block.getWorld().getNearbyEntities(block.getLocation().add(.5, 1, .5), .4, .4, .4)) {
            if (nearbyEntity.getScoreboardTags().contains("vt_elevator") && nearbyEntity.getType() == EntityType.AREA_EFFECT_CLOUD) {
                return true;
            }
        }
        return false;
    }


    private Block findNextElevatorDown(Block eBlock, int minY) {
        Block next = null;
        for (int i = minY; i < (int) eBlock.getLocation().getY(); i++) {
            Block block = eBlock.getLocation().getWorld().getBlockAt((int) eBlock.getLocation().getX(), i, (int) eBlock.getLocation().getZ());
            if ((!getConfig().getBoolean("require-same-type") || eBlock.getLocation().getBlock().getType() == block.getType()) && block.getY() != eBlock.getY()) {
                if (isElevator(block)) {
                    next = block;
                }
            }
        }
        return next;
    }

    private Block findNextElevatorUp(Block eBlock, int maxY) {
        Block next = null;
        for (int i = (int) eBlock.getLocation().getY(); i < maxY; i++) {
            Block block = eBlock.getLocation().getWorld().getBlockAt((int) eBlock.getLocation().getX(), i, (int) eBlock.getLocation().getZ());
            if ((!getConfig().getBoolean("require-same-type") || eBlock.getLocation().getBlock().getType() == block.getType()) && block.getY() != eBlock.getY()) {
                if (isElevator(block)) {
                    next = block;
                    return next;
                }
            }
        }
        return next;
    }

}
