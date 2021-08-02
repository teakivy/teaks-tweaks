package me.teakivy.vanillatweaks.Packs.Survival.CoordsHud;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class HUD implements Listener {
    static Main main = Main.getPlugin(Main.class);

    static boolean running = false;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (main.getConfig().getBoolean("packs.coords-hud.force-enable")) {
            if (!Main.chEnabled.contains(player.getUniqueId())) {
                Main.chEnabled.add(player.getUniqueId());
            }
        }
        if (main.getConfig().getBoolean("packs.coords-hud.auto-enable") && !player.getScoreboardTags().contains("vt_ch")) {
            player.addScoreboardTag("vt_ch");
            Main.chEnabled.add(player.getUniqueId());
        }
    }

    public static void startHUD() {
        running = true;
        doHUD();
    }

    public static void doHUD() {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            for (UUID uuid : Main.chEnabled) {
                Player player = Bukkit.getPlayer(uuid);
                if (player == null) continue;
                if (player.isOnline()) DisplayHud.showHud(player);
            }
            if (running) doHUD();
        }, 1L);
    }

    public static void stopHUD() {
        running = false;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }
}
