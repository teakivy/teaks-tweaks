package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MushroomCow;
import org.bukkit.event.entity.EntityDeathEvent;

public class MooshroomHead extends AdvancedMobHead {

    public MooshroomHead() {
        super(EntityType.MOOSHROOM, Sound.ENTITY_MOOSHROOM_CONVERT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        MushroomCow mooshroom = (MushroomCow) event.getEntity();
        return switch (mooshroom.getVariant()) {
            case RED -> "red_mooshroom";
            case BROWN -> "brown_mooshroom";
        };
    }
}
