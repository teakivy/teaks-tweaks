package me.teakivy.teakstweaks.packs.moremobheads.mobs.advanced;

import me.teakivy.teakstweaks.packs.moremobheads.abstractions.AdvancedMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

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
}
