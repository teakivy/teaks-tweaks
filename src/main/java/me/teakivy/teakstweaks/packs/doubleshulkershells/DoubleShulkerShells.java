package me.teakivy.teakstweaks.packs.doubleshulkershells;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DoubleShulkerShells extends BasePack {

    public DoubleShulkerShells() {
        super(TTPack.DOUBLE_SHULKER_SHELLS, Material.SHULKER_SHELL);
    }

    @EventHandler
    public void shulkerKill(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.SHULKER) {
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.SHULKER_SHELL, 2));
        }
    }

}
