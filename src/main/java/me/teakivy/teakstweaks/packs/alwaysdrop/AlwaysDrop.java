package me.teakivy.teakstweaks.packs.alwaysdrop;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AlwaysDrop extends BasePack {

    public AlwaysDrop() {
        super("always-drop", Material.ENDER_CHEST);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        List<String> blockList = getConfig().getStringList("blocks");
        for (String block : blockList) {
            if (event.getBlock().getType().toString().equalsIgnoreCase(block)) {
                event.setDropItems(false);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(event.getBlock().getType()));
            }
        }
    }
}
