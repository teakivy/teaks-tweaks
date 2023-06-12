package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.EntityDeathEvent;

public class SheepHead extends BaseMobHead {

    public SheepHead() {
        super(EntityType.SHEEP, "sheep", Sound.ENTITY_SHEEP_AMBIENT);

        addHeadTexture("black", "Black Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTMzMzVlODA2NWM3YjVkZmVhNThkM2RmNzQ3NGYzOTZhZjRmYTBhMmJhNTJhM2M5YjdmYmE2ODMxOTI3MWM5MSJ9fX0=");
        addHeadTexture("blue", "Blue Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQwZTI3N2RhNmMzOThiNzQ5YTMyZjlkMDgwZjFjZjRjNGVmM2YxZjIwZGQ5ZTVmNDIyNTA5ZTdmZjU5M2MwIn19fQ==");
        addHeadTexture("brown", "Brown Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzEyOGQwODZiYzgxNjY5ZmMyMjU1YmIyMmNhZGM2NmEwZjVlZDcwODg1ZTg0YzMyZDM3YzFiNDg0ZGIzNTkwMSJ9fX0=");
        addHeadTexture("cyan", "Cyan Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ0MmZjYmNhZjlkNDhmNzNmZmIwYzNjMzZmMzRiNDY0MzI5NWY2ZGFhNmNjNzRhYjlkMjQyZWQ1YWE1NjM2In19fQ==");
        addHeadTexture("gray", "Gray Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ZhZmVjZjA2MDNiMmRjZDc5ODRkMjUyNTg2MDY5ODk1ZGI5YWE3OGUxODQxYmQ1NTRiMTk1MDhkY2Y5NjdhMSJ9fX0=");
        addHeadTexture("green", "Green Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWVhODg3ZWFlNGIwNzYzNmU5ZTJmOTA2NjA5YjAwYWI4ZDliODZiNzQ3MjhiODE5ZmY2ZjM3NjU4M2VhMTM5In19fQ==");
        addHeadTexture("light_blue", "Light Blue Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJmMjNhZjg3MTljNDM3YjNlZTg0MDE5YmEzYzllNjljYTg1NGQzYThhZmQ1Y2JhNmQ5Njk2YzA1M2I0ODYxNCJ9fX0=");
        addHeadTexture("light_gray", "Light Gray Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQyZTJlOTNhMTQyYmZkNDNmMjQwZDM3ZGU4ZjliMDk3NmU3NmU2NWIyMjY1MTkwODI1OWU0NmRiNzcwZSJ9fX0=");
        addHeadTexture("lime", "Lime Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmJlYWQwMzQyYWU4OWI4ZGZkM2Q3MTFhNjBhZGQ2NWUyYzJiZmVhOGQwYmQyNzRhNzU4N2RlZWQ3YTMxODkyZSJ9fX0=");
        addHeadTexture("magenta", "Magenta Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYThlMWYwNWYwZGFjY2E2M2E3MzE4NzRmOTBhNjkzZmZlMjFmZjgzMmUyYjFlMWQwN2I2NWM4NzY0NTI2ZjA4OSJ9fX0=");
        addHeadTexture("orange", "Orange Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjY4NGQwNGZhODBhYTU5ZGExNDUzNWRlYWQzODgzZDA5N2ZiYmE0MDA2MjU2NTlmNTI1OTk2NDgwNmJhNjZmMCJ9fX0=");
        addHeadTexture("pink", "Pink Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM2M2U4YTkzZDI4N2E4NGU2NDAzMDlhZTgzY2ExZGUwYTBiMjU3NTA1YTIwZWM1NWIzMzQ5ZDQwYTQ0ODU0In19fQ==");
        addHeadTexture("purple", "Purple Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ0OWQwODI5MWRhZTQ1YTI0NjczNjE5NjAyZjQzNWI1N2Y0Y2Q0ZTllOThkMmUwZmJlYzRmMTgxNDQ3ODFkMyJ9fX0=");
        addHeadTexture("red", "Red Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQ3OGUwNTcxNThkZTZmNDVlMjU0MWNkMTc3ODhlNjQwY2NiNTk3MjNkZTU5YzI1NGU4MmFiNTcxMWYzZmMyNyJ9fX0=");
        addHeadTexture("white", "White Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRmZTdjYzQ2ZDc0OWIxNTMyNjFjMWRjMTFhYmJmMmEzMTA4ZWExYmEwYjI2NTAyODBlZWQxNTkyZGNmYzc1YiJ9fX0=");
        addHeadTexture("yellow", "Yellow Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRiMjhmMDM1NzM1OTA2ZjgyZmZjNGRiYTk5YzlmMGI1NTI0MGU0MjZjZDFjNTI1YTlhYTc3MTgwZWVjNDkzNCJ9fX0=");
        addHeadTexture("jeb", "jeb_ Sheep Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjMzMzI2NzY1YTE5MGViZjkwZDU0ODZkNzFmMjBlMjU5N2U0YmVlMmEzOTFmZWNiYmQ4MGRlYmZlMWY4MmQ3OCJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Sheep sheep = (Sheep) event.getEntity();
        if (event.getEntity().getCustomName() != null &&
                event.getEntity().getCustomName().equalsIgnoreCase("jeb_")) {
            return this.textures.get("jeb");
        }

        if (sheep.getColor() == null) return this.textures.get("white");

        String key = switch (sheep.getColor()) {
            case BLACK -> "black";
            case BLUE -> "blue";
            case BROWN -> "brown";
            case CYAN -> "cyan";
            case GRAY -> "gray";
            case GREEN -> "green";
            case LIGHT_BLUE -> "light_blue";
            case LIGHT_GRAY -> "light_gray";
            case LIME -> "lime";
            case MAGENTA -> "magenta";
            case ORANGE -> "orange";
            case PINK -> "pink";
            case PURPLE -> "purple";
            case RED -> "red";
            case WHITE -> "white";
            case YELLOW -> "yellow";
        };

        return this.textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Sheep sheep = (Sheep) event.getEntity();

        if (event.getEntity().getCustomName() != null &&
                event.getEntity().getCustomName().equalsIgnoreCase("jeb_")) {
            return "jeb_ Sheep";
        }

        if (sheep.getColor() == null) return "White Sheep";

        String color = switch (sheep.getColor()) {
            case BLACK -> "Black";
            case BLUE -> "Blue";
            case BROWN -> "Brown";
            case CYAN -> "Cyan";
            case GRAY -> "Gray";
            case GREEN -> "Green";
            case LIGHT_BLUE -> "Light Blue";
            case LIGHT_GRAY -> "Light Gray";
            case LIME -> "Lime";
            case MAGENTA -> "Magenta";
            case ORANGE -> "Orange";
            case PINK -> "Pink";
            case PURPLE -> "Purple";
            case RED -> "Red";
            case WHITE -> "White";
            case YELLOW -> "Yellow";
        };

        return color + " Sheep";
    }
}
