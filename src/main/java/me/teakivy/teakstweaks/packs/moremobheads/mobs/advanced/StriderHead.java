package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Strider;
import org.bukkit.event.entity.EntityDeathEvent;

public class StriderHead extends AdvancedMobHead {

    public StriderHead() {
        super(EntityType.STRIDER, Sound.ENTITY_STRIDER_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Strider strider = (Strider) event.getEntity();
        return strider.isShivering() ? "freezing_strider" : "normal_strider";
    }
}
