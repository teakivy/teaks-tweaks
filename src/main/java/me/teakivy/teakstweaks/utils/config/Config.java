package me.teakivy.teakstweaks.utils.config;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.Logger;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class Config {
    public static void init() {
        TeaksTweaks.getInstance().saveDefaultConfig();
        updateConfig();
    }

    public static ConfigurationSection get() {
        return TeaksTweaks.getInstance().getConfig();
    }

    public static boolean isPackEnabled(String pack) {
        return getBoolean("packs." + pack + ".enabled");
    }

    public static boolean isCraftingTweakEnabled(String tweak) {
        return getBoolean("crafting-tweaks." + tweak + ".enabled");
    }

    public static ConfigurationSection getPackConfig(String pack) {
        return get().getConfigurationSection("packs." + pack);
    }

    public static boolean isDevMode() {
        return getBoolean("config.dev-mode");
    }

    public static String getVersion() {
        return getString("config.version");
    }

    public static String getCreatedVersion() {
        return getString("config.created-version");
    }

    private static void updateConfig() {
        String configVersion = getString("config.version");
        String pluginConfigVersion = Objects.requireNonNull(TeaksTweaks.getInstance().getConfig().getDefaults()).getString("config.version");

        if (TeaksTweaks.getInstance().getConfig().getBoolean("config.dev-mode") && !configVersion.equalsIgnoreCase(pluginConfigVersion)) return;

        try {
            ConfigUpdater.update(TeaksTweaks.getInstance(), "config.yml", new File(TeaksTweaks.getInstance().getDataFolder(), "config.yml"), Collections.emptyList(), true);
        } catch (IOException ignored) {}

        Logger.info(MiniMessage.miniMessage().deserialize("<yellow>Updated Plugin Config"));
    }

    public static int getInt(String path) {
        return get().getInt(path);
    }

    public static boolean getBoolean(String path) {
        return get().getBoolean(path);
    }

    public static String getString(String path) {
        return get().getString(path);
    }
}
