package me.teakivy.teakstweaks;

import me.teakivy.teakstweaks.craftingtweaks.CraftingRegister;
import me.teakivy.teakstweaks.packs.hermitcraft.tag.Tag;
import me.teakivy.teakstweaks.utils.*;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import me.teakivy.teakstweaks.utils.gui.GUIListener;
import me.teakivy.teakstweaks.utils.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static me.teakivy.teakstweaks.utils.metrics.CustomMetrics.registerCustomMetrics;

public final class Main extends JavaPlugin implements Listener {

    public static ArrayList<UUID> chEnabled = new ArrayList<>();

    public DataManager data;

    private final ArrayList<String> activePacks = new ArrayList<>();
    private final ArrayList<String> activeCraftingTweaks = new ArrayList<>();

    private Register register;

    public Tag tagListener;
    public boolean devMode;

    @Override
    public void onEnable() {
        this.devMode = getConfig().getBoolean("config.dev-mode");

        // Data Manager
        this.data = new DataManager(this);
        data.saveDefaultConfig();

        // Credits
        createCredits();

        // Metrics
        Metrics metrics = new Metrics(this, 12001);
        registerCustomMetrics(metrics);

        // Update Config.yml
        updateConfig();

        // Update Checker
        getServer().getPluginManager().registerEvents(new UpdateJoinAlert(), this);
        getServer().getPluginManager().registerEvents(new GUIListener(), this);

        Bukkit.getScheduler().runTaskLater(this, UpdateChecker::sendUpdateMessage, 20L * 3);


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
        Logger.log(Logger.LogLevel.INFO, "");
        Logger.log(Logger.LogLevel.INFO, "Teak's Tweaks Started!");
        Logger.log(Logger.LogLevel.INFO, "");

        // Packs
        register = new Register();
        register.registerAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.log(Logger.LogLevel.INFO, MessageHandler.getMessage("plugin.shutdown.plugin-shutdown"));

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

    public void addCraftingTweaks(String name) {
        activeCraftingTweaks.add(name);
    }

    public ArrayList<String> getCraftingTweaks() {
        return activeCraftingTweaks;
    }

    public static Register getRegister() {
        return Main.getInstance().register;
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

    public static ConfigurationSection getPackConfig(String pack) {
        return getInstance().getConfig().getConfigurationSection("packs." + pack);
    }

    private void createCredits() {
        try {
            new Credits().createCredits();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateConfig() {
        int configVersion = this.getConfig().getInt("config.version");
        int pluginConfigVersion = Objects.requireNonNull(this.getConfig().getDefaults()).getInt("config.version");

        if (!devMode && configVersion < pluginConfigVersion) return;

        try {
            ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.log(Logger.LogLevel.INFO, "Updated Config to Version: " + this.getConfig().getInt("config.version"));
    }

}