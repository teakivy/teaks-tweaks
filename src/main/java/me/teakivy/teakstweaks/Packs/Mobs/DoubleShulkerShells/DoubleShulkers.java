package me.teakivy.teakstweaks.Packs.Mobs.DoubleShulkerShells;

import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DoubleShulkers extends BasePack {

    public DoubleShulkers() {
        super("Double Shulker Shells", "double-shulker-shells");
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
