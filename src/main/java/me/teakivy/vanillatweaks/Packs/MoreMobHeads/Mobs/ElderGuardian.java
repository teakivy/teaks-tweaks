package me.teakivy.vanillatweaks.Packs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class ElderGuardian {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), 1, 0)) return;

        event.getDrops().add(MobHeads.getHead("Elder Guardian", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGEyZDY0ZjRhMDBlOWM4NWY2NzI2MmVkY2FjYjg0NTIzNTgxYWUwZjM3YmRhYjIyZGQ3MDQ1MjRmNjJlMTY5ZiJ9fX0"));
    }
}
