package me.teakivy.teakstweaks.packs.oldmoremobheads.mobs;

import me.teakivy.teakstweaks.packs.oldmoremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class FrogHead extends BaseMobHead {

    public FrogHead() {
        super(EntityType.FROG, "frog", Sound.ENTITY_FROG_TONGUE);

        addHeadTexture("warm", "Warm Frog Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDViMGRhNDM5NzViODNjMzMyMjc4OGRkYTMxNzUwNjMzMzg0M2FlYmU1NTEyNzg3Y2IyZTNkNzY5ZWQyYjM4MiJ9fX0");
        addHeadTexture("temperate", "Temperate Frog Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTUwZDEwNzNkNDFmMTkzNDA1ZDk1YjFkOTQxZjlmZTFhN2ZmMDgwZTM4MTU1ZDdiYjc4MGJiYmQ4ZTg2ZjcwZCJ9fX0");
        addHeadTexture("cold", "Cold Frog Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY4Nzc4OTNlOTIwZmY1ZGZhNGI1ZmJkMTRkYWJlZTJlNjMwOGE2Zjk3YzNhMTliMDhlMjQxYTI5ZWI5YTVjMyJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Frog frog = (Frog) event.getEntity();

        String key = switch (Objects.requireNonNull(FrogVariant.fromVariant(frog.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };

        return this.textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Frog frog = (Frog) event.getEntity();

        String key = switch (Objects.requireNonNull(FrogVariant.fromVariant(frog.getVariant()))) {
            case WARM -> "Warm";
            case TEMPERATE -> "Temperate";
            case COLD -> "Cold";
        };

        return key + " Frog";
    }

    protected enum FrogVariant {
        WARM(Frog.Variant.WARM),
        TEMPERATE(Frog.Variant.TEMPERATE),
        COLD(Frog.Variant.COLD);

        private final Frog.Variant variant;

        FrogVariant(Frog.Variant profession) {
            this.variant = profession;
        }

        public Frog.Variant getVariant() {
            return variant;
        }

        public static FrogVariant fromVariant(Frog.Variant variant) {
            for (FrogVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}
