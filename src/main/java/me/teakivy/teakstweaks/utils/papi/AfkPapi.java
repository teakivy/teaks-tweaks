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

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {

        if (player == null) return "";

        if (!plugin.getConfig().getBoolean("packs.afk-display.enabled", false)) {
            return null;
        }
        if (params.toLowerCase().equals("afkstatus")) {
            boolean isAfk = AFKManager.isAfk(player);

            String playerAfk = plugin.getConfig().getString("packs.afk-display.placeholder-player-afk");
            String playerNotAfk = plugin.getConfig().getString("packs.afk-display.placeholder-player-not-afk");

            return isAfk ? playerAfk : playerNotAfk;
        } else {
            return null;
        }
    }
}
