package me.teakivy.teakstweaks.packs.wanderingtraderannouncements;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class WanderingTraderAnnouncements extends BasePack {
    private static final List<Location> traderLocations = new ArrayList<>();

    public WanderingTraderAnnouncements() {
        super("wandering-trader-announcements", Material.EMERALD);
    }

    @EventHandler
    public void onWanderingTraderSpawn(EntitySpawnEvent event) {
        if (event.getEntity().getType() != EntityType.WANDERING_TRADER) return;
        if (Config.isPackEnabled("wandering-trades")) return;

        int radius = getConfig().getInt("radius");
        if (radius < 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(getText("announcement_all"));
            }
            return;
        }

        event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), radius, radius, radius).forEach(entity -> {
            if (entity.getType() == EntityType.PLAYER) {
                entity.sendMessage(getText("announcement"));
            }
        });
    }
}
