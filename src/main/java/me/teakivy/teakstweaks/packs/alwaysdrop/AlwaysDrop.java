package me.teakivy.teakstweaks.packs.alwaysdrop;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class AlwaysDrop extends BasePack {

    private final HashSet<Material> alwaysDropBlocks;

    public AlwaysDrop() {
        super(TTPack.ALWAYS_DROP, Material.ENDER_CHEST);
        alwaysDropBlocks = new HashSet<>();
        for (String block : getConfig().getStringList("blocks")) {
            Material item = Material.matchMaterial(block);
            if (item != null && item.isBlock()) {
                alwaysDropBlocks.add(item);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (alwaysDropBlocks.contains(event.getBlock().getType())) {
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType()));
        }
    }
}
