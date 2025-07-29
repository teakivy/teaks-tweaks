package me.teakivy.teakstweaks.packs.coordshud;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CoordsHud extends BasePack {

    public CoordsHud() {
        super(TTPack.COORDS_HUD, Material.OBSERVER);
        startHUD();
    }

    static boolean running = false;
    static int taskID = -1;

    public static void startHUD() {
        if (taskID != -1) return;
        running = true;
        CoordsHudDisplay.init();
        new Thread(() -> {
            taskID = Bukkit.getScheduler().runTaskTimer(TeaksTweaks.getInstance(), () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!isEnabled(player)) continue;
                    CoordsHudDisplay.showHud(player);
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

    public static boolean isEnabled(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();

        if (data.has(Key.get("ch_enabled"), PersistentDataType.BOOLEAN)) {
            return data.get(Key.get("ch_enabled"), PersistentDataType.BOOLEAN);
        }

        setEnabled(player, Config.getBoolean("packs.coords-hud.auto-enable") ||
                Config.getBoolean("packs.coords-hud.force-enable"));
        return isEnabled(player);
    }

    public static void setEnabled(Player player, boolean enabled) {
        if (Config.getBoolean("packs.coords-hud.force-enable")) enabled = true;
        PersistentDataContainer data = player.getPersistentDataContainer();

        data.set(Key.get("ch_enabled"), PersistentDataType.BOOLEAN, enabled);
    }
}
