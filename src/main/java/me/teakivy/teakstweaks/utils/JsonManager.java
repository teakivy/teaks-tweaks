package me.teakivy.teakstweaks.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.teakivy.teakstweaks.Main;

import java.io.*;
import java.util.LinkedHashMap;

public class JsonManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Gson getGson() {
        return gson;
    }

    public static LinkedHashMap<String, Object> getFromFile(String path) {
        return getFromFile(new File(Main.getInstance().getDataFolder() + "/" + path));
    }

    public static LinkedHashMap<String, Object> getFromFile(File file) {
        LinkedHashMap<String, Object> json;
        if (!file.exists()) return new LinkedHashMap<>();

        try {
            json = Main.getGson().fromJson(new FileReader(file), LinkedHashMap.class);
            return json;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new LinkedHashMap<>();
    }

    public static LinkedHashMap<String, Object> getFromResource(String path) {
        return Main.getGson().fromJson(new InputStreamReader(Main.getInstance().getResource(path)), LinkedHashMap.class);
    }

    public static void saveToFile(LinkedHashMap<String, Object> json, String path) {
        saveToFile(json, new File(Main.getInstance().getDataFolder() + "/" + path));
    }

    public static void saveToFile(LinkedHashMap<String, Object> json, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            Main.getGson().toJson(json, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
