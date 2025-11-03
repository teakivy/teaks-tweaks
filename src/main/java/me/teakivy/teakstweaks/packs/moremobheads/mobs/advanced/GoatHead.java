package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Goat;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class GoatHead extends AdvancedMobHead {

    public GoatHead() {
        super(EntityType.GOAT, Sound.ENTITY_GOAT_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Goat goat = (Goat) event.getEntity();
        return goat.isScreaming() ? "screaming_goat" : "goat";
    }

    @Override
    protected Sound getSound(String key) {
        return key.equalsIgnoreCase("screaming_goat") ? Sound.ENTITY_GOAT_SCREAMING_AMBIENT : Sound.ENTITY_GOAT_AMBIENT;
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("screaming_goat", "goat");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
