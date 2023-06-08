package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.BaseMobHead;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;

public class ChargedCreeperHead extends BaseMobHead {

    public ChargedCreeperHead() {
        super(EntityType.CREEPER, "charged-creeper", Sound.ENTITY_CREEPER_PRIMED, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzUxMWU0YTNkNWFkZDZhNTQ0OTlhYmFkMTBkNzk5ZDA2Y2U0NWNiYTllNTIwYWZkMjAwODYwOGE2Mjg4YjdlNyJ9fX0");
    }

    @Override
    public boolean dropHead(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("charged-creeper"))) return false;
        Creeper creeper = (Creeper) event.getEntity();

        return creeper.isPowered();
    }
}
