package me.teakivy.teakstweaks.utils.lang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Objects;

public class TranslatableLanguage {
    private String lang;
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
        System.out.println("Modified: " + modified);

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

//        System.out.println(pluginMap.values());

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

        newMap.put("meta.version", pluginMap.get("meta.version"));

        map = newMap;
        save(map);

        Logger.log(Logger.LogLevel.INFO, "Updated language file: " + this.getFileName());
    }

    private void save(LinkedHashMap<String, Object> map) {
        System.out.println(map.get("meta.version"));
        System.out.println(map.get("meta.language_name"));
        System.out.println("Saving language file: " + this.getFileName());
        File file = new File(Main.getInstance().getDataFolder() + "/lang/" + this.lang + ".json");

        System.out.println("Exists: " + file.exists());
        try {
            FileWriter writer = new FileWriter(Main.getInstance().getDataFolder() + "/lang/" + this.lang + ".json");
            gson.toJson(map, writer);
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
            gson.toJson(map, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCreated() {
        return getFile().exists();
    }
}
