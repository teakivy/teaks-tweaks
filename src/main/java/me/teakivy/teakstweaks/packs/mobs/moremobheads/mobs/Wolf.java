package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Wolf {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("wolf"))) return;

        org.bukkit.entity.Wolf wolf = (org.bukkit.entity.Wolf) event.getEntity();

        if (wolf.isAngry()) event.getDrops().add(MobHeads.getHead("Angry Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQxYWE3ZTNiOTU2NGIzODQ2ZjFkZWExNGYxYjFjY2JmMzk5YmJiMjNiOTUyZGJkN2VlYzQxODAyYTI4OWM5NiJ9fX0"));
        else event.getDrops().add(MobHeads.getHead("Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY0MzlhNDNlNTY4NzAwODgxNWEyZGQxZmY0YTEzNGMxMjIyMWI3ODIzMzY2NzhiOTc5YWQxM2RjZTM5NjY1ZSJ9fX0"));
    }
}
