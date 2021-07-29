package me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.vanillatweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class Wither {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), 1, 0)) return;

        Random rand = new Random();
        int gen = rand.nextInt(4);

        if (gen == 0) event.getDrops().add(MobHeads.getHead("Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRhMTA4MjhmNjNiN2VjZGVmZDc2N2IzMjQ1ZmJkYWExM2MzZWMwYzZiMTM3NzRmMWVlOGQzMDdjMDM0YzM4MyJ9fX0"));
        if (gen == 1) event.getDrops().add(MobHeads.getHead("Invulnerable Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5NmViOGQ1OTljNGZlZjUzYTk3MTc2YjcyZmY4ZmM0MWUzMmE2NmExNTlmZDQ1MTkwYTBkYTE1NDU4N2UxMiJ9fX0"));
        if (gen == 2) event.getDrops().add(MobHeads.getHead("Armored Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjM3YzU4MTRhOTJmOGVjMGY2YWU5OTMzYWJlOTU0MmUxNjUxOTA3NjhlNzYwNDc4NTQzYWViZWVkNDAyN2MyNyJ9fX0"));
        if (gen == 3) event.getDrops().add(MobHeads.getHead("Armored Invulnerable Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM2ODJiMDYyMDNiOWRlNGMyODU0MTA3MWEyNmNkYzM0MGRkMjVkNGMzNzJiNzAyM2VjMmY0MTIwMjFkNjJmNyJ9fX0"));
    }
}
