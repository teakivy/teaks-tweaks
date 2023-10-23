package me.teakivy.teakstweaks.packs.survival.coordshud;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class HUD extends BasePack {

    public HUD () {
        super("coords-hud", PackType.SURVIVAL, Material.OBSERVER);
        startHUD();
    }

    static boolean running = false;
    static int taskID = -1;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (getConfig().getBoolean("force-enable")) {
            if (!TeaksTweaks.chEnabled.contains(player.getUniqueId())) {
                TeaksTweaks.chEnabled.add(player.getUniqueId());
            }
        }
        if (getConfig().getBoolean("auto-enable") && !player.getScoreboardTags().contains("ch")) {
            player.addScoreboardTag("ch");
            TeaksTweaks.chEnabled.add(player.getUniqueId());
        }
    }

    public static void startHUD() {
        if (taskID != -1) return;
        running = true;
        DisplayHud.init();
        new Thread(() -> {
            taskID = Bukkit.getScheduler().runTaskTimer(teaksTweaks, () -> {
                for (UUID uuid : TeaksTweaks.chEnabled) {
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
