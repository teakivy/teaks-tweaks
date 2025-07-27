package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.event.entity.EntityDeathEvent;

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
}
