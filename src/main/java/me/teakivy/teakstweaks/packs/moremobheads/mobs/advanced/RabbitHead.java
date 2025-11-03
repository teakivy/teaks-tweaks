package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.Objects;

public class RabbitHead extends AdvancedMobHead {

    public RabbitHead() {
        super(EntityType.RABBIT, Sound.ENTITY_RABBIT_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Rabbit rabbit = (Rabbit) event.getEntity();
        if (rabbit.customName() != null &&
                Objects.requireNonNull(rabbit.customName()).toString().toLowerCase().contains("toast")) {
            return "toast_rabbit";
        }

        String type = switch (rabbit.getRabbitType()) {
            case BROWN -> "brown";
            case WHITE -> "white";
            case BLACK -> "black";
            case BLACK_AND_WHITE -> "black_white";
            case GOLD -> "gold";
            case SALT_AND_PEPPER -> "salt_pepper";
            case THE_KILLER_BUNNY -> "killer";
        };
        return type + "_rabbit";
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("toast", "brown", "white", "black", "black_white", "gold", "salt_pepper", "killer");
        for (String key : keys) {
            new CustomItem(key + "_rabbit_head", getHead(key + "_rabbit")).register();
        }
    }
}
