package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.bukkit.event.entity.EntityDeathEvent;

public class ParrotHead extends AdvancedMobHead {

    public ParrotHead() {
        super(EntityType.PARROT, Sound.ENTITY_PARROT_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Parrot parrot = (Parrot) event.getEntity();
        return switch (parrot.getVariant()) {
            case BLUE -> "blue_parrot";
            case GRAY -> "gray_parrot";
            case RED -> "red_parrot";
            case GREEN -> "green_parrot";
            case CYAN -> "light_blue_parrot";
        };
    }
}
