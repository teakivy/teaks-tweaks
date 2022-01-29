package me.teakivy.teakstweaks.Packs.TeaksTweaks.BetterFoliage;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BetterFoliage extends BasePack {

    public BetterFoliage() {
        super("Better Foliage", "better-foliage");
    }

    @EventHandler
    public void interactEvent(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getItem() == null) return;
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) return;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (block.getType() == Material.DIRT && getConfig().getBoolean("dirt-to-grass.enabled")) {
            double wheatChance = getConfig().getDouble("dirt-to-grass.chance.wheat-seeds");
            double melonChance = getConfig().getDouble("dirt-to-grass.chance.melon-seeds");
            double pumpkinChance = getConfig().getDouble("dirt-to-grass.chance.pumpkin-seeds");
            double beetrootChance = getConfig().getDouble("dirt-to-grass.chance.beetroot-seeds");

            double chance;

            switch (item.getType()) {
                case WHEAT_SEEDS:
                    chance = wheatChance;
                    break;
                case MELON_SEEDS:
                    chance = melonChance;
                    break;
                case PUMPKIN_SEEDS:
                    chance = pumpkinChance;
                    break;
                case BEETROOT_SEEDS:
                    chance = beetrootChance;
                default:
                    return;
            }


            if (player.getGameMode() != GameMode.CREATIVE) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }
            event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ITEM_BONE_MEAL_USE, 1.0F, 2F);

            if (getRandomFromChance(chance)) {
                block.setType(Material.GRASS_BLOCK);
            }
        }

        if (getConfig().getBoolean("foliage-shearing")) {
            if (block.getType() == Material.LARGE_FERN && item.getType() == Material.SHEARS) {
                block.breakNaturally(new ItemStack(Material.SHEARS));
            }
            if (block.getType() == Material.TALL_GRASS && item.getType() == Material.SHEARS) {
                block.breakNaturally(new ItemStack(Material.SHEARS));
            }
        }
    }


    public boolean getRandomFromChance(double chance) {
        return Math.random() < chance;
    }
}
