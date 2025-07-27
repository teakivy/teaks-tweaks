package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.entity.EntityDeathEvent;

public class HorseHead extends AdvancedMobHead {

    public HorseHead() {
        super(EntityType.HORSE, Sound.ENTITY_HORSE_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Horse horse = (Horse) event.getEntity();
        return switch (horse.getColor()) {
            case WHITE -> "white_horse";
            case CREAMY -> "creamy_horse";
            case CHESTNUT -> "chestnut_horse";
            case BROWN -> "brown_horse";
            case BLACK -> "black_horse";
            case GRAY -> "gray_horse";
            case DARK_BROWN -> "dark_brown_horse";
        };
    }
}
