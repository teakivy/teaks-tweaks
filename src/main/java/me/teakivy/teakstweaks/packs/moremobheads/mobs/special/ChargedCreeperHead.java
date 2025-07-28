package me.teakivy.teakstweaks.packs.moremobheads.mobs.special;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.moremobheads.abstractions.SpecialMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class ChargedCreeperHead extends SpecialMobHead {

    @EventHandler
    protected void playerKillEvent(EntityDeathEvent event) {
        if (event.getEntityType() != EntityType.CREEPER) return;
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        if (!MoreMobHeads.shouldDrop(killer, "charged_creeper")) return;
        Creeper creeper = (Creeper) event.getEntity();
        if (!creeper.isPowered()) return;
        event.getDrops().add(MoreMobHeads.getHeadItem("charged_creeper", Sound.ENTITY_LIGHTNING_BOLT_IMPACT));

        MoreMobHeads.grant(killer, "charged_creeper");
    }
}
