package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Goat;
import org.bukkit.event.entity.EntityDeathEvent;

public class GoatHead extends BaseMobHead {

    public GoatHead() {
        super(EntityType.GOAT, "goat", Sound.ENTITY_GOAT_AMBIENT);

        addHeadTexture("goat", "Goat Head", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc0NzNlMDU1ZGY2ZTdmZDk4NjY0ZTlmZGI2MzY3NWYwODgxMDYzMDVkNzQ0MDI0YTQxYmIzNTg5MThhMTQyYiJ9fX0");
        addHeadTexture("screaming_goat", "Screaming Goat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmRhNDg1YWMyMzUxMjQyMDg5MWE1YWUxZThkZTk4OWYwOTFkODQ4ZDE1YTkwNjhkYTQ3MjBkMzE2ZmM0MzMwZiJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Goat goat = (Goat) event.getEntity();

        if (goat.isScreaming()) return "screaming_goat";

        return "goat";
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Goat goat = (Goat) event.getEntity();

        if (goat.isScreaming()) return "Screaming Goat";

        return "Goat";
    }
}
