package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.Objects;

public class FrogHead extends AdvancedMobHead {

    public FrogHead() {
        super(EntityType.FROG, Sound.ENTITY_FROG_TONGUE);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Frog frog = (Frog) event.getEntity();
        String type = switch (Objects.requireNonNull(FrogVariant.fromVariant(frog.getVariant()))) {
            case WARM -> "warm";
            case TEMPERATE -> "temperate";
            case COLD -> "cold";
        };
        return type + "_frog";
    }

    protected enum FrogVariant {
        WARM(Frog.Variant.WARM),
        TEMPERATE(Frog.Variant.TEMPERATE),
        COLD(Frog.Variant.COLD);

        private final Frog.Variant variant;

        FrogVariant(Frog.Variant variant) {
            this.variant = variant;
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

    protected void register() {
        super.register();
        List<String> keys = List.of("warm", "temperate", "cold");
        for (String key : keys) {
            new CustomItem(key + "_frog_head", getHead(key + "_frog")).register();
        }
    }
}
