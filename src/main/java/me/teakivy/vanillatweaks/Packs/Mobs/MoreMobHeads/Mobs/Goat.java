package me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Goat {
    public static void onDeath(EntityDeathEvent event) {
        org.bukkit.entity.Goat goat = (org.bukkit.entity.Goat) event.getEntity();
        if (goat.isScreaming()) {
            if (!MobHeads.dropChance(event.getEntity().getKiller(), 1, 0)) return;
        }else {
            if (!MobHeads.dropChance(event.getEntity().getKiller(), .01, .001)) return;
        }

        if (goat.isScreaming()) event.getDrops().add(MobHeads.getHead("Screaming Goat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmRhNDg1YWMyMzUxMjQyMDg5MWE1YWUxZThkZTk4OWYwOTFkODQ4ZDE1YTkwNjhkYTQ3MjBkMzE2ZmM0MzMwZiJ9fX0"));
        else event.getDrops().add(MobHeads.getHead("Goat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc0NzNlMDU1ZGY2ZTdmZDk4NjY0ZTlmZGI2MzY3NWYwODgxMDYzMDVkNzQ0MDI0YTQxYmIzNTg5MThhMTQyYiJ9fX0"));
    }
}
