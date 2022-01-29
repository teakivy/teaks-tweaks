package me.teakivy.teakstweaks.Packs.Survival.CoordsHud;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Packs.BasePack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class HUD extends BasePack {

    public HUD () {
        super("Coords Hud", "coords-hud");
        startHUD();
    }

    static boolean running = false;
    static int taskID = -1;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (getConfig().getBoolean("force-enable")) {
            if (!Main.chEnabled.contains(player.getUniqueId())) {
                Main.chEnabled.add(player.getUniqueId());
            }
        }
        if (getConfig().getBoolean("auto-enable") && !player.getScoreboardTags().contains("vt_ch")) {
            player.addScoreboardTag("vt_ch");
            Main.chEnabled.add(player.getUniqueId());
        }
    }

    public static void startHUD() {
        if (taskID != -1) return;
        running = true;
        DisplayHud.init();
        new Thread(() -> {
            taskID = Bukkit.getScheduler().runTaskTimer(main, () -> {
                for (UUID uuid : Main.chEnabled) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player == null) continue;
                    if (player.isOnline()) DisplayHud.showHud(player);
                }
                if (!running) {
                    Bukkit.getScheduler().cancelTask(taskID);
                    taskID = -1;
                }
            }, 1, 1).getTaskId();
        }).start();
    }

    public static void stopHUD() {
        running = false;
        taskID = -1;
    }

    @Override
    public void unregister() {
        super.unregister();
        stopHUD();
    }
}
