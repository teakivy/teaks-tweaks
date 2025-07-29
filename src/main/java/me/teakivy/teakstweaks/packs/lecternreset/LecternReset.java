package me.teakivy.teakstweaks.packs.lecternreset;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.block.Lectern;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.LecternInventory;

public class LecternReset extends BasePack {

    public LecternReset() {
        super(TTPack.LECTERN_RESET, Material.LECTERN);
    }

    @EventHandler
    public void onLectern(InventoryOpenEvent event) {
        if (event.getInventory().getType() != InventoryType.LECTERN) return;

        LecternInventory lecternInv = (LecternInventory) event.getInventory();
        Lectern lectern = lecternInv.getHolder();

        if (lectern == null) return;

        lectern.setPage(0);
        lectern.update();
    }

}
