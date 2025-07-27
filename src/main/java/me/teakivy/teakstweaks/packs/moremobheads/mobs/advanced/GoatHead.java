package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Goat;
import org.bukkit.event.entity.EntityDeathEvent;

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
        return key == "screaming_goat" ? Sound.ENTITY_GOAT_SCREAMING_AMBIENT : Sound.ENTITY_GOAT_AMBIENT;
    }
}
