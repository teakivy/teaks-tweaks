package me.teakivy.teakstweaks.utils.lang;

import me.teakivy.teakstweaks.TeaksTweaks;
import org.bukkit.ChatColor;

import java.io.*;
import java.util.*;

public class Translatable {
    private static final List<TranslatableLanguage> languages = new ArrayList<>();

    private static TranslatableLanguage currentLanguage;

    /**
     * Initializes the language system
     * @param lang The language code
     */
    public static void init(String lang) {
        languages.add(new TranslatableLanguage("en"));

        if (isPluginLanguage(lang)) {
            currentLanguage = getLanguage(lang);
            currentLanguage.load();
            return;
        }

        currentLanguage = new TranslatableLanguage(lang);
        currentLanguage.load();
    }

    /**
     * Gets a string from the language map
     * @param key The key
     * @return The string
     */
    public static String get(String key) {
        return ChatColor.translateAlternateColorCodes('&', currentLanguage.get(key));
    }

    /**
     * Gets an error message
     * @param key The error key
     * @return The error message
     */
    public static String getError(String key) {
        return ChatColor.translateAlternateColorCodes('&', currentLanguage.get("error." + key));
    }

    /**
     * If the language is a built-in language
     * @param lang The language code
     * @return If the language is a built-in language
     */
    public static boolean isPluginLanguage(String lang) {
        lang = lang.replace(".json", "");
        for (TranslatableLanguage language : languages) {
            if (language.getLang().equals(lang)) return true;
        }
        return false;
    }

    /**
     * Gets a language object
     * @param lang The language code
     * @return The language object
     */
    public static TranslatableLanguage getLanguage(String lang) {
        for (TranslatableLanguage language : languages) {
            if (language.getLang().equals(lang)) return language;
        }
        return null;
    }

    /**
     * Gets the language map from the plugin's data folder
     * @param lang The language code
     * @return The language map
     */
    public static LinkedHashMap<String, Object> getLanguageMapFromResource(String lang) {
        File file = new File(TeaksTweaks.getInstance().getDataFolder() + "/lang/" + lang + ".json");
        if (!file.exists()) return null;

        try {
            return TeaksTweaks.getGson().fromJson(new FileReader(file), LinkedHashMap.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the language map from the plugin's resources
     * @param lang The language code
     * @return The language map
     */
    public static LinkedHashMap<String, Object> getLanguageMapFromPlugin(String lang) {
        InputStream initialStream = TeaksTweaks.getInstance().getResource("lang/" + lang + ".json");
        if (initialStream == null) initialStream = TeaksTweaks.getInstance().getResource("lang/en.json");

        try {
            initialStream = new ByteArrayInputStream(Objects.requireNonNull(initialStream).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Reader targetReader = new InputStreamReader(initialStream);

        return TeaksTweaks.getGson().fromJson(targetReader, LinkedHashMap.class);
    }
}
