package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.event.entity.EntityDeathEvent;

public class FoxHead extends BaseMobHead {

    public FoxHead() {
        super(EntityType.FOX, "red-fox", Sound.ENTITY_FOX_AMBIENT);

        addHeadTexture("red", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdlMDA0MzExMWJjNTcwOTA4NTYyNTkxNTU1NzFjNzkwNmU3MDcwNDZkZjA0MWI4YjU3MjcwNGM0NTFmY2Q4MiJ9fX0");
        addHeadTexture("white", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE0MzYzNzdlYjRjNGI0ZTM5ZmIwZTFlZDg4OTlmYjYxZWUxODE0YTkxNjliOGQwODcyOWVmMDFkYzg1ZDFiYSJ9fX0");
    }

    @Override
    public boolean dropHead(EntityDeathEvent event) {
        Fox fox = (Fox) event.getEntity();

        String key = this.key;
        if (fox.getFoxType() == Fox.Type.SNOW) key = "white-fox";

        return MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance(key));
    }

    @Override
    public String getTexture(EntityDeathEvent event) {
        Fox fox = (Fox) event.getEntity();

        String key = switch (fox.getFoxType()) {
            case RED -> "red";
            case SNOW -> "white";
        };

        return this.textures.get(key);
    }

    @Override
    public String getName(EntityDeathEvent event) {
        Fox fox = (Fox) event.getEntity();

        String key = switch (fox.getFoxType()) {
            case RED -> "";
            case SNOW -> "Snow ";
        };

        return key + "Fox";
    }
}
