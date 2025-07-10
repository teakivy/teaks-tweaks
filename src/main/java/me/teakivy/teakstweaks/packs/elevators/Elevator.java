package me.teakivy.teakstweaks.packs.elevators;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Marker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Elevator extends BasePack {
    private static final HashMap<UUID, Long> cooldown = new HashMap<>();

    public Elevator() {
        super("elevators", Material.ENDER_PEARL);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (!Permission.ELEVATOR_CREATE.check(event.getPlayer())) return;

        if (!event.getItemDrop().getItemStack().getType().toString().equalsIgnoreCase(getConfig().getString("activator"))) return;
        if (event.getItemDrop().getItemStack().getAmount() != 1) return;
        if (isElevator(event.getItemDrop().getLocation().add(0, -1, 0).getBlock())) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!getElevatorMaterials().contains(event.getItemDrop().getLocation().add(0, -1, 0).getBlock().getType())) return;
                if (event.getItemDrop().getItemStack().getAmount() != 1) return;

                event.getItemDrop().remove();
                createElevator(event.getItemDrop().getLocation().add(0, -1, 0));

                if (event.getItemDrop().isDead()) this.cancel();
            }
        }.runTaskTimer(getPlugin(), 0, 20L);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!getElevatorMaterials().contains(event.getBlock().getType())) return;
        if (!isElevator(block)) return;

        for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation().add(.5, 1, .5), .4, .8, .4)) {
            if (block.getY() + 1 != entity.getLocation().getBlockY()) continue;
            if (entity.getType() != EntityType.MARKER) continue;
            if (!entity.getScoreboardTags().contains("elevator")) continue;

            entity.remove();
            event.getBlock().getDrops().add(new ItemStack(Material.ENDER_PEARL));
            return;
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (!Permission.ELEVATOR_USE.check(event.getPlayer())) return;
        Player player = event.getPlayer();
        if (isOnCooldown(player)) return;
        setCooldown(player);
        Location loc = player.getLocation();
        if (player.isSneaking()) return;

        Block standingBlock = loc.add(0, -1, 0).getBlock();
        if (!isElevator(standingBlock)) return;

        Block elevatorSpot = findNextElevatorDown(standingBlock, standingBlock.getWorld().getMinHeight());
        if (elevatorSpot == null) return;

        player.teleportAsync(new Location(player.getWorld(), player.getLocation().getX(), elevatorSpot.getY() + 1, player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation().add(0, 1, 0), 20, -.5, -.5, -.5, 4);

        if (!getConfig().getBoolean("play-sound")) return;
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }

    @EventHandler
    public void onJump(PlayerMoveEvent e) {
        if (!Permission.ELEVATOR_USE.check(e.getPlayer())) return;
        Player player = e.getPlayer();
        if (isOnCooldown(player)) return;
        if (e.getPlayer().getVelocity().getY() < .1) return;
        e.getPlayer().getVelocity().getY();

        Location loc = player.getLocation();
        Block standingBlock = loc.add(0, -0.6, 0).getBlock();
        if (!isElevator(standingBlock)) return;

        Block elevatorSpot = findNextElevatorUp(standingBlock);
        if (elevatorSpot == null) return;

        Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), () -> {
            player.teleportAsync(new Location(loc.getWorld(), loc.getX(), elevatorSpot.getY() + 1, loc.getZ(), loc.getYaw(), loc.getPitch()));
            player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation().add(0, 1, 0), 20, -.5, -.5, -.5, 4);

            if (!getConfig().getBoolean("play-sound")) return;
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            setCooldown(player);
        }, 1L);
    }

    private void createElevator(Location loc) {
        Block block = loc.getBlock();

        Marker marker = (Marker) block.getWorld().spawnEntity(block.getLocation().add(.5, 1, .5), EntityType.MARKER);
        marker.addScoreboardTag("elevator");

        loc.getWorld().spawnParticle(Particle.PORTAL, block.getLocation().add(.5, 1, .5), 200, -.5, -.5, -.5, -1);
    }

    private boolean isElevator(Block block) {
        for (Entity nearbyEntity : block.getWorld().getNearbyEntities(block.getLocation().add(.5, 1, .5), .4, .4, .4)) {
            if (!nearbyEntity.getScoreboardTags().contains("elevator")) continue;
            if (nearbyEntity.getType() != EntityType.MARKER) continue;

            return true;
        }
        return false;
    }

    private Block findNextElevatorDown(Block eBlock, int minY) {
        for (int i = (int) eBlock.getLocation().getY(); i >= minY; i--) {
            Block block = eBlock.getLocation().getWorld().getBlockAt(eBlock.getX(), i, eBlock.getZ());
            if (!checkBlock(eBlock, block)) continue;

            return block;
        }
        return null;
    }

    private Block findNextElevatorUp(Block eBlock) {
        Block next = null;
        for (int i = (int) eBlock.getLocation().getY(); i < eBlock.getWorld().getMaxHeight(); i++) {
            Block block = eBlock.getLocation().getWorld().getBlockAt(eBlock.getX(), i, eBlock.getZ());
            if (!checkBlock(eBlock, block)) continue;

            next = block;
            return next;
        }
        return next;
    }

    private List<Material> getElevatorMaterials() {
        List<Material> elevatorMaterials = new ArrayList<>();
        for (String block : getConfig().getStringList("elevator-blocks")) {
            elevatorMaterials.add(Material.valueOf(block));
        }

        return elevatorMaterials;
    }

    private boolean checkBlock(Block b1, Block b2) {
        if (getConfig().getBoolean("require-same-type") && b2.getType() != b1.getType()) return false;
        if (b2.getY() == b1.getY()) return false;

        return isElevator(b2);
    }

    private boolean isOnCooldown(Player player) {
        if (cooldown.containsKey(player.getUniqueId())) {
            return cooldown.get(player.getUniqueId()) > System.currentTimeMillis();
        }
        return false;
    }

    private void setCooldown(Player player) {
        cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 500);
    }
}
