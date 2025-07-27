package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class CowHead extends AdvancedMobHead {

    public CowHead() {
        super(EntityType.COW, Sound.ENTITY_COW_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Cow cow = (Cow) event.getEntity();
        String type = switch (Objects.requireNonNull(CowVariant.fromVariant(cow.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };
        return type + "_cow";
    }

    protected enum CowVariant {
        WARM(Cow.Variant.WARM),
        TEMPERATE(Cow.Variant.TEMPERATE),
        COLD(Cow.Variant.COLD);

        private final Cow.Variant variant;

        CowVariant(Cow.Variant profession) {
            this.variant = profession;
        }

        public Cow.Variant getVariant() {
            return variant;
        }

        public static CowVariant fromVariant(Cow.Variant variant) {
            for (CowVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}