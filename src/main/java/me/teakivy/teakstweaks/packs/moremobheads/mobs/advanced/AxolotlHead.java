package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

public class AxolotlHead extends AdvancedMobHead {

    public AxolotlHead() {
        super(EntityType.AXOLOTL, Sound.ENTITY_AXOLOTL_IDLE_AIR);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Axolotl axolotl = (Axolotl) event.getEntity();
        return switch (axolotl.getVariant()) {
            case LUCY -> "lucy_axolotl";
            case WILD -> "wild_axolotl";
            case GOLD -> "gold_axolotl";
            case CYAN -> "cyan_axolotl";
            case BLUE -> "blue_axolotl";
        };
    }
}
