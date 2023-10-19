package me.teakivy.teakstweaks.utils.lang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.teakivy.teakstweaks.Main;
import org.bukkit.ChatColor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Translatable {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final List<TranslatableLanguage> languages = new ArrayList<>();

    private static TranslatableLanguage currentLanguage;

    public static void init(String lang) {
        languages.add(new TranslatableLanguage("en"));

//        for (TranslatableLanguage language : languages) {
//            language.update();
//        }

        if (isPluginLanguage(lang)) {
            currentLanguage = getLanguage(lang);
            currentLanguage.load();
            return;
        }

        currentLanguage = new TranslatableLanguage(lang);
        currentLanguage.load();
    }

    private static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static String get(String key) {
        return ChatColor.translateAlternateColorCodes('&', currentLanguage.get(key));
    }

    public static String getError(String key) {
        return ChatColor.translateAlternateColorCodes('&', currentLanguage.get("error." + key));
    }

    public static boolean isPluginLanguage(String lang) {
        lang = lang.replace(".json", "");
        for (TranslatableLanguage language : languages) {
            if (language.getLang().equals(lang)) return true;
        }
        return false;
    }

    public static boolean isResourceLanguage(String lang) {
        if (isResourceLanguage(lang)) return true;
        for (String file : listFilesUsingJavaIO(Main.getInstance().getDataFolder() + "/lang")) {
            if (file.replace(".json", "").equals(lang)) return true;
        }
        return false;
    }

    public static TranslatableLanguage getLanguage(String lang) {
        for (TranslatableLanguage language : languages) {
            if (language.getLang().equals(lang)) return language;
        }
        return null;
    }

    public static File getLanguageFileFromResource(String lang) {
        return new File(Main.getInstance().getDataFolder() + "/lang/" + lang + ".json");
    }

    public static LinkedHashMap<String, Object> getLanguageMapFromResource(String lang) {
        File file = new File(Main.getInstance().getDataFolder() + "/lang/" + lang + ".json");
        if (!file.exists()) return null;

        try {
            return gson.fromJson(new FileReader(file), LinkedHashMap.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static LinkedHashMap<String, Object> getLanguageMapFromPlugin(String lang) {
        InputStream initialStream = Main.getInstance().getResource("lang/" + lang + ".json");
        System.out.println(initialStream);
        if (initialStream == null) initialStream = Main.getInstance().getResource("lang/en.json");

        try {
            initialStream = new ByteArrayInputStream(Objects.requireNonNull(initialStream).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Reader targetReader = new InputStreamReader(initialStream);

        return gson.fromJson(targetReader, LinkedHashMap.class);
    }
}
