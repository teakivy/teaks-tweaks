package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class SheepHead extends AdvancedMobHead {

    public SheepHead() {
        super(EntityType.SHEEP, Sound.ENTITY_SHEEP_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Sheep sheep = (Sheep) event.getEntity();
        if (sheep.customName() != null &&
                Objects.requireNonNull(sheep.customName()).toString().toLowerCase().contains("_jeb")) {
            return "jeb_sheep";
        }

        String type = switch (sheep.getColor()) {
            case WHITE -> "white";
            case ORANGE -> "orange";
            case MAGENTA -> "magenta";
            case LIGHT_BLUE -> "light_blue";
            case YELLOW -> "yellow";
            case LIME -> "lime";
            case PINK -> "pink";
            case GRAY -> "gray";
            case LIGHT_GRAY -> "light_gray";
            case CYAN -> "cyan";
            case PURPLE -> "purple";
            case BLUE -> "blue";
            case BROWN -> "brown";
            case GREEN -> "green";
            case RED -> "red";
            case BLACK -> "black";
            case null -> "white";
        };
        return type + "_sheep";
    }
}
