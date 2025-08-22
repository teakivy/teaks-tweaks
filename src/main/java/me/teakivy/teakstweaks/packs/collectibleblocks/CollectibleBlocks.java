package me.teakivy.teakstweaks.packs.collectibleblocks;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class CollectibleBlocks extends BasePack {

    private HashSet<Material> collectibleBlocks;

    public CollectibleBlocks() {
        super(TTPack.COLLECTIBLE_BLOCKS, Material.BUDDING_AMETHYST);
        collectibleBlocks = new HashSet<>();
        for (String block : getConfig().getStringList("blocks")) {
            Material item = Material.matchMaterial(block);
            if (item != null && item.isBlock()) {
                collectibleBlocks.add(item);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!collectibleBlocks.contains(event.getBlock().getType())) return;

        boolean shouldDrop = true;
        if (getConfig().getBoolean("require-silk-touch")) {
            shouldDrop = event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH);
        }

        if (!shouldDrop) return;
        event.setDropItems(false);
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType()));
    }
}
