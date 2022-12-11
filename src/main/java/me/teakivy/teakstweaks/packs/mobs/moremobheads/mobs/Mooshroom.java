package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.entity.MushroomCow;
import org.bukkit.event.entity.EntityDeathEvent;

public class Mooshroom {
    public static void onDeath(EntityDeathEvent event) {
        MushroomCow mooshroom = (MushroomCow) event.getEntity();
        if (mooshroom.getVariant() == MushroomCow.Variant.RED)
            if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("red-mooshroom"))) return;
        else if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("brown-mooshroom"))) return;

        if (mooshroom.getVariant() == MushroomCow.Variant.RED) event.getDrops().add(MobHeads.getHead("Red Mooshroom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE4MDYwNmU4MmM2NDJmMTQxNTg3NzMzZTMxODBhZTU3ZjY0NjQ0MmM5ZmZmZDRlNTk5NzQ1N2UzNDMxMWEyOSJ9fX0"));
        if (mooshroom.getVariant() == MushroomCow.Variant.BROWN) event.getDrops().add(MobHeads.getHead("Brown Mooshroom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U2NDY2MzAyYTVhYjQzOThiNGU0NzczNDk4MDhlNWQ5NDAyZWEzYWQ4ZmM0MmUyNDQ2ZTRiZWQwYTVlZDVlIn19fQ"));

    }
}
