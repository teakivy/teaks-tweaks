package me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Head;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Axolotl implements Listener {

    public static void onDeath(EntityDeathEvent event) {
        org.bukkit.entity.Axolotl axolotl = (org.bukkit.entity.Axolotl) event.getEntity();

        if (axolotl.getVariant() == org.bukkit.entity.Axolotl.Variant.BLUE) {
            if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("blue-axolotl"))) return;
        } else {
            if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("axolotl"))) return;
        }

        if (axolotl.getVariant() == org.bukkit.entity.Axolotl.Variant.LUCY)
            event.getDrops().add(MobHeads.getHead("Lucy Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY3ZTE1ZWFiNzMwNjRiNjY4MGQxZGI5OGJhNDQ1ZWQwOTE0YmEzNWE3OTk5OTdjMGRhMmIwM2ZmYzNhODgyNiJ9fX0"));
        if (axolotl.getVariant() == org.bukkit.entity.Axolotl.Variant.WILD)
            event.getDrops().add(MobHeads.getHead("Wild Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDdjZjAyNzQ5OThiZjVhN2YzOGIzNzAzNmUxNTRmMTEyZmEyZTI4YmFkNDBkNWE3Yzk0NzY1ZmU0ZjUyMjExZSJ9fX0"));
        if (axolotl.getVariant() == org.bukkit.entity.Axolotl.Variant.GOLD)
            event.getDrops().add(MobHeads.getHead("Gold Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTU4NTYwMTE1ZmFhZDExNjE5YjNkNTVkZTc5ZWYyYTA1M2Y0NzhhNjcxOTRiYmU5MjQ3ZWRlYTBiYzk4ZTgzNCJ9fX0"));
        if (axolotl.getVariant() == org.bukkit.entity.Axolotl.Variant.CYAN)
            event.getDrops().add(MobHeads.getHead("Cyan Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODUxMTk2ZDQzOTMwNjU5ZDcxN2UxYjZhMDQ2YTA4ZDEyMjBmY2I0ZTMxYzQ4NTZiYzMzZTc1NTE5ODZlZjFkIn19fQ=="));
        if (axolotl.getVariant() == org.bukkit.entity.Axolotl.Variant.BLUE)
            event.getDrops().add(MobHeads.getHead("Blue Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjhmZDEwYjBmZWY0NTk1OTYwYjFmNjQxOTNiYzhhMTg2NWEyZDJlZDQ4YjJlMmNlMDNkOTk0NTYzMDI3ZGY5NSJ9fX0"));

    }
}
