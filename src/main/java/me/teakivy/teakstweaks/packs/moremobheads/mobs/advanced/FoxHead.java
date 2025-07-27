package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.event.entity.EntityDeathEvent;

public class FoxHead extends AdvancedMobHead {

    public FoxHead() {
        super(EntityType.FOX, Sound.ENTITY_FOX_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Fox fox = (Fox) event.getEntity();
        return switch (fox.getFoxType()) {
            case RED -> "red_fox";
            case SNOW -> "white_fox";
        };
    }
}
