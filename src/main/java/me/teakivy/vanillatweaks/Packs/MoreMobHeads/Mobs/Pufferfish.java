package me.teakivy.vanillatweaks.Packs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Pufferfish {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), .15, .01)) return;

        event.getDrops().add(MobHeads.getHead("Pufferfish", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTI3MzNkNWRhNTljODJlYWYzMTBiMzgyYWZmNDBiZDUxM2M0NDM1NGRiYmFiZmUxNGIwNjZhNTU2ODEwYTdmOSJ9fX0"));
    }
}
