package me.teakivy.teakstweaks.Packs.TeaksTweaks.CryObsidian;

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

public class CryObsidian extends BasePack {

    public CryObsidian() {
        super("Cry Obsidian", "cry-obsidian");
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

        if (block.getType() == Material.OBSIDIAN && getConfig().getBoolean("obsidian-to-crying.enabled")) {
            double tearChance = getConfig().getDouble("obsidian-to-crying.chance.tear");
            double waterChance = getConfig().getDouble("obsidian-to-crying.chance.water");

            double chance;

            switch (item.getType()) {
                case GHAST_TEAR:
                    chance = tearChance;
                    break;
                case WATER_BUCKET:
                    chance = waterChance;
                    break;
                default:
                    return;
            }


            if (player.getGameMode() != GameMode.CREATIVE) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }
            event.getClickedBlock().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_GHAST_WARN, 0.2F, 0F);

            if (getRandomFromChance(chance)) {
                block.setType(Material.CRYING_OBSIDIAN);
            }
        }


    public boolean getRandomFromChance(double chance) {
        return Math.random() < chance;
    }
}