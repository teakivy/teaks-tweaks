package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class PiglinHead {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("piglin"))) return;

        event.getDrops().add(MobHeads.getHead("Piglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODFhZmQ4NTM5MTE4MmE5ZjlkZTRmY2UyOWVhZjAyNTE0Y2MyZTA0NDgxNTc3ZGE1ZWRlYjU4YjE3ZTc1NzEzNSJ9fX0"));
    }
}
