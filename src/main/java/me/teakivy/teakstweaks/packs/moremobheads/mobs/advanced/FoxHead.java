package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

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

    protected void register() {
        super.register();
        List<String> keys = List.of("red_fox", "white_fox");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
