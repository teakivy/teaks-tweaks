package me.teakivy.teakstweaks.Utils.DataManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.ChatColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

public class JSONDataManager {

    private File file;
    private JSONObject json;
    private JSONParser parser = new JSONParser();
    private HashMap<String, Object> defaults = new HashMap<String, Object>();

    public JSONDataManager(File file) {
        this.file = file;
        reload();
    }

    @SuppressWarnings("unchecked")
    public void reload() {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.print("{");
                pw.print("}");
                pw.flush();
                pw.close();
            }
            json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            defaults.put("WelcomeMessage", "Welcome to my server!");
            defaults.put("MyNumber", 1337);

            JSONObject myObject = new JSONObject();
            myObject.put("Test", "test");
            myObject.put("Test2", "test2");
            defaults.put("MyObject", myObject);

            JSONArray myArray = new JSONArray();
            myArray.add("Value1");
            myArray.add("Value2");
            defaults.put("MyArray", myArray);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public boolean save() {
        try {
            JSONObject toSave = new JSONObject();

            for (String s : defaults.keySet()) {
                Object o = defaults.get(s);
                if (o instanceof String) {
                    toSave.put(s, getString(s));
                } else if (o instanceof Double) {
                    toSave.put(s, getDouble(s));
                } else if (o instanceof Integer) {
                    toSave.put(s, getInteger(s));
                } else if (o instanceof JSONObject) {
                    toSave.put(s, getObject(s));
                } else if (o instanceof JSONArray) {
                    toSave.put(s, getArray(s));
                }
            }

            TreeMap<String, Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
            treeMap.putAll(toSave);

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = g.toJson(treeMap);

            FileWriter fw = new FileWriter(file);
            fw.write(prettyJsonString);
            fw.flush();
            fw.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String getRawData(String key) {
        return json.containsKey(key) ? json.get(key).toString()
                : (defaults.containsKey(key) ? defaults.get(key).toString() : key);
    }

    public String getString(String key) {
        return ChatColor.translateAlternateColorCodes('&', getRawData(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(getRawData(key));
    }

    public double getDouble(String key) {
        try {
            return Double.parseDouble(getRawData(key));
        } catch (Exception ex) { }
        return -1;
    }

    public double getInteger(String key) {
        try {
            return Integer.parseInt(getRawData(key));
        } catch (Exception ex) { }
        return -1;
    }

    public JSONObject getObject(String key) {
        return json.containsKey(key) ? (JSONObject) json.get(key)
                : (defaults.containsKey(key) ? (JSONObject) defaults.get(key) : new JSONObject());
    }

    public JSONArray getArray(String key) {
        return json.containsKey(key) ? (JSONArray) json.get(key)
                : (defaults.containsKey(key) ? (JSONArray) defaults.get(key) : new JSONArray());
    }

}