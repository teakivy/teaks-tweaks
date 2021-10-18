package me.teakivy.teakstweaks;

import me.teakivy.teakstweaks.CraftingTweaks.CraftingRegister;
import me.teakivy.teakstweaks.Events.UpdateJoinAlert;
import me.teakivy.teakstweaks.Packs.Hermitcraft.Tag.Tag;
import me.teakivy.teakstweaks.Utils.ConfigUpdater.ConfigUpdater;
import me.teakivy.teakstweaks.Utils.Credits;
import me.teakivy.teakstweaks.Utils.DataManager.DataManager;
import me.teakivy.teakstweaks.Utils.Logger.Log;
import me.teakivy.teakstweaks.Utils.Logger.Logger;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import me.teakivy.teakstweaks.Utils.Metrics.Metrics;
import me.teakivy.teakstweaks.Utils.Register.Register;
import me.teakivy.teakstweaks.Utils.UpdateChecker.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static me.teakivy.teakstweaks.Utils.Metrics.CustomMetrics.registerCustomMetrics;

public final class Main extends JavaPlugin implements Listener {

    public static ArrayList<UUID> chEnabled = new ArrayList<>();
    public Boolean newVersionAvaliable = false;
    public String latestVTVersion;

    public DataManager data;


    public Tag tagListener;

    @Override
    public void onEnable() {

        // Data Manager
        this.data = new DataManager(this);
        data.saveDefaultConfig();

        // Data updater
//        try {
//            ConfigUpdater.update(this, "data.yml", new File(this.getDataFolder(), "data.yml"), Collections.emptyList(), false);
//            this.reloadConfig();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        data.saveDefaultConfig();

        if (getConfig().getBoolean("config.dev-mode")) {
            Log.message(MessageHandler.getMessage("plugin.startup.dev-mode-enabled"));
        }

        // Credits
        try {
            new Credits().createCredits();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Metrics
        Metrics metrics = new Metrics(this, 12001);
        registerCustomMetrics(metrics);

        // Update Config.yml
        if (!getConfig().getBoolean("config.dev-mode")) {
            if (this.getConfig().getInt("config.version") < Objects.requireNonNull(this.getConfig().getDefaults()).getInt("config.version")) {
                try {
                    ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList(), true);
                    this.reloadConfig();
                    Log.message(MessageHandler.getMessage("plugin.startup.updated-config-version").replace("%config_version%", String.valueOf(this.getConfig().getInt("config.version"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList(), true);
                this.reloadConfig();
                Log.message(MessageHandler.getMessage("plugin.startup.updated-config-version").replace("%config_version%", String.valueOf(this.getConfig().getInt("config.version"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Update Checker
        getServer().getPluginManager().registerEvents(new UpdateJoinAlert(), this);

        String latestVersion = null;
        try {
            latestVersion = new UpdateChecker(this, 94021).getLatestVersion();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Logger.log(Logger.LogLevel.ERROR, MessageHandler.getMessage("plugin.error.cant-get-latest"));
        }
        String thisVersion = this.getDescription().getVersion();
        if (!thisVersion.equalsIgnoreCase(latestVersion)) {
            Logger.log(Logger.LogLevel.WARNING, MessageHandler.getMessage("plugin.startup.update-available").replace("%latest_version%", latestVersion));
            newVersionAvaliable = true;
            latestVTVersion = latestVersion;
        }


        // Crafting Tweaks
        CraftingRegister.register();

        // Commands
        Register.registerCommands();

        // Config
        this.saveDefaultConfig();

        boolean displayedFirstSpace = false;

        for (String pack : this.getConfig().getConfigurationSection("packs").getKeys(false)) {
            if (this.getConfig().getBoolean("packs." + pack + ".enabled")) {
                if (!displayedFirstSpace) Log.message("");
                displayedFirstSpace = true;

                Log.message(MessageHandler.getMessage("plugin.startup.pack-enabled").replace("%pack%", getPackName(pack)));
            }
        }
        if (displayedFirstSpace) Log.message("");

        tagListener = new Tag();

        // Coords HUD
        for (String uuid : data.getConfig().getStringList("chEnabled")) {
            chEnabled.add(UUID.fromString(uuid));
        }

        // Plugin startup logic
        Log.message(MessageHandler.getMessage("plugin.startup.plugin-started"));

        // Packs
        Register.registerAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Log.message(MessageHandler.getMessage("plugin.shutdown.plugin-shutdown"));

        data.reloadConfig();
        List<String> list = new ArrayList<>();
        for (UUID uuid : chEnabled) {
            list.add(uuid.toString());
        }
        data.getConfig().set("chEnabled", list);
        try {
            data.saveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPackName(String pack) {
        if (!data.getConfig().contains("messages.pack." + pack)) return pack;
        return ChatColor.translateAlternateColorCodes('&', MessageHandler.getMessage("pack." + pack + ".name"));
    }
}