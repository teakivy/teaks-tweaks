package me.teakivy.teakstweaks.packs.survival.fastleafdecay;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;

import java.util.*;

public class Decay extends BasePack {

    // Code By: @StarTux on Github
    // Couldn't find where to contact you
    // If you have any issues with this, DM me @TeakIvyYT on Twitter
    // Thanks!

    public Decay() {
        super("Fast Leaf Decay", "fast-leaf-decay", PackType.SURVIVAL, Material.OAK_LEAVES);
    }

    private final List<Block> scheduledBlocks = new ArrayList<>();
    private static final List<BlockFace> NEIGHBORS = Arrays
            .asList(BlockFace.UP,
                    BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST,
                    BlockFace.DOWN);


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        onBlockRemove(event.getBlock(), getConfig().getLong("break-delay"));
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        onBlockRemove(event.getBlock(), getConfig().getLong("decay-delay"));
    }


    private void onBlockRemove(final Block oldBlock, long delay) {
        if (!Tag.LOGS.isTagged(oldBlock.getType())
                && !Tag.LEAVES.isTagged(oldBlock.getType())) {
            return;
        }
        // No return
        Collections.shuffle(NEIGHBORS);
        for (BlockFace neighborFace: NEIGHBORS) {
            final Block block = oldBlock.getRelative(neighborFace);
            if (!block.getType().toString().toLowerCase(Locale.ROOT).contains("leaves")) continue;
            Leaves leaves = (Leaves) block.getBlockData();
            if (leaves.isPersistent()) continue;
            if (scheduledBlocks.contains(block)) continue;
            if (getConfig().getBoolean("one-by-one")) {
                if (scheduledBlocks.isEmpty()) {
                    main.getServer().getScheduler().runTaskLater(main, this::decayOne, delay);
                }
                scheduledBlocks.add(block);
            } else {
                main.getServer().getScheduler().runTaskLater(main, () -> decay(block), delay);
            }
            scheduledBlocks.add(block);
        }
    }

    private boolean decay(Block block) {
        if (!scheduledBlocks.remove(block)) return false;
        if (!block.getWorld().isChunkLoaded(block.getX() >> 4, block.getZ() >> 4)) return false;
        if (!Tag.LEAVES.isTagged(block.getType())) return false;
        Leaves leaves = (Leaves) block.getBlockData();
        if (leaves.isPersistent()) return false;
        if (leaves.getDistance() < 7) return false;
        LeavesDecayEvent event = new LeavesDecayEvent(block);
        main.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        if (getConfig().getBoolean("spawn-particles")) {
            block.getWorld()
                    .spawnParticle(Particle.BLOCK_DUST,
                            block.getLocation().add(0.5, 0.5, 0.5),
                            8, 0.2, 0.2, 0.2, 0,
                            block.getType().createBlockData());
        }
        if (getConfig().getBoolean("play-sound")) {
            block.getWorld().playSound(block.getLocation(),
                    Sound.BLOCK_GRASS_BREAK,
                    SoundCategory.BLOCKS, 0.05f, 1.2f);
        }
        block.breakNaturally();
        return true;
    }
    private void decayOne() {
        boolean decayed = false;
        do {
            if (scheduledBlocks.isEmpty()) return;
            Block block = scheduledBlocks.get(0);
            decayed = decay(block); // Will remove block from list.
        } while (!decayed);
        if (!scheduledBlocks.isEmpty()) {
            long delay = getConfig().getLong("decay-delay");
            if (delay <= 0) delay = 1L;
            main.getServer().getScheduler().runTaskLater(main, this::decayOne, delay);
        }
    }
}
