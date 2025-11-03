package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Strider;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class StriderHead extends AdvancedMobHead {

    public StriderHead() {
        super(EntityType.STRIDER, Sound.ENTITY_STRIDER_AMBIENT);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Strider strider = (Strider) event.getEntity();
        return strider.isShivering() ? "freezing_strider" : "normal_strider";
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("freezing_strider", "normal_strider");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
