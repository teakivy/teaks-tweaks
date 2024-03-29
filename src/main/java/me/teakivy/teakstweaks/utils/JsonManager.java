package me.teakivy.teakstweaks.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.teakivy.teakstweaks.TeaksTweaks;

import java.io.*;
import java.util.LinkedHashMap;

public class JsonManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Gson getGson() {
        return gson;
    }

    public static LinkedHashMap<String, Object> getFromFile(String path) {
        return getFromFile(new File(TeaksTweaks.getInstance().getDataFolder() + "/" + path));
    }

    public static LinkedHashMap<String, Object> getFromFile(File file) {
        LinkedHashMap<String, Object> json;
        if (!file.exists()) return new LinkedHashMap<>();

        try {
            json = TeaksTweaks.getGson().fromJson(new FileReader(file), LinkedHashMap.class);
            return json;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedHashMap<>();
    }

    public static LinkedHashMap<String, Object> get(String path) {
        LinkedHashMap<String, Object> json = getFromFile(path);
        if (json == null) return getFromResource(path);
        return json;
    }

    public static LinkedHashMap<String, Object> getFromResource(String path) {
        return TeaksTweaks.getGson().fromJson(new InputStreamReader(TeaksTweaks.getInstance().getResource(path)), LinkedHashMap.class);
    }

    public static void saveToFile(LinkedHashMap<String, Object> json, String path) {
        saveToFile(json, new File(TeaksTweaks.getInstance().getDataFolder() + "/" + path));
    }

    public static void saveToFile(LinkedHashMap<String, Object> json, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            TeaksTweaks.getGson().toJson(json, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a json object with another json object
     * @param from The json object to update from
     * @param to The json object to update to
     * @param safe If the update should be safe (disable overriding existing values)
     * @return The updated json object
     */
    public static LinkedHashMap<String, Object> updateJson(LinkedHashMap<String, Object> from, LinkedHashMap<String, Object> to, boolean safe) {
        if (!safe) return to;
        LinkedHashMap<String, Object> newJson = new LinkedHashMap<>();

        for (String key : to.keySet()) {
            if (from.containsKey(key)) {
                newJson.put(key, from.get(key));
            } else {
                newJson.put(key, to.get(key));
            }
        }

        return newJson;
    }
}
