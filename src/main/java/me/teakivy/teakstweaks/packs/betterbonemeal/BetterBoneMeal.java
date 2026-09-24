package me.teakivy.teakstweaks.packs.betterbonemeal;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.ParticleHelper;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.LeafLitter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BetterBoneMeal extends BasePack {

    public BetterBoneMeal() {
        super(TTPack.BETTER_BONE_MEAL, Material.BONE_MEAL);
    }

    @EventHandler
    public void onBoneMeal(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (block == null) return;
        if (event.getHand() == null) return;
        if (player.getInventory().getItem(event.getHand()).getType() != Material.BONE_MEAL) return;

        boolean success = tryBoneMeal(block);

        if (success) {
            player.swingHand(event.getHand());
            doBoneMealAnimation(block);
        }

    }

    /**
     * Handle the bone mealing of a pitcher plant. It should drop 1 pitcher plant.
     * @param block The clicked block
     * @return True if it was successful
     */
    private boolean handlePitcherPlant(Block block) {
        if (!getConfig().getBoolean("allow-pitcher-plant")) return false;

        block.getWorld().dropItem(block.getLocation().add(.5, .5, .5), new ItemStack(Material.PITCHER_PLANT));
        return true;
    }

    /**
     * Handle the bone mealing of sugar cane. It should grow to 3 blocks tall, if possible.
     * @param block The clicked sugar cane block
     * @return True if anything changed
     */
    private boolean handleSugarCane(Block block) {
        if (!getConfig().getBoolean("allow-sugar-cane")) return false;

        Block bottom = block;
        int stacksize = 0;
        while (stacksize < 3 && bottom.getType() == Material.SUGAR_CANE) {
            bottom = bottom.getRelative(BlockFace.DOWN);
            stacksize++;
        }
        if (stacksize >= 3) return false;

        stacksize = 0;
        Block current = bottom.getRelative(BlockFace.UP);

        while (current.getType() == Material.SUGAR_CANE && stacksize < 3) {
            current = current.getRelative(BlockFace.UP);
            stacksize++;
        }
        if (stacksize >= 3) return false;

        boolean changed = false;
        while (stacksize <= 2) {
            stacksize++;
            if (current.getType() == Material.AIR) {
                current.setType(Material.SUGAR_CANE);
                current = current.getRelative(BlockFace.UP);
                changed = true;
                continue;
            }
            break;
        }
        return changed;
    }

    private boolean handleLeafLitter(Block block) {
        if (!getConfig().getBoolean("allow-leaf-litter")) return false;

        LeafLitter leafLitter = (LeafLitter) block.getBlockData();
        if (leafLitter.getSegmentAmount() >= 4) {
            block.getWorld().dropItem(block.getLocation().add(.5, .5, .5), new ItemStack(Material.LEAF_LITTER));
            return true;
        }

        leafLitter.setSegmentAmount(leafLitter.getSegmentAmount() + 1);
        block.setBlockData(leafLitter);
        return true;
    }

    private boolean handleSmallFlower(Block block, String configKey) {
        if (!getConfig().getBoolean(configKey)) return false;

        List<BlockFace> faces = new ArrayList<>();
        faces.add(BlockFace.NORTH);
        faces.add(BlockFace.EAST);
        faces.add(BlockFace.SOUTH);
        faces.add(BlockFace.WEST);
        Collections.shuffle(faces);

        for (BlockFace face : faces) {
            Block adjacent = block.getRelative(face);
            if (adjacent.getType() != Material.AIR) continue;
            Block adjacentBelow = adjacent.getRelative(BlockFace.DOWN);
            if (adjacentBelow.getType() != Material.GRASS_BLOCK) continue;
            adjacent.setType(block.getType());
            return true;
        }

        return false;
    }

    private boolean handleCactus(Block block) {
        return false;
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent event) {
        ItemStack item = event.getItem();
        if (item.getType() != Material.BONE_MEAL) return;
        Block dispenser = event.getBlock();
        Dispenser dispenserBlock = (Dispenser) dispenser.getState();

        Directional directional = (Directional) dispenser.getBlockData();
        Block block = dispenser.getRelative(directional.getFacing());

        boolean success = tryBoneMeal(block);

        if (success) {
            event.setCancelled(true);
            doBoneMealAnimation(block);

            Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                removeBoneMealFromDispenser(dispenserBlock);
            }, 1L);
        }
    }

    private boolean tryBoneMeal(Block block) {
        return switch (block.getType()) {
            case PITCHER_PLANT -> handlePitcherPlant(block);
            case SUGAR_CANE -> handleSugarCane(block);
            case LEAF_LITTER -> handleLeafLitter(block);
            case DANDELION, POPPY, BLUE_ORCHID, ALLIUM, AZURE_BLUET, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, CORNFLOWER, LILY_OF_THE_VALLEY, CLOSED_EYEBLOSSOM, OPEN_EYEBLOSSOM, WITHER_ROSE, GOLDEN_DANDELION -> handleSmallFlower(block, "allow-small-flowers");
            case TORCHFLOWER -> handleSmallFlower(block, "allow-torchflowers");
            case CACTUS -> handleCactus(block);
            default -> false;
        };
    }

    private void removeBoneMealFromDispenser(Dispenser dispenser) {
        List<Integer> possibleSlots = new ArrayList<>();

        for (int i = 0; i < dispenser.getInventory().getSize(); i++) {
            ItemStack item = dispenser.getInventory().getItem(i);

            if (item != null && item.getType() == Material.BONE_MEAL) possibleSlots.add(i);
        }

        if (possibleSlots.isEmpty()) return;

        Collections.shuffle(possibleSlots);

        int slot = possibleSlots.getFirst();
        ItemStack item = dispenser.getInventory().getItem(slot);

        if (item == null) return;
        if (item.getAmount() <= 1) {
            dispenser.getInventory().setItem(slot, null);
            return;
        }
        item.setAmount(item.getAmount() - 1);
    }

    private void doBoneMealAnimation(Block block) {
        ParticleHelper.spawnBoneMealParticles(block);

        block.getWorld().playSound(
                block.getLocation().add(0.5, 0.5, 0.5),
                Sound.ITEM_BONE_MEAL_USE,
                1.0f,
                1.0f
        );
    }
}
