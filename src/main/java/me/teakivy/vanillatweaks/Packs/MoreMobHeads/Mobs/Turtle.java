package me.teakivy.vanillatweaks.Packs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Turtle {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), .1, .01)) return;

        event.getDrops().add(MobHeads.getHead("Turtle", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA0OTMxMjAwYWQ0NjBiNjUwYTE5MGU4ZDQxMjI3YzM5OTlmYmViOTMzYjUxY2E0OWZkOWU1OTIwZDFmOGU3ZCJ9fX0"));
    }
}
