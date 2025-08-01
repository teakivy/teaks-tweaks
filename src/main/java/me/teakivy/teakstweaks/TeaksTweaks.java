package me.teakivy.teakstweaks;

import com.google.gson.Gson;
import me.teakivy.teakstweaks.utils.*;
import me.teakivy.teakstweaks.utils.advancements.AdvancementManager;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.gui.GUIListener;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.metrics.Metrics;
import me.teakivy.teakstweaks.utils.papi.PAPIExpansion;
import me.teakivy.teakstweaks.utils.permission.PermissionManager;
import me.teakivy.teakstweaks.utils.recipe.RecipeManager;
import me.teakivy.teakstweaks.utils.register.Register;
import me.teakivy.teakstweaks.utils.update.UpdateChecker;
import me.teakivy.teakstweaks.utils.update.UpdateJoinAlert;
import me.teakivy.teakstweaks.utils.update.VersionManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.translation.Argument;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static me.teakivy.teakstweaks.utils.metrics.CustomMetrics.registerCustomMetrics;

public final class TeaksTweaks extends JavaPlugin implements Listener {
    private final ArrayList<String> activePacks = new ArrayList<>();
    private final ArrayList<String> activeCraftingTweaks = new ArrayList<>();

    private TranslationManager translationManager;
    private AdvancementManager advancementManager;

    @Override
    public void onLoad() {
        advancementManager = new AdvancementManager();
        advancementManager.load();
    }

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

        advancementManager.enable();

        translationManager = new TranslationManager(getDataFolder());
        translationManager.initialize();

        // Update Checker
        if (Config.getBoolean("settings.disable-update-checker")) {
            Logger.info(Component.translatable("startup.update.disabled"));
        } else {
            getServer().getPluginManager().registerEvents(new UpdateJoinAlert(), this);
        }

        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        getServer().getPluginManager().registerEvents(new RecipeManager(), this);

        Bukkit.getScheduler().runTaskLater(this, UpdateChecker::sendUpdateMessage, 20L * 3);


        // Commands
        Register.registerAll();

        // Plugin startup logic
        Logger.info(newText(" "));
        Logger.info(Component.translatable("startup.plugin.started", Argument.string("version", this.getDescription().getVersion())));
        Logger.info(newText(" "));


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
        Logger.info(Component.translatable("startup.plugin.shutting_down"));
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
     * Get the main instance
     * @return Main instance
     */
    public static TeaksTweaks getInstance() {
        return getPlugin(TeaksTweaks.class);
    }

    /**
     * Get the TranslationManager instance
     * @return TranslationManager instance
     */
    public static TranslationManager getTranslationManager() {
        return getInstance().translationManager;
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

    public static AdvancementManager getAdvancementManager() {
        return getInstance().advancementManager;
    }
}