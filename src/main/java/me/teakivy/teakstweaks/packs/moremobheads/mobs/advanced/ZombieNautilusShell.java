package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ZombieNautilus;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.Objects;

public class ZombieNautilusShell extends AdvancedMobHead {

    public ZombieNautilusShell() {
        super(EntityType.ZOMBIE_NAUTILUS, Sound.ENTITY_ZOMBIE_NAUTILUS_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        ZombieNautilus zombieNautilus = (ZombieNautilus) event.getEntity();
        String type = switch (Objects.requireNonNull(ZombieNautilusShell.ZombieNautilusVariant.fromVariant(zombieNautilus.getVariant()))) {
            case CORAL -> "coral";
            case TEMPERATE -> "temperate";
        };
        return type + "_zombie_nautilus";
    }

    protected enum ZombieNautilusVariant {
        CORAL(ZombieNautilus.Variant.WARM),
        TEMPERATE(ZombieNautilus.Variant.TEMPERATE);

        private final ZombieNautilus.Variant variant;

        ZombieNautilusVariant(ZombieNautilus.Variant variant) {
            this.variant = variant;
        }

        public ZombieNautilus.Variant getVariant() {
            return variant;
        }

        public static ZombieNautilusShell.ZombieNautilusVariant fromVariant(ZombieNautilus.Variant variant) {
            for (ZombieNautilusShell.ZombieNautilusVariant value : values()) {
                if (value.variant == variant) return value;
            }
            return null;
        }
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("coral", "temperate");
        for (String key : keys) {
            new CustomItem(key + "_zombie_nautilus_shell", getHead(key + "_zombie_nautilus")).register();
        }
    }
}