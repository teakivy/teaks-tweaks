package me.teakivy.teakstweaks.packs.dragondrops;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DragonDrops extends BasePack {

    public DragonDrops() {
        super(TTPack.DRAGON_DROPS, Material.DRAGON_EGG);
    }

    @EventHandler
    public void dragonKill(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.ENDER_DRAGON) {
            event.getDrops().clear();
            if (getConfig().getBoolean("elytra.drop") && shouldDrop(getConfig().getInt("elytra.chance"))) {
                event.getDrops().add(new ItemStack(Material.ELYTRA));
            }
            if (getConfig().getBoolean("dragon-egg.drop") && shouldDrop(getConfig().getInt("dragon-egg.chance"))) {
                event.getDrops().add(new ItemStack(Material.DRAGON_EGG));
            }
        }
    }

    public boolean shouldDrop(int chance) {
        int random = (int) (Math.random() * 100) + 1;
        return random <= chance;
    }
}
