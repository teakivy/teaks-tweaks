package me.teakivy.teakstweaks;

import com.google.gson.Gson;
import me.teakivy.teakstweaks.craftingtweaks.CraftingRegister;
import me.teakivy.teakstweaks.packs.hermitcraft.tag.Tag;
import me.teakivy.teakstweaks.utils.*;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import me.teakivy.teakstweaks.utils.gui.GUIListener;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.metrics.Metrics;
import me.teakivy.teakstweaks.utils.update.UpdateChecker;
import me.teakivy.teakstweaks.utils.update.UpdateJoinAlert;
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

public final class TeaksTweaks extends JavaPlugin implements Listener {
    public DataManager data;

    private final ArrayList<String> activePacks = new ArrayList<>();
    private final ArrayList<String> activeCraftingTweaks = new ArrayList<>();

    private Register register;

    public Tag tagListener;
    public boolean devMode;

    /**
     * Called when the plugin is enabled
     */
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

        // Plugin startup logic
        Logger.info("");
        Logger.info(Translatable.get("startup.plugin.started").replace("%version%", this.getDescription().getVersion()));
        Logger.info("");

        // Packs
        register = new Register();
        register.registerAll();
    }

    /**
     * Called when the plugin is disabled
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.info(Translatable.get("startup.plugin.shutting_down"));

        try {
            data.saveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Clear the active packs list
     */
    public void clearPacks() {
        activePacks.clear();
    }

    /**
     * Add a pack to the active packs list
     * @param name
     */
    public void addPack(String name) {
        activePacks.add(name);
    }

    /**
     * Get the active packs list
     * @return ArrayList<String> of active packs
     */
    public ArrayList<String> getPacks() {
        return activePacks;
    }

    /**
     * Add a crafting tweak to the active crafting tweaks list
     * @param name Crafting tweak name
     */
    public void addCraftingTweaks(String name) {
        activeCraftingTweaks.add(name);
    }

    /**
     * Get the active crafting tweaks list
     * @return ArrayList<String> of active crafting tweaks
     */
    public ArrayList<String> getCraftingTweaks() {
        return activeCraftingTweaks;
    }

    /**
     * Get the register instance
     * @return Register instance
     */
    public static Register getRegister() {
        return TeaksTweaks.getInstance().register;
    }

    /**
     * Get the main instance
     * @return Main instance
     */
    public static TeaksTweaks getInstance() {
        return getPlugin(TeaksTweaks.class);
    }

    /**
     * Get the config section for a pack
     * @param pack Pack name
     * @return config: packs.[pack]
     */
    public static ConfigurationSection getPackConfig(String pack) {
        return getInstance().getConfig().getConfigurationSection("packs." + pack);
    }

    /**
     * Create the credits file
     */
    private void createCredits() {
        try {
            new Credits().createCredits();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the config.yml file
     */
    private void updateConfig() {
        String configVersion = this.getConfig().getString("config.version");
        String pluginConfigVersion = Objects.requireNonNull(this.getConfig().getDefaults()).getString("config.version");

        if (!devMode && !configVersion.equalsIgnoreCase(pluginConfigVersion)) return;

        try {
            ConfigUpdater.update(this, "config.yml", new File(this.getDataFolder(), "config.yml"), Collections.emptyList(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.info("Updated Plugin Config");
    }

    /**
     * Get the Gson instance from JsonManager
     * @return Gson instance
     */
    public static Gson getGson() {
        return JsonManager.getGson();
    }

}