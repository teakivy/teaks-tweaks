package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class CatHead extends AdvancedMobHead {

    public CatHead() {
        super(EntityType.CAT, Sound.ENTITY_CAT_HISS);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Cat cat = (Cat) event.getEntity();
        String type = switch (Objects.requireNonNull(CatType.fromType(cat.getCatType()))) {
            case CALICO -> "calico";
            case JELLIE -> "jellie";
            case SIAMESE -> "siamese";
            case PERSIAN -> "persian";
            case BRITISH_SHORTHAIR -> "british_shorthair";
            case TABBY -> "tabby";
            case BLACK -> "black";
            case RED -> "red";
            case RAGDOLL -> "ragdoll";
            case WHITE -> "white";
            case ALL_BLACK -> "all_black";
        };
        return type + "_cat";
    }

    protected enum CatType {
        CALICO(Cat.Type.CALICO),
        JELLIE(Cat.Type.JELLIE),
        SIAMESE(Cat.Type.SIAMESE),
        PERSIAN(Cat.Type.PERSIAN),
        BRITISH_SHORTHAIR(Cat.Type.BRITISH_SHORTHAIR),
        TABBY(Cat.Type.TABBY),
        BLACK(Cat.Type.BLACK),
        RED(Cat.Type.RED),
        RAGDOLL(Cat.Type.RAGDOLL),
        WHITE(Cat.Type.WHITE),
        ALL_BLACK(Cat.Type.ALL_BLACK);

        private final Cat.Type type;

        CatType(Cat.Type type) {
            this.type = type;
        }

        public Cat.Type getType() {
            return type;
        }

        public static CatType fromType(Cat.Type type) {
            for (CatType value : values()) {
                if (value.type == type) return value;
            }
            return null;
        }
    }
}