package me.teakivy.teakstweaks;

import me.teakivy.teakstweaks.CraftingTweaks.CraftingRegister;
import me.teakivy.teakstweaks.Packs.Hermitcraft.Tag.Tag;
import me.teakivy.teakstweaks.Utils.*;
import me.teakivy.teakstweaks.Utils.DataManager.DataManager;
import me.teakivy.teakstweaks.Utils.Logger.Log;
import me.teakivy.teakstweaks.Utils.Logger.Logger;
import me.teakivy.teakstweaks.Utils.Metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

import static me.teakivy.teakstweaks.Utils.Metrics.CustomMetrics.registerCustomMetrics;

public final class Main extends JavaPlugin implements Listener {

    public static ArrayList<UUID> chEnabled = new ArrayList<>();
    public Boolean newVersionAvaliable = false;
    public String latestVTVersion;

    public DataManager data;

    private ArrayList<String> activePacks = new ArrayList<>();
    private ArrayList<String> activeCraftingTweaks = new ArrayList<>();

    public Tag tagListener;

    @Override
    public void onEnable() {

        // 1.18 Warning
        if (Bukkit.getServer().getVersion().contains("1.18")) {
            getServer().getLogger().log(Level.WARNING, "This plugin has not been fully tested with 1.18, please report any bugs to the GitHub, or on our support Discord.\nhttps://github.com/teakivy/teaks-tweaks\nhttps://discord.io/teakivy");
        }

        // Data Manager
        this.data = new DataManager(this);
        data.saveDefaultConfig();

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
                    Log.message("Dev Mode Enabled!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList(), true);
                this.reloadConfig();
                Log.message("Updated Config to Version: " + this.getConfig().getInt("config.version"));
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
            Log.warning("[VT] Teaks Tweaks has an update!\nPlease update to the latest version (" + latestVersion + ")\n&ehttps://www.spigotmc.org/resources/teaks-tweaks.94021/");
            newVersionAvaliable = true;
            latestVTVersion = latestVersion;
        }


        // Crafting Tweaks
        CraftingRegister.registerAll();

        // Commands
        Register.registerCommands();

        // Config
        this.saveDefaultConfig();

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

    public void clearPacks() {
        activePacks.clear();
    }

    public void addPack(String name) {
        activePacks.add(name);
    }

    public ArrayList<String> getPacks() {
        return activePacks;
    }

    public void clearCraftingTweaks() {
        activeCraftingTweaks.clear();
    }

    public void addCraftingTweaks(String name) {
        activeCraftingTweaks.add(name);
    }

    public ArrayList<String> getCraftingTweaks() {
        return activeCraftingTweaks;
    }
}