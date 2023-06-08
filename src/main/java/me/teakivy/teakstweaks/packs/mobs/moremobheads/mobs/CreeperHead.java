package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Material;
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
}
