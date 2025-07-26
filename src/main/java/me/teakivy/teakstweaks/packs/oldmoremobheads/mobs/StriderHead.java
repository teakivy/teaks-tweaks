package me.teakivy.teakstweaks.packs.oldmoremobheads.mobs;

import me.teakivy.teakstweaks.packs.oldmoremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Strider;
import org.bukkit.event.entity.EntityDeathEvent;

public class StriderHead extends BaseMobHead {

    public StriderHead() {
        super(EntityType.STRIDER, "strider", Sound.ENTITY_STRIDER_AMBIENT);

        addHeadTexture("normal", "Strider Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM0MGZhZDFjMTFkZTllNjQyMmI0MDU0MjZlOWI5NzkwN2YzNWJjZTM0NWUzNzU4NjA0ZDNlN2JlN2RmODg0In19fQ");
        addHeadTexture("freezing", "Freezing Strider Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjcxMzA4NWE1NzUyN2U0NTQ1OWMzOGZhYTdiYjkxY2FiYjM4MWRmMzFjZjJiZjc5ZDY3YTA3MTU2YjZjMjMwOSJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Strider strider = (Strider) event.getEntity();

        if (strider.isShivering()) return this.textures.get("freezing");

        return this.textures.get("normal");
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Strider strider = (Strider) event.getEntity();

        if (strider.isShivering()) return "Freezing Strider";

        return "Strider";
    }
}
