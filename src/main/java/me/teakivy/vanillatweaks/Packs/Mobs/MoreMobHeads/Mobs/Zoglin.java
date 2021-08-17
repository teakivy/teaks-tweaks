package me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.Head;
import me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Zoglin {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("zoglin"))) return;

        event.getDrops().add(MobHeads.getHead("Zoglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUzNDkzYTk1NmJmZDc1ODhlZDFhOGVhODU4NzU5NjY3NjU5ZDU4MTAwY2JlY2Q2ZDk2Y2NjMGNhOWIzNjkyMyJ9fX0"));
    }
}
