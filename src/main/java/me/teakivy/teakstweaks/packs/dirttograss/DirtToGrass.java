package me.teakivy.teakstweaks.packs.dirttograss;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DirtToGrass extends BasePack {

    public DirtToGrass() {
        super(TTPack.DIRT_TO_GRASS, Material.GRASS_BLOCK);
    }

    @EventHandler
    public void interactEvent(PlayerInteractEvent event) {
        if (!Permission.DIRT_TO_GRASS.check(event.getPlayer())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getItem() == null) return;
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) return;
        if (block.getType() != Material.DIRT) return;
        ItemStack item = player.getInventory().getItemInMainHand();

        double wheatChance = getConfig().getDouble("wheat-seeds-chance");
        double melonChance = getConfig().getDouble("melon-seeds-chance");
        double pumpkinChance = getConfig().getDouble("pumpkin-seeds-chance");
        double beetrootChance = getConfig().getDouble("beetroot-seeds-chance");

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


    public boolean getRandomFromChance(double chance) {
        return Math.random() < chance;
    }
}
