package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class ChickenHead extends BaseMobHead {

    public ChickenHead() {
        super(EntityType.CHICKEN, "chicken", Sound.ENTITY_CHICKEN_AMBIENT);

        addHeadTexture("warm", "Warm Chicken Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Q2NmIwYTM1NWFhMjdmMzY0OWRmZjQwODBmZTkyYWI1ZjJhMWUxOWJhOGM3ZjE1Y2E2NzUwNjg0M2RlYTBkNyJ9fX0");
        addHeadTexture("temperate", "Temperate Chicken Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJhZjZlNTg0N2VlYTA5OWUxYjBhYjhjMjBhOWU1ZjNjNzE5MDE1OGJkYTU0ZTI4MTMzZDliMjcxZWMwY2I0YiJ9fX0");
        addHeadTexture("cold", "Cold Chicken Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTA3NzM2MWExOGZjMWRjM2EwYzhlMmNlZTEyMmRiNmNkMGUyZmIwODAzMzU0OGFlOWM0N2U2ZjMzZmE0ZGIzOCJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Chicken chicken = (Chicken) event.getEntity();

        String key = switch (Objects.requireNonNull(ChickenHead.ChickenVariant.fromVariant(chicken.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };

        return this.textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Chicken chicken = (Chicken) event.getEntity();

        String key = switch (Objects.requireNonNull(ChickenHead.ChickenVariant.fromVariant(chicken.getVariant()))) {
            case WARM -> "Warm";
            case TEMPERATE -> "Temperate";
            case COLD -> "Cold";
        };

        return key + " Chicken";
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

        public static ChickenHead.ChickenVariant fromVariant(Chicken.Variant variant) {
            for (ChickenHead.ChickenVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}
