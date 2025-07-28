package me.teakivy.teakstweaks.packs.moremobheads.mobs.special;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.moremobheads.abstractions.SpecialMobHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class WitherHead extends SpecialMobHead {

    @EventHandler
    protected void playerKillEvent(EntityDeathEvent event) {
        if (event.getEntityType() != EntityType.WITHER) return;
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        if (!MoreMobHeads.shouldDrop(killer, "wither")) return;
        event.getDrops().add(MoreMobHeads.getHeadItem("wither", Sound.ENTITY_WITHER_SPAWN));
        MoreMobHeads.grant(killer, "wither");

        Random rand = new Random();
        int gen = rand.nextInt(4);
        switch (gen) {
            case 0:
                event.getDrops().add(MoreMobHeads.getHeadItem("wither_projectile", Sound.ENTITY_WITHER_SHOOT));
                MoreMobHeads.grant(killer, "wither_projectile");
                break;
            case 1:
                event.getDrops().add(MoreMobHeads.getHeadItem("blue_wither_projectile", Sound.ENTITY_WITHER_SHOOT));
                MoreMobHeads.grant(killer, "blue_wither_projectile");
                break;
        }
    }
}
