package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Sound;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class BeeHead extends AdvancedMobHead {

    public BeeHead() {
        super(EntityType.BEE, Sound.ENTITY_BEE_POLLINATE);
    }

    @Override
    protected String getKey(EntityDeathEvent event) {
        Bee bee = (Bee) event.getEntity();
        if (bee.getAnger() > 0) {
            return bee.hasNectar() ? "angry_pollinated_bee" : "angry_bee";
        }
        return bee.hasNectar() ? "pollinated_bee" : "bee";
    }

    protected void register() {
        super.register();
        List<String> keys = List.of("angry_pollinated_bee", "angry_bee", "pollinated_bee", "bee");
        for (String key : keys) {
            new CustomItem(key + "_head", getHead(key)).register();
        }
    }
}
