package me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Head;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Warden {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("warden"))) return;

        event.getDrops().add(MobHeads.getHead("Warden", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJmMzg3OWI3MzcxMjc0ODVlYjM1ZGRlZTc0OGQwNmNmOTE0YjE5M2Q5Nzc1M2FlMzRlOTIyMzA4NDI4MzFmYiJ9fX0"));
    }
}
