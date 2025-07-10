package me.teakivy.teakstweaks.packs.dragondrops;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DragonDrops extends BasePack {

    public DragonDrops() {
        super("dragon-drops", Material.DRAGON_EGG);
    }

    @EventHandler
    public void dragonKill(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDER_DRAGON) {
            event.getDrops().clear();
            if (getConfig().getBoolean("drop-egg")) {
                event.getDrops().add(new ItemStack(Material.DRAGON_EGG));
            }
            if (getConfig().getBoolean("drop-elytra")) {
                event.getDrops().add(new ItemStack(Material.ELYTRA));
            }
        }
    }
}
