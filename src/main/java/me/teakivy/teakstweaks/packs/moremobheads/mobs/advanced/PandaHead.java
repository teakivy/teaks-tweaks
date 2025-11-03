package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class PandaHead extends AdvancedMobHead {

    public PandaHead() {
        super(EntityType.PANDA, Sound.ENTITY_PANDA_SNEEZE);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Panda panda = (Panda) event.getEntity();
        return switch (panda.getMainGene()) {
            case NORMAL -> "normal_panda";
            case WORRIED -> "worried_panda";
            case LAZY -> "lazy_panda";
            case PLAYFUL -> "playful_panda";
            case AGGRESSIVE -> "aggressive_panda";
            case BROWN -> "brown_panda";
            case WEAK -> "weak_panda";
        };
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("normal_panda", "worried_panda", "lazy_panda", "playful_panda", "aggressive_panda", "brown_panda", "weak_panda");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
