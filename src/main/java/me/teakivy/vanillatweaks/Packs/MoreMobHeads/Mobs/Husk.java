package me.teakivy.vanillatweaks.Packs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Husk {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), .06, .01)) return;

        event.getDrops().add(MobHeads.getHead("Husk", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMzODMxOGJjOTFhMzZjZDVhYjZhYTg4NWM5YTRlZTJiZGFjZGFhNWM2NmIyYTk5ZGZiMGE1NjA5ODNmMjQ4MCJ9fX0"));
    }
}
