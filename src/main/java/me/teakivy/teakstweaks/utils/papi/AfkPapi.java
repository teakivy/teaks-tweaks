package me.teakivy.teakstweaks.utils.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.afkdisplay.AFKManager;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class AfkPapi extends PlaceholderExpansion {

    private final TeaksTweaks plugin;

    public AfkPapi(TeaksTweaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "teakstweaks";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    private String getConfigValue(String path, String defaultValue) {
        return plugin.getConfig().getString("packs.afk-display." + path, defaultValue);
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {

        if (player == null) return ""; // Check that player isn't null

        if (!plugin.getConfig().getBoolean("packs.afk-display.enabled", false)) {
            return null; // AFK display is disabled
        }

        switch (params.toLowerCase()) {
            case "afkstatus":
                boolean isAfk = AFKManager.isAfk(player); // Using AFKManager

                // Get this from config using the new method
                String playerAfk = getConfigValue("player-afk", "afk");
                String playerNotAfk = getConfigValue("player-not-afk", "not afk");

                return isAfk ? playerAfk : playerNotAfk; // Return AFK status
            default:
                return null; // Return null for unknown parameters
        }
    }
}
