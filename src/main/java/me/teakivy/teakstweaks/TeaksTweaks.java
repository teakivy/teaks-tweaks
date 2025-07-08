package me.teakivy.teakstweaks;

import com.google.gson.Gson;
import me.teakivy.teakstweaks.craftingtweaks.CraftingRegister;
import me.teakivy.teakstweaks.utils.*;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.gui.GUIListener;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.metrics.Metrics;
import me.teakivy.teakstweaks.utils.papi.PAPIExpansion;
import me.teakivy.teakstweaks.utils.permission.PermissionManager;
import me.teakivy.teakstweaks.utils.recipe.RecipeManager;
import me.teakivy.teakstweaks.utils.update.UpdateChecker;
import me.teakivy.teakstweaks.utils.update.UpdateJoinAlert;
import me.teakivy.teakstweaks.utils.update.VersionManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static me.teakivy.teakstweaks.utils.metrics.CustomMetrics.registerCustomMetrics;

public final class TeaksTweaks extends JavaPlugin implements Listener {
    private final ArrayList<String> activePacks = new ArrayList<>();
    private final ArrayList<String> activeCraftingTweaks = new ArrayList<>();

    private Register register;

    /**
     * Called when the plugin is enabled
     */
    @Override
    public void onEnable() {

        VersionManager.init();
        // Initialize an audiences instance for the plugin
        // Credits
        createCredits();

        createDataFolders();

        PermissionManager.init();

        // Initialize & Update Config
        Config.init();

        // Language
        Translatable.init(getConfig().getString("settings.language"));

        // Update Checker
        if (Config.getBoolean("settings.disable-update-checker")) {
            Logger.info(Translatable.get("startup.update.disabled"));
        } else {
            getServer().getPluginManager().registerEvents(new UpdateJoinAlert(), this);
        }

        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getServer().getPluginManager().registerEvents(new RecipeManager(), this);

        Bukkit.getScheduler().runTaskLater(this, UpdateChecker::sendUpdateMessage, 20L * 3);

        // Crafting Tweaks
        CraftingRegister.init();
        CraftingRegister.registerAll();

        // Commands
        Register.registerCommands();

        // Plugin startup logic
        Logger.info(newText(" "));
        Logger.info(Translatable.get("startup.plugin.started", Placeholder.parsed("version", this.getDescription().getVersion())));
        Logger.info(newText(" "));

        // Packs
        register = new Register();
        register.registerAll();

        // Remove legacy data.yml file
        removeDataFile();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PAPIExpansion(this).register();
        }

        // Metrics
        Bukkit.getScheduler().runTaskLater(this, () -> {
            Metrics metrics = new Metrics(this, 12001);
            registerCustomMetrics(metrics);
        }, 20L * 30);
    }

    /**
     * Called when the plugin is disabled
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.info(Translatable.get("startup.plugin.shutting_down"));
    }

    /**
     * Clear the active packs list
     */
    public void clearPacks() {
        activePacks.clear();
    }

    /**
     * Add a pack to the active packs list
     * @param name Pack name
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
     * Create the credits file
     */
    private void createCredits() {
        try {
            new Credits().createCredits();
        } catch (IOException ignored) {}
    }

    /**
     * Get the Gson instance from JsonManager
     * @return Gson instance
     */
    public static Gson getGson() {
        return JsonManager.getGson();
    }

    /**
     * Remove the data.yml file as it is deprecated
     */
    private void removeDataFile() {
        File file = new File(getDataFolder(), "data.yml");
        if (file.exists()) {
            file.delete();
        }
    }

    public static Component newText(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }

    public void createDataFolders() {
        File dataFolder = new File(getDataFolder(), "data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File packsFolder = new File(getDataFolder(), "lang");
        if (!packsFolder.exists()) {
            packsFolder.mkdirs();
        }
    }

}