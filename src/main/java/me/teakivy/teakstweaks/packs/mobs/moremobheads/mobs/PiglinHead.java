package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PiglinHead extends BaseMobHead {

    public PiglinHead() {
        super(EntityType.PIGLIN, "piglin", null);
    }

    @Override
    public ItemStack getHead(EntityDeathEvent event) {
        return new ItemStack(Material.PIGLIN_HEAD);
    }
}
