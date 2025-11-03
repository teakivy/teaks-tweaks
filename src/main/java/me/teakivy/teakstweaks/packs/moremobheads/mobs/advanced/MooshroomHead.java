package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MushroomCow;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

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

    protected void register() {
        super.register();
        List<String> keys = List.of("red_mooshroom", "brown_mooshroom");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
