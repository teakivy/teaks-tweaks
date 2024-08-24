package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EnderDragonHead extends BaseMobHead {

    public EnderDragonHead() {
        super(EntityType.ENDER_DRAGON, "ender_dragon", null);
    }

    @Override
    public ItemStack getHead(EntityDeathEvent event) {
        return new ItemStack(Material.DRAGON_HEAD);
    }
}
