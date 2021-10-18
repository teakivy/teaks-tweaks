package me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Mobs;

import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.Head;
import me.teakivy.teakstweaks.Packs.Mobs.MoreMobHeads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class Horse {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("horse"))) return;

        org.bukkit.entity.Horse horse = (org.bukkit.entity.Horse) event.getEntity();

        if (horse.getColor() == org.bukkit.entity.Horse.Color.WHITE) event.getDrops().add(MobHeads.getHead("White Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdiYzYxNjA5NzMwZjJjYjAxMDI2OGZhYjA4MjFiZDQ3MzUyNjk5NzUwYTE1MDU5OWYyMWMzZmM0ZTkyNTkxYSJ9fX0"));
        if (horse.getColor() == org.bukkit.entity.Horse.Color.CREAMY) event.getDrops().add(MobHeads.getHead("Creamy Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJhMGQ1NGNjMDcxMjY3ZDZiZmQ1ZjUyM2Y4Yzg5ZGNmZGM1ZTgwNWZhYmJiNzYwMTBjYjNiZWZhNDY1YWE5NCJ9fX0"));
        if (horse.getColor() == org.bukkit.entity.Horse.Color.CHESTNUT) event.getDrops().add(MobHeads.getHead("Chestnut Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmM4NzIwZDFmNTUyNjkzYjQwYTlhMzNhZmE0MWNlZjA2YWZkMTQyODMzYmVkOWZhNWI4ODdlODhmMDVmNDlmYSJ9fX0"));
        if (horse.getColor() == org.bukkit.entity.Horse.Color.BROWN) event.getDrops().add(MobHeads.getHead("Brown Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjc3MTgwMDc3MGNiNGU4MTRhM2Q5MTE4NmZjZDc5NWVjODJlMDYxMDJmZjdjMWVlNGU1YzM4MDEwMmEwYzcwZiJ9fX0"));
        if (horse.getColor() == org.bukkit.entity.Horse.Color.BLACK) event.getDrops().add(MobHeads.getHead("Black Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjcyM2ZhNWJlNmFjMjI5MmE3MjIzMGY1ZmQ3YWI2NjM0OTNiZDhmN2U2NDgxNjQyNGRjNWJmMjRmMTMzODkwYyJ9fX0"));
        if (horse.getColor() == org.bukkit.entity.Horse.Color.GRAY) event.getDrops().add(MobHeads.getHead("Gray Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI1OTg2MTAyMTgxMDgzZmIzMTdiYzU3MTJmNzEwNGRhYTVhM2U4ODkyNjRkZmViYjkxNTlmNmUwOGJhYzkwYyJ9fX0"));
        if (horse.getColor() == org.bukkit.entity.Horse.Color.DARK_BROWN) event.getDrops().add(MobHeads.getHead("Dark Brown Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YyMzQxYWFhMGM4MmMyMmJiYzIwNzA2M2UzMTkyOTEwOTdjNTM5YWRhZDlhYTkxM2ViODAwMWIxMWFhNTlkYSJ9fX0"));

    }
}
