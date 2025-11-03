package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

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

    protected void register() {
        super.register();
        List<String> keys = List.of("lucy_axolotl", "wild_axolotl", "gold_axolotl", "cyan_axolotl", "blue_axolotl");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
