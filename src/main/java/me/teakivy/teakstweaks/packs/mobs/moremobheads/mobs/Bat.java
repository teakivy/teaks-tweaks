package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Bat implements Listener {

    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("bat"))) return;

        event.getDrops().add(MobHeads.getHead("Bat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGEyOWM5ZWNlNDI4ZmEyMzM5NWFjMjAxOWJmMmQwMjc0NDA1MjlmMjUzM2ZjODIwMWU3YjNkYTBmNjBmMjAwNSJ9fX0"));
    }

}
