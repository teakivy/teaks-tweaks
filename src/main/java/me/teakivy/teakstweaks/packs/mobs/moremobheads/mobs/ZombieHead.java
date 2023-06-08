package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ZombieHead extends BaseMobHead {

    public ZombieHead() {
        super(EntityType.ZOMBIE, "zombie", null);
    }

    @Override
    public ItemStack getHead(EntityDeathEvent event) {
        return new ItemStack(Material.ZOMBIE_HEAD);
    }
}
