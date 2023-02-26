package me.teakivy.teakstweaks.packs.teakstweaks.lecternreset;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Material;
import org.bukkit.block.Lectern;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.LecternInventory;

public class LecternReset extends BasePack {

    public LecternReset() {
        super("Lectern Reset", "lectern-reset", PackType.TEAKSTWEAKS, Material.LECTERN);
    }

    @EventHandler
    public void onLectern(InventoryCloseEvent event) {
        if (event.getInventory().getType() != InventoryType.LECTERN) return;

        LecternInventory lecternInv = (LecternInventory) event.getInventory();
        Lectern lectern = lecternInv.getHolder();

        if (lectern == null) return;
        if (lecternInv.getViewers().size() != 1) return;

        lectern.setPage(0);
        lectern.update();
    }

}
