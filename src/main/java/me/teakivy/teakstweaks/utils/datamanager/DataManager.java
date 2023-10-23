package me.teakivy.teakstweaks.utils.datamanager;

import me.teakivy.teakstweaks.TeaksTweaks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataManager {

    private TeaksTweaks plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    /**
     * Creates a new DataManager
     * @param plugin The plugin
     */
    public DataManager(TeaksTweaks plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    /**
     * Reloads the config
     */
    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("data.yml");

        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    /**
     * Gets the config
     * @return
     */
    public FileConfiguration getConfig() {
        if (this.dataConfig == null) reloadConfig();
        return this.dataConfig;
    }

    /**
     * Saves the config
     * @throws IOException If the config cannot be saved
     */
    public void saveConfig() throws IOException {
        if (this.dataConfig == null || this.configFile == null) return;
        this.getConfig().save(this.configFile);
    }

    /**
     * Saves the default config
     */
    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        if (!this.configFile.exists()) {
            this.plugin.saveResource("data.yml", false);
        }
    }

}
