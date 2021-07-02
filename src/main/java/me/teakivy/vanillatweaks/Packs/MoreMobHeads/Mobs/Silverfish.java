package me.teakivy.vanillatweaks.Packs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Silverfish {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), .05, .01)) return;

        event.getDrops().add(MobHeads.getHead("Silverfish", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjI1ZTlmYWUzNzE2NjRkZTFhODAwYzg0ZDAyNTEyNGFiYjhmMTUxMTE4MDdjOGJjMWFiOTEyNmFhY2JkNGY5NSJ9fX0"));
    }
}
