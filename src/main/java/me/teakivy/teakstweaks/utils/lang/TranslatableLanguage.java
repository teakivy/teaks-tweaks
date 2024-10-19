package me.teakivy.teakstweaks.utils.lang;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.config.Config;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.io.*;
import java.util.LinkedHashMap;

public class TranslatableLanguage {
    private final String lang;
    public LinkedHashMap<String, Object> map = new LinkedHashMap<>();

    /**
     * Creates a new TranslatableLanguage object
     * @param lang The language code
     */
    public TranslatableLanguage(String lang) {
        this.lang = lang;

        if (!getFile().exists() && Translatable.isPluginLanguage(lang)) {
            TeaksTweaks.getInstance().saveResource("lang/" + lang + ".json", false);
        }
    }

    /**
     * Gets the language code
     * @return The language code
     */
    public String getLang() {
        return lang;
    }

    /**
     * Gets the file name of the language file
     * @return The file name
     */
    public String getFileName() {
        return lang + ".json";
    }

    /**
     * Gets the language file
     * @return The language file
     */
    public File getFile() {
        return new File(TeaksTweaks.getInstance().getDataFolder() + "/lang/" + this.lang + ".json");
    }

    /**
     * Gets a string from the language file
     * @param key The key
     * @return The string
     */
    public String get(String key) {
        if (map.containsKey(key)) {
            return (String) map.get(key);
        } else {
            return key;
        }
    }

    /**
     * Gets the string name of the language
     * @return The string name of the language
     */
    public String getName() {
        if (map.containsKey("meta.language_name")) return (String) map.get("meta.language_name");

        return lang;
    }

    /**
     * Loads the language file
     */
    public void load() {
        map = Translatable.getLanguageMapFromResource(this.lang);
        if (map == null) {
            Logger.warning("Failed to load language file: " + this.lang);
            create();

            if (map == null) {
                Logger.error("Failed to create language file: " + this.lang);
                return;
            }

            Logger.success("Created language file " + this.lang + ".json using en.json as a template");
        }

        Logger.info("Loaded language file: " + this.getFileName());

        update();
    }

    /**
     * Updates the language file
     */
    public void update() {
        LinkedHashMap<String, Object> pluginMap = Translatable.getLanguageMapFromPlugin(this.lang);
        boolean modified = (map.get("meta.modified") != null
                && (boolean) map.get("meta.modified"))
                || !Translatable.isPluginLanguage(this.lang);

        if (pluginMap == null) {
            pluginMap = Translatable.getLanguageMapFromPlugin("en");

            if (pluginMap == null) {
                Logger.error("Failed to update language file: " + this.lang);
                return;
            }
        }

        if (!modified) {
            TeaksTweaks.getInstance().saveResource("lang/" + this.lang + ".json", true);
            map = pluginMap;
            return;
        }

        LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();

        for (String key : pluginMap.keySet()) {
            if (map.containsKey(key)) {
                newMap.put(key, map.get(key));
            } else {
                newMap.put(key, pluginMap.get(key));
            }
        }

        for (String key : map.keySet()) {
            if (!pluginMap.containsKey(key)) continue;
            if (newMap.containsKey(key)) continue;
            newMap.put(key, map.get(key));
        }

        newMap.put("meta.version", Config.getVersion());

        map = newMap;
        save(map);

        Logger.info(MiniMessage.miniMessage().deserialize("<yellow>Updated language file: " + this.getFileName()));
    }

    /**
     * Saves the language file
     * @param map The language map
     */
    private void save(LinkedHashMap<String, Object> map) {
        try {
            FileWriter writer = new FileWriter(TeaksTweaks.getInstance().getDataFolder() + "/lang/" + this.lang + ".json");
            TeaksTweaks.getGson().toJson(map, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a custom language file
     */
    private void create() {
        if (isCreated()) return;
        try {
            FileWriter writer = new FileWriter(getFile());
            map = Translatable.getLanguageMapFromPlugin("en");
            map.put("meta.language_name", this.lang);
            map.put("meta.modified", true);
            TeaksTweaks.getGson().toJson(map, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the language file has been created
     * @return If the language file has been created
     */
    private boolean isCreated() {
        return getFile().exists();
    }
}
