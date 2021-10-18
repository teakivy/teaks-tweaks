package me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Head;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Cod {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("cod"))) return;

        event.getDrops().add(MobHeads.getHead("Cod", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjI0NmUxOWIzMmNmNzg0NTQ5NDQ3ZTA3Yjk2MDcyZTFmNjU2ZDc4ZTkzY2NjYTU2Mzc0ODVlNjc0OTczNDY1MiJ9fX0"));
    }
}
