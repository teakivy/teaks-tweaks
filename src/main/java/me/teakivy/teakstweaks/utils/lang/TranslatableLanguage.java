package me.teakivy.teakstweaks.utils.lang;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;

import java.io.*;
import java.util.LinkedHashMap;

public class TranslatableLanguage {
    private String lang;
    public LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    public TranslatableLanguage(String lang) {
        this.lang = lang;

        if (!getFile().exists() && Translatable.isPluginLanguage(lang)) {
            Main.getInstance().saveResource("lang/" + lang + ".json", false);
        }
    }

    public String getLang() {
        return lang;
    }

    public String getFileName() {
        return lang + ".json";
    }

    public File getFile() {
        return new File(Main.getInstance().getDataFolder() + "/lang/" + this.lang + ".json");
    }

    public String get(String key) {
        if (map.containsKey(key)) {
            return (String) map.get(key);
        } else {
            return key;
        }
    }

    public String getName() {
        if (map.containsKey("meta.language_name")) return (String) map.get("meta.language_name");

        return lang;
    }

    public void load() {
        map = Translatable.getLanguageMapFromResource(this.lang);
        if (map == null) {
            Logger.log(Logger.LogLevel.WARNING, "Failed to load language file: " + this.lang);
            create();

            if (map == null) {
                Logger.log(Logger.LogLevel.ERROR, "Failed to create language file: " + this.lang);
                return;
            }

            Logger.log(Logger.LogLevel.SUCCESS, "Created language file " + this.lang + ".json using en.json as a template");
        }

        Logger.log(Logger.LogLevel.INFO, "Loaded language file: " + this.getFileName());

        update();
    }

    public void update() {
        LinkedHashMap<String, Object> pluginMap = Translatable.getLanguageMapFromPlugin(this.lang);
        boolean modified = (map.get("meta.modified") != null
                && (boolean) map.get("meta.modified"))
                || !Translatable.isPluginLanguage(this.lang);

        if (pluginMap == null) {
            pluginMap = Translatable.getLanguageMapFromPlugin("en");

            if (pluginMap == null) {
                Logger.log(Logger.LogLevel.ERROR, "Failed to update language file: " + this.lang);
                return;
            }
        }

        if (!modified) {
            Main.getInstance().saveResource("lang/" + this.lang + ".json", true);
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

        newMap.put("meta.version", Main.getInstance().getConfig().getString("config.version"));

        map = newMap;
        save(map);

        Logger.log(Logger.LogLevel.INFO, "Updated language file: " + this.getFileName());
    }

    private void save(LinkedHashMap<String, Object> map) {
        try {
            FileWriter writer = new FileWriter(Main.getInstance().getDataFolder() + "/lang/" + this.lang + ".json");
            Main.getGson().toJson(map, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void create() {
        if (isCreated()) return;
        try {
            FileWriter writer = new FileWriter(getFile());
            map = Translatable.getLanguageMapFromPlugin("en");
            map.put("meta.language_name", this.lang);
            map.put("meta.modified", true);
            Main.getGson().toJson(map, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCreated() {
        return getFile().exists();
    }
}
