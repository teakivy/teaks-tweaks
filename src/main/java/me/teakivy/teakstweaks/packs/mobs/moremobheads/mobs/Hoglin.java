package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Hoglin {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("hoglin"))) return;

        event.getDrops().add(MobHeads.getHead("Hoglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM0YTdmNTdmYzAzYjEzYWEyZjlkODNjZGQ0ODIyYjkzNjc5MzA5NmRhZjUxZTc4MDI1YmJkMjQxZWQ2ZjY4ZCJ9fX0"));
    }
}
