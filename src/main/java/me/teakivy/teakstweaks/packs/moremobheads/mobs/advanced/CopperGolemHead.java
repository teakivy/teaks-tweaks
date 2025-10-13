package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.CopperGolem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.event.entity.EntityDeathEvent;

public class CopperGolemHead extends AdvancedMobHead {

    public CopperGolemHead() {
        super(EntityType.COPPER_GOLEM, Sound.ENTITY_COPPER_GOLEM_SPIN);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        CopperGolem golem = (CopperGolem) event.getEntity();
        return switch (golem.getWeatheringState()) {
            case UNAFFECTED -> "copper_golem";
            case EXPOSED -> "exposed_copper_golem";
            case WEATHERED -> "weathered_copper_golem";
            case OXIDIZED -> "oxidized_copper_golem";
        };
    }

    @Override
    protected Sound getSound(String key) {
        return switch (key) {
            case "exposed_copper_golem", "weathered_copper_golem" -> Sound.ENTITY_COPPER_GOLEM_WEATHERED_SPIN;
            case "oxidized_copper_golem" -> Sound.ENTITY_COPPER_GOLEM_OXIDIZED_SPIN;
            default -> Sound.ENTITY_COPPER_GOLEM_SPIN;
        };
    }
}
