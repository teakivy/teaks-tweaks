package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.Main;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class UpdateChecker {
    private final static int resourceId = 94021;

    public static String getLatestVersion() {
        String url = "https://api.spiget.org/v2/resources/" + resourceId + "/versions/latest";

        try {
            @SuppressWarnings("deprecation")
            String nameJson = IOUtils.toString(new URL(url));
            JSONObject latest = (JSONObject) JSONValue.parseWithException(nameJson);
            return latest.get("name").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean hasUpdate() {
        String latestVersion = getLatestVersion();
        if (latestVersion == null) return false;
        return !latestVersion.equals(Main.getInstance().getDescription().getVersion());
    }

    public static synchronized void update() throws IOException, InvalidPluginException, InvalidDescriptionException {
        Logger.log(Logger.LogLevel.INFO, "Downloading TeaksTweaksLoader.jar...", true);
        File file = FileDownloader.downloadFile("https://github.com/teakivy/TeaksTweaksLoader/releases/download/release/TeaksTweaksLoader.jar", "plugins/TeaksTweaksLoader.jar");

        try {
            Logger.log(Logger.LogLevel.INFO, "Loading Teak's Tweaks Loader...", true);
            Bukkit.getServer().getPluginManager().loadPlugin(file);
        } catch (InvalidPluginException | InvalidDescriptionException e) {
            throw new RuntimeException(e);
        }

        Plugin plugin = Bukkit.getPluginManager().getPlugin("TeaksTweaksLoader");
        if (plugin == null) return;
        Bukkit.getPluginManager().enablePlugin(plugin);
    }

    public static synchronized void deleteUpdater() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("TeaksTweaksLoader");
        if (plugin == null) return;

        Logger.log(Logger.LogLevel.INFO, "Disabling Teak's Tweaks Loader...", true);
        Bukkit.getServer().getPluginManager().disablePlugin(plugin);

        Logger.log(Logger.LogLevel.INFO, "Deleting TeaksTweaksLoader.jar...", true);
        File file = new File("plugins/TeaksTweaksLoader.jar");
        if (file.exists()) file.delete();

        Logger.log(Logger.LogLevel.INFO, ChatColor.GREEN + "Teak's Tweaks has been updated to v" + Main.getInstance().getDescription().getVersion() + "!");
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + "Teak's Tweaks has been updated to v" + Main.getInstance().getDescription().getVersion() + "!");
        }
    }

    public static void sendUpdateMessage() {
        if (hasUpdate()) {
            Logger.log(Logger.LogLevel.INFO, "A new version of Teak's Tweaks is available! (" + getLatestVersion() + ")");
            Logger.log(Logger.LogLevel.INFO, "You can run " + ChatColor.GOLD + "/teakstweaks update" + ChatColor.WHITE + " to update to the latest version.");
        }
    }

    public static void startLoop() {
        if (!Main.getInstance().getConfig().getBoolean("settings.auto-update")) return;
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            if (!hasUpdate()) return;

            Logger.log(Logger.LogLevel.INFO, "A new version of Teak's Tweaks has been detected! (" + getLatestVersion() + ")");
            Logger.log(Logger.LogLevel.INFO, "Starting update process...");
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(ChatColor.YELLOW + "A new version of Teak's Tweaks has been detected!");
                player.sendMessage(ChatColor.GOLD + "Starting update process...");
            }

            try {
                update();
            } catch (IOException | InvalidPluginException | InvalidDescriptionException e) {
                e.printStackTrace();

                Logger.log(Logger.LogLevel.ERROR, "An error occurred while updating Teak's Tweaks!");
                Logger.log(Logger.LogLevel.ERROR, "Please update manually.");
            }
        }, 20L * 30, 20L * 60 * Main.getInstance().getConfig().getInt("settings.update-check-interval"));
    }
}


