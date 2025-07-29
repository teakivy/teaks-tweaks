package me.teakivy.teakstweaks.packs.cauldronconcrete;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CauldronConcrete extends BasePack {

    public CauldronConcrete() {
        super (TTPack.CAULDRON_CONCRETE, Material.CAULDRON);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!Permission.CAULDRON_CONCRETE.check(event.getPlayer())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (event.getClickedBlock().getType() != Material.WATER_CAULDRON) return;

        if (event.getItem() == null) return;
        if (!event.getItem().getType().name().contains("_CONCRETE_POWDER")) return;

        Levelled levelled = (Levelled) event.getClickedBlock().getBlockData();
        if (levelled.getLevel() == 1 && getConfig().getBoolean("drops-water-level")) {
            event.getClickedBlock().setType(Material.BARRIER);
            Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> event.getClickedBlock().setType(Material.CAULDRON), 1L);
        }

        event.setCancelled(true);

        if (event.getClickedBlock().getType() == Material.WATER_CAULDRON && getConfig().getBoolean("drops-water-level")) {
            levelled.setLevel(levelled.getLevel() - 1);
        }

        event.getClickedBlock().setBlockData(levelled);

        event.getItem().setAmount(event.getItem().getAmount() - 1);

        ItemStack newItem = new ItemStack(Material.valueOf(event.getItem().getType().name().replace("_CONCRETE_POWDER", "_CONCRETE")));

        event.getPlayer().getInventory().addItem(newItem);
    }
}
