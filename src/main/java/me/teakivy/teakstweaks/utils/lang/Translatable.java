package me.teakivy.teakstweaks.utils.lang;

import me.teakivy.teakstweaks.TeaksTweaks;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

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
        languages.add(new TranslatableLanguage("de"));
        languages.add(new TranslatableLanguage("fr"));
        languages.add(new TranslatableLanguage("ru"));
        languages.add(new TranslatableLanguage("fi"));
        languages.add(new TranslatableLanguage("nl"));
        languages.add(new TranslatableLanguage("pl"));

        if (isPluginLanguage(lang)) {
            currentLanguage = getLanguage(lang);
            currentLanguage.load();
            return;
        }

        currentLanguage = new TranslatableLanguage(lang);
        currentLanguage.load();
    }

    /**
     * Gets a component from the language map
     * @param key The key
     * @param resolvers The resolvers
     * @return The component
     */
    public static Component get(String key, TagResolver... resolvers) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(currentLanguage.get(key), resolvers);
    }

    /**
     * Gets an error message
     * @param key The error key
     * @param resolvers The resolvers
     * @return The error message
     */
    public static Component getError(String key, TagResolver... resolvers) {
        return get("error." + key, resolvers);
    }

    public static String getString(String key) {
        return currentLanguage.get(key);
    }

    /**
     * If the language is a built-in language
     * @param lang The language code
     * @return If the language is a built-in language
     */
    public static boolean isPluginLanguage(String lang) {
        lang = lang.replace(".json", "");

        InputStream resource = TeaksTweaks.getInstance().getResource("lang/" + lang + ".json");
        return resource != null;
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
