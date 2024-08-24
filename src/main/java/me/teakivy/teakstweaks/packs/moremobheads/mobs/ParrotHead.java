package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.bukkit.event.entity.EntityDeathEvent;

public class ParrotHead extends BaseMobHead {

    public ParrotHead() {
        super(EntityType.PARROT, "parrot", Sound.ENTITY_PARROT_AMBIENT);

        addHeadTexture("red", "Red Parrot Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBhM2Q0N2Y1NGU3MWE1OGJmOGY1N2M1MjUzZmIyZDIxM2Y0ZjU1YmI3OTM0YTE5MTA0YmZiOTRlZGM3NmVhYSJ9fX0");
        addHeadTexture("blue", "Blue Parrot Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk0YmQzZmNmNGQ0NjM1NGVkZThmZWY3MzEyNmRiY2FiNTJiMzAxYTFjOGMyM2I2Y2RmYzEyZDYxMmI2MWJlYSJ9fX0");
        addHeadTexture("green", "Green Parrot Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmExZGMzMzExNTIzMmY4MDA4MjVjYWM5ZTNkOWVkMDNmYzE4YWU1NTNjMjViODA1OTUxMzAwMGM1OWUzNTRmZSJ9fX0");
        addHeadTexture("light_blue", "Light Blue Parrot Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzI2OGNlMzdiZTg1MDdlZDY3ZTNkNDBiNjE3ZTJkNzJmNjZmOWQyMGIxMDZlZmIwOGU2YmEwNDFmOWI5ZWYxMCJ9fX0");
        addHeadTexture("gray", "Gray Parrot Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzFiZTcyM2FhMTczOTNkOTlkYWRkYzExOWM5OGIyYzc5YzU0YjM1ZGViZTA1YzcxMzhlZGViOGQwMjU2ZGM0NiJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Parrot parrot = (Parrot) event.getEntity();

        String key = switch (parrot.getVariant()) {
            case RED -> "red";
            case BLUE -> "blue";
            case GREEN -> "green";
            case CYAN -> "light_blue";
            case GRAY -> "gray";
        };

        return textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Parrot parrot = (Parrot) event.getEntity();

        String name = switch (parrot.getVariant()) {
            case RED -> "Red";
            case BLUE -> "Blue";
            case GREEN -> "Green";
            case CYAN -> "Light Blue";
            case GRAY -> "Gray";
        };

        return name + " Parrot";
    }
}
