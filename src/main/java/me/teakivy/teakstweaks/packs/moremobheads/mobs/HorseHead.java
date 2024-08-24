package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.entity.EntityDeathEvent;

public class HorseHead extends BaseMobHead {

    public HorseHead() {
        super(EntityType.HORSE, "horse", Sound.ENTITY_HORSE_AMBIENT);

        addHeadTexture("white", "White Horse Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdiYzYxNjA5NzMwZjJjYjAxMDI2OGZhYjA4MjFiZDQ3MzUyNjk5NzUwYTE1MDU5OWYyMWMzZmM0ZTkyNTkxYSJ9fX0");
        addHeadTexture("creamy", "Creamy Horse Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJhMGQ1NGNjMDcxMjY3ZDZiZmQ1ZjUyM2Y4Yzg5ZGNmZGM1ZTgwNWZhYmJiNzYwMTBjYjNiZWZhNDY1YWE5NCJ9fX0");
        addHeadTexture("chestnut", "Chestnut Horse Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmM4NzIwZDFmNTUyNjkzYjQwYTlhMzNhZmE0MWNlZjA2YWZkMTQyODMzYmVkOWZhNWI4ODdlODhmMDVmNDlmYSJ9fX0");
        addHeadTexture("brown", "Brown Horse Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjc3MTgwMDc3MGNiNGU4MTRhM2Q5MTE4NmZjZDc5NWVjODJlMDYxMDJmZjdjMWVlNGU1YzM4MDEwMmEwYzcwZiJ9fX0");
        addHeadTexture("black", "Black Horse Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjcyM2ZhNWJlNmFjMjI5MmE3MjIzMGY1ZmQ3YWI2NjM0OTNiZDhmN2U2NDgxNjQyNGRjNWJmMjRmMTMzODkwYyJ9fX0");
        addHeadTexture("gray", "Gray Horse Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI1OTg2MTAyMTgxMDgzZmIzMTdiYzU3MTJmNzEwNGRhYTVhM2U4ODkyNjRkZmViYjkxNTlmNmUwOGJhYzkwYyJ9fX0");
        addHeadTexture("dark_brown", "Dark Brown Horse Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YyMzQxYWFhMGM4MmMyMmJiYzIwNzA2M2UzMTkyOTEwOTdjNTM5YWRhZDlhYTkxM2ViODAwMWIxMWFhNTlkYSJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Horse horse = (Horse) event.getEntity();

        String key = switch (horse.getColor()) {
            case WHITE -> "white";
            case CREAMY -> "creamy";
            case CHESTNUT -> "chestnut";
            case BROWN -> "brown";
            case BLACK -> "black";
            case GRAY -> "gray";
            case DARK_BROWN -> "dark_brown";
        };

        return textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Horse horse = (Horse) event.getEntity();

        String name = switch (horse.getColor()) {
            case WHITE -> "White";
            case CREAMY -> "Creamy";
            case CHESTNUT -> "Chestnut";
            case BROWN -> "Brown";
            case BLACK -> "Black";
            case GRAY -> "Gray";
            case DARK_BROWN -> "Dark Brown";
        };

        return name + " Horse";
    }
}
