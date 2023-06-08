package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDeathEvent;

public class WolfHead extends BaseMobHead {

    public WolfHead() {
        super(EntityType.WOLF, "wolf", Sound.ENTITY_WOLF_GROWL);

        addHeadTexture("normal", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY0MzlhNDNlNTY4NzAwODgxNWEyZGQxZmY0YTEzNGMxMjIyMWI3ODIzMzY2NzhiOTc5YWQxM2RjZTM5NjY1ZSJ9fX0");
        addHeadTexture("angry", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQxYWE3ZTNiOTU2NGIzODQ2ZjFkZWExNGYxYjFjY2JmMzk5YmJiMjNiOTUyZGJkN2VlYzQxODAyYTI4OWM5NiJ9fX0");
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Wolf wolf = (Wolf) event.getEntity();

        if (wolf.isAngry()) return this.textures.get("angry");

        return this.textures.get("normal");
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Wolf wolf = (Wolf) event.getEntity();

        if (wolf.isAngry()) return "Angry Wolf";

        return "Wolf";
    }
}
