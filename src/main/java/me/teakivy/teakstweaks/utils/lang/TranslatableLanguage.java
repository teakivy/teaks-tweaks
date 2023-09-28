package me.teakivy.teakstweaks.utils.lang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.internal.LinkedTreeMap;
import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class TranslatableLanguage {
    private String lang;
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public HashMap<String, Object> map = new HashMap<>();
    private HashMap<String, Object> resourceMap;
    public TranslatableLanguage(String lang) {
        this.lang = lang;

        resourceMap = getResource();

        try {
            load();
        } catch (FileNotFoundException e) {
            Logger.log(Logger.LogLevel.ERROR, "Could not find language file for " + lang + ". Using en_us.json instead.");
            try {
                this.lang = "en_us";
                load();
            } catch (FileNotFoundException e2) {
                Logger.log(Logger.LogLevel.ERROR, "Could not find language file for en_us.json. Please report this to the plugin developer.");
            }
        }
    }

    public String getLang() {
        return lang;
    }

    public File getFile() {
        return new File(Main.getInstance().getDataFolder(), "lang/" + lang + ".json");
    }

    public HashMap<String, Object> getResource() {
        InputStream initialStream = Main.getInstance().getResource("lang/" + lang + ".json");
        if (initialStream == null) initialStream = Main.getInstance().getResource("lang/en_us.json");

        try {
            initialStream = new ByteArrayInputStream(Objects.requireNonNull(initialStream).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Reader targetReader = new InputStreamReader(initialStream);

        return gson.fromJson(targetReader, HashMap.class);
    }

    public String get(String key) {
        if (map.containsKey(key)) {
            return (String) map.get(key);
        } else {
            return key;
        }
    }

    public boolean isKnownLanguage() {
        return Main.getInstance().getResource("lang/" + lang + ".json") != null;
    }

    public String getName() {
        if (map.containsKey("meta.language_name")) return (String) map.get("meta.language_name");

        return lang;
    }

    public void load() throws FileNotFoundException {
        File langFile = getFile();
        if (!langFile.exists()) {
            Main.getInstance().saveResource("lang/" + lang + ".json", false);
        }

        map = gson.fromJson(new FileReader(getFile()), HashMap.class);

        if (shouldUpdate()) {
            map = update();

            save();
        }
    }

    public boolean shouldUpdate() {
        if (Main.getInstance().devMode) return true;
        if (resourceMap.containsKey("meta.version")) {
            double version = (double) resourceMap.get("meta.version");
            if (map.containsKey("meta.version")) {
                double currentVersion = (double) map.get("meta.version");
                return version > currentVersion;
            }
        }

        return true;
    }

    public HashMap<String, Object> update() {
        HashMap<String, Object> newMap = resourceMap;
        boolean modified = !isKnownLanguage();
        if (newMap.containsKey("meta.modified")) {
            modified = (boolean) newMap.get("meta.modified");
        }

        if (!modified) {
            for (String key : newMap.keySet()) {
                if (map.containsKey(key)) {
                    newMap.put(key, map.get(key));
                }
            }
        }

        for (String s : newMap.keySet()) {
            if (!map.containsKey(s)) {
                map.put(s, newMap.get(s));
            }
        }

        for (String s : map.keySet()) {
            if (!newMap.containsKey(s)) {
                newMap.remove(s);
            }
        }
        return newMap;
    }

    private void save() {
        try {
            FileWriter writer = new FileWriter(getFile());
            gson.toJson(map, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
