package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.moremobheads.MobHeads;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CreeperHead extends BaseMobHead {

    public CreeperHead() {
        super(EntityType.CREEPER, "creeper", null);
    }

    @Override
    public ItemStack getHead(EntityDeathEvent event) {
        return new ItemStack(Material.CREEPER_HEAD);
    }

    @Override
    public boolean dropHead(EntityDeathEvent event) {
        Creeper creeper = (Creeper) event.getEntity();
        if (creeper.isPowered()) return false;
        return MobHeads.shouldDrop(event.getEntity().getKiller(), "creeper");
    }
}
