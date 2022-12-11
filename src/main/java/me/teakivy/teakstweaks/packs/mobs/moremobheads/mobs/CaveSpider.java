package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class CaveSpider {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("cave-spider"))) return;

        event.getDrops().add(MobHeads.getHead("Cave Spider", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWY4NDNiYjdhOTg0ZTczNGFiNzZhODhjOWExYTBmNWE0MGJmNzk1MjQ4MDlhODUxMWJmMzJkMDU3NTI2ZjdmMyJ9fX0"));
    }
}
