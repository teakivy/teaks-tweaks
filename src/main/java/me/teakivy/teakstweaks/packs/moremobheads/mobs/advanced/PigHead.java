package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class PigHead extends AdvancedMobHead {

    public PigHead() {
        super(EntityType.PIG, Sound.ENTITY_PIG_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Pig pig = (Pig) event.getEntity();
        String type = switch (Objects.requireNonNull(PigVariant.fromVariant(pig.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };
        return type + "_pig";
    }

    protected enum PigVariant {
        WARM(Pig.Variant.WARM),
        TEMPERATE(Pig.Variant.TEMPERATE),
        COLD(Pig.Variant.COLD);

        private final Pig.Variant variant;

        PigVariant(Pig.Variant profession) {
            this.variant = profession;
        }

        public Pig.Variant getVariant() {
            return variant;
        }

        public static PigVariant fromVariant(Pig.Variant variant) {
            for (PigVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}
