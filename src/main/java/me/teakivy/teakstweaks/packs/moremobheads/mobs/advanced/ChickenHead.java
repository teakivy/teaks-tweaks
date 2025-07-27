package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class ChickenHead extends AdvancedMobHead {

    public ChickenHead() {
        super(EntityType.CHICKEN, Sound.ENTITY_CHICKEN_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Chicken chicken = (Chicken) event.getEntity();
        String type = switch (Objects.requireNonNull(ChickenVariant.fromVariant(chicken.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };
        return type + "_chicken";
    }

    protected enum ChickenVariant {
        WARM(Chicken.Variant.WARM),
        TEMPERATE(Chicken.Variant.TEMPERATE),
        COLD(Chicken.Variant.COLD);

        private final Chicken.Variant variant;

        ChickenVariant(Chicken.Variant profession) {
            this.variant = profession;
        }

        public Chicken.Variant getVariant() {
            return variant;
        }

        public static ChickenVariant fromVariant(Chicken.Variant variant) {
            for (ChickenVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}