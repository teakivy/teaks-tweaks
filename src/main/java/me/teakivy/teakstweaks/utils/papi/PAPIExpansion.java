package me.teakivy.teakstweaks.utils.papi;

import com.destroystokyo.paper.ClientOption;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.afkdisplay.AFKDisplay;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PAPIExpansion extends PlaceholderExpansion {

    private final TeaksTweaks plugin;

    public PAPIExpansion(TeaksTweaks plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "teakstweaks";
    }

    @Override
    @NotNull
    public String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors());
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
    public @NotNull String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) return "";
        if (identifier.equalsIgnoreCase("afk_status")) {
            return handleAFKStatus(player);
        }

        return "";
    }

    private String handleAFKStatus(Player player) {
        return AFKDisplay.isAFK(player) ?
                TranslationManager.getString(player.getClientOption(ClientOption.LOCALE), "afk_display.placeholder.afk") :
                TranslationManager.getString(player.getClientOption(ClientOption.LOCALE), "afk_display.placeholder._not_afk");
    }
}
