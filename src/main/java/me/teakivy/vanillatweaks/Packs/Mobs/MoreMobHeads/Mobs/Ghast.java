package me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Ghast {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), .625, .0125)) return;

        event.getDrops().add(MobHeads.getHead("Ghast", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUzZGUzMWEyZDAwNDFhNmVmNzViZjdhNmM4NDY4NDY0ZGIxYWFhNjIwMWViYjFhNjAxM2VkYjIyNDVjNzYwNyJ9fX0"));
    }
}
