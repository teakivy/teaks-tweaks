package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

public class BeeHead extends BaseMobHead {

    public BeeHead() {
        super(EntityType.BEE, "bee", Sound.ENTITY_BEE_POLLINATE);

        addHeadTexture("angry", "Angry Bee Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQwMDIyM2YxZmE1NDc0MWQ0MjFkN2U4MDQ2NDA5ZDVmM2UxNWM3ZjQzNjRiMWI3Mzk5NDAyMDhmM2I2ODZkNCJ9fX0");
        addHeadTexture("angry_pollinated", "Angry Pollinated Bee Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTZiNzRlMDUyYjc0Mjg4Nzk5YmE2ZDlmMzVjNWQwMjIxY2Y4YjA0MzMxNTQ3ZWMyZjY4ZDczNTk3YWUyYzliIn19fQ");
        addHeadTexture("normal", "Bee Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlhYzE2ZjI5NmI0NjFkMDVlYTA3ODVkNDc3MDMzZTUyNzM1OGI0ZjMwYzI2NmFhMDJmMDIwMTU3ZmZjYTczNiJ9fX0");
        addHeadTexture("normal_pollinated", "Pollinated Bee Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjcyN2QwYWIwM2Y1Y2QwMjJmODcwNWQzZjdmMTMzY2E0OTIwZWFlOGUxZTQ3YjUwNzQ0MzNhMTM3ZTY5MWU0ZSJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Bee bee = (Bee) event.getEntity();

        if (bee.getAnger() != 0) {
            if (!bee.hasNectar()) return textures.get("angry");
            return textures.get("angry_pollinated");
        }
        if (!bee.hasNectar()) return textures.get("normal");
        return textures.get("normal_pollinated");
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Bee bee = (Bee) event.getEntity();

        if (bee.getAnger() != 0) {
            if (!bee.hasNectar()) return "Angry Bee";
            return "Angry Pollinated Bee";
        }
        if (!bee.hasNectar()) return "Bee";
        return "Pollinated Bee";
    }
}
