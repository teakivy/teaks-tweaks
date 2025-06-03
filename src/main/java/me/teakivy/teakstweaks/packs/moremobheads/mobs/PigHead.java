package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Pig;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class PigHead extends BaseMobHead {

    public PigHead() {
        super(EntityType.PIG, "pig", Sound.ENTITY_PIG_AMBIENT);

        addHeadTexture("warm", "Warm Pig Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2JlYmExYTJkNTZlODRmOGU1MWZlZDY2NTlmMmNiN2MxNGZlZDQzODU5YWY1ODQ3Mzc4OTdiZjcwYzAzOTQ3NSJ9fX0");
        addHeadTexture("temperate", "Temperate Pig Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDFlZTc2ODFhZGYwMDA2N2YwNGJmNDI2MTFjOTc2NDEwNzVhNDRhZTJiMWMwMzgxZDVhYzZiMzI0NjIxMWJmZSJ9fX0");
        addHeadTexture("cold", "Cold Pig Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmExOGQ0MDQzY2Q2YzkwMzg2Njc4ODkxNGZkNTM0MzE1MjgxYWY5ZjI1OWUzNDgzN2UzZTE3NWU1NDVjMmVkZSJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Pig pig = (Pig) event.getEntity();

        String key = switch (Objects.requireNonNull(PigHead.PigVariant.fromVariant(pig.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };

        return this.textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Pig pig = (Pig) event.getEntity();

        String key = switch (Objects.requireNonNull(PigHead.PigVariant.fromVariant(pig.getVariant()))) {
            case WARM -> "Warm";
            case TEMPERATE -> "Temperate";
            case COLD -> "Cold";
        };

        return key + " Pig";
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

        public static PigHead.PigVariant fromVariant(Pig.Variant variant) {
            for (PigHead.PigVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}
