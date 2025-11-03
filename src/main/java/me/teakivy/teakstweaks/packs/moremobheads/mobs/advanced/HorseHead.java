package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

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

    protected void register() {
        super.register();
        List<String> keys = List.of("white_horse", "creamy_horse", "chestnut_horse", "brown_horse", "black_horse", "gray_horse", "dark_brown_horse");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
