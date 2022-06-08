package me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Head;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Tadpole {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("tadpole"))) return;

        event.getDrops().add(MobHeads.getHead("Tadpole", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2RhZjE2NTNiNWY1OWI1ZWM1YTNmNzk2MDljYjQyMzM1NzlmZWYwN2U2OTNiNjE3NDllMDkwMDE0OWVkZjU2MyJ9fX0"));
    }
}
