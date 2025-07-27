package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class WolfHead extends AdvancedMobHead {

    public WolfHead() {
        super(EntityType.WOLF, Sound.ENTITY_WOLF_CUTE_PANT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Wolf wolf = (Wolf) event.getEntity();
        String type = switch (Objects.requireNonNull(WolfVariant.fromVariant(wolf.getVariant()))) {
            case ASHEN -> "ashen";
            case BLACK -> "black";
            case CHESTNUT -> "chestnut";
            case PALE -> "pale";
            case RUSTY -> "rusty";
            case SNOWY -> "snowy";
            case SPOTTED -> "spotted";
            case STRIPED -> "striped";
            case WOODS -> "woods";
        };
        if (wolf.isAngry()) type = "angry_" + type;
        return type + "_wolf";
    }

    @Override
    protected Sound getSound(String key) {
        return key.startsWith("angry_") ? Sound.ENTITY_WOLF_GROWL : Sound.ENTITY_WOLF_CUTE_PANT;
    }

    protected enum WolfVariant {
        ASHEN(Wolf.Variant.ASHEN),
        BLACK(Wolf.Variant.BLACK),
        CHESTNUT(Wolf.Variant.CHESTNUT),
        PALE(Wolf.Variant.PALE),
        RUSTY(Wolf.Variant.RUSTY),
        SNOWY(Wolf.Variant.SNOWY),
        SPOTTED(Wolf.Variant.SPOTTED),
        STRIPED(Wolf.Variant.STRIPED),
        WOODS(Wolf.Variant.WOODS);

        private final Wolf.Variant variant;

        WolfVariant(Wolf.Variant profession) {
            this.variant = profession;
        }

        public Wolf.Variant getVariant() {
            return variant;
        }

        public static WolfVariant fromVariant(Wolf.Variant variant) {
            for (WolfVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}
