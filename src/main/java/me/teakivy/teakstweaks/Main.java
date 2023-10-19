package me.teakivy.teakstweaks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.teakivy.teakstweaks.craftingtweaks.CraftingRegister;
import me.teakivy.teakstweaks.packs.hermitcraft.tag.Tag;
import me.teakivy.teakstweaks.utils.*;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import me.teakivy.teakstweaks.utils.gui.GUIListener;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

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


        // Language
        Translatable.init(getConfig().getString("settings.language"));

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
        Logger.log(Logger.LogLevel.INFO, Translatable.get("startup.plugin.started").replace("%version%", this.getDescription().getVersion()));
        Logger.log(Logger.LogLevel.INFO, "");

        // Packs
        register = new Register();
        register.registerAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.log(Logger.LogLevel.INFO, Translatable.get("startup.plugin.shutting_down"));

        try {
            data.saveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        String configVersion = this.getConfig().getString("config.version");
        String pluginConfigVersion = Objects.requireNonNull(this.getConfig().getDefaults()).getString("config.version");

        if (!devMode && !configVersion.equalsIgnoreCase(pluginConfigVersion)) return;

        try {
            ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.log(Logger.LogLevel.INFO, "Updated Plugin Config");
    }

    public static Gson getGson() {
        return JsonManager.getGson();
    }

}