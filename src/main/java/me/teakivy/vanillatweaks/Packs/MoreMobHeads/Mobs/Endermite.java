package me.teakivy.vanillatweaks.Packs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class Endermite {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(Objects.requireNonNull(event.getEntity().getKiller()), .1, .01)) return;

        event.getDrops().add(MobHeads.getHead("Endermite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGM2YjY1YzIyYjQ0NjViYTY3OTNiMjE5NWNkNTA4NGNlODNiODhkY2E2ZTU1ZWI5NDg0NTQwYWNkNzM1MmE1MCJ9fX0"));
    }
}
