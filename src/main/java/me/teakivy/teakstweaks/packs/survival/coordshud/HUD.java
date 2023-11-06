package me.teakivy.teakstweaks.packs.survival.coordshud;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class HUD extends BasePack {

    public HUD () {
        super("coords-hud", PackType.SURVIVAL, Material.OBSERVER);
        startHUD();
    }

    static boolean running = false;
    static int taskID = -1;

    public static void startHUD() {
        if (taskID != -1) return;
        running = true;
        DisplayHud.init();
        new Thread(() -> {
            taskID = Bukkit.getScheduler().runTaskTimer(teaksTweaks, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!isEnabled(player)) continue;
                    DisplayHud.showHud(player);
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
