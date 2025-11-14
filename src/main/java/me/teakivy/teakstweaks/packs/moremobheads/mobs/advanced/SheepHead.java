package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.Objects;

public class SheepHead extends AdvancedMobHead {

    public SheepHead() {
        super(EntityType.SHEEP, Sound.ENTITY_SHEEP_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Sheep sheep = (Sheep) event.getEntity();
        if (sheep.customName() != null &&
                Objects.requireNonNull(sheep.customName()).toString().toLowerCase().contains("jeb_")) {
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

    protected void register() {
        super.register();
        List<String> keys = List.of("jeb", "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black");
        for (String key : keys) {
            new CustomItem(key + "_sheep_head", getHead(key + "_sheep")).register();
        }
    }
}
