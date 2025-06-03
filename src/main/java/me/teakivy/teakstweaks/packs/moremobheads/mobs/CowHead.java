package me.teakivy.teakstweaks.packs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class CowHead extends BaseMobHead {

    public CowHead() {
        super(EntityType.COW, "cow", Sound.ENTITY_COW_AMBIENT);

        addHeadTexture("warm", "Warm Cow Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmYzNDRjOTk0MmFjMzU3ODlmYTZjMWQ2MDI4NzZjOWY1NmY2OGYwMmU2NzBkZTBmZjgxYjdjODMxNjEwZmY1YSJ9fX0");
        addHeadTexture("temperate", "Temperate Cow Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhmZGM2NWUzYmEwOWMxMmJjMWQ2OGZmZmIzM2Q2MzQzMDVjMDE0MjMxYjNkNDJhODg4ZTMzNmFlNDkwMDkwZiJ9fX0");
        addHeadTexture("cold", "Cold Cow Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDI4ZDQwNTA3YTBlYzUxMmNkOTA4NGM2ZGI4NzRlNDAzZmQzOTYxMjA1NzQxMzJhNjhjZTEwY2FmZGQ3YjY3YyJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Cow cow = (Cow) event.getEntity();

        String key = switch (Objects.requireNonNull(CowHead.CowVariant.fromVariant(cow.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };

        return this.textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Cow cow = (Cow) event.getEntity();

        String key = switch (Objects.requireNonNull(CowHead.CowVariant.fromVariant(cow.getVariant()))) {
            case WARM -> "Warm";
            case TEMPERATE -> "Temperate";
            case COLD -> "Cold";
        };

        return key + " Cow";
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

        public static CowHead.CowVariant fromVariant(Cow.Variant variant) {
            for (CowHead.CowVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }
}
