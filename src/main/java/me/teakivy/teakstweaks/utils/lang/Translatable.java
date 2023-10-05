package me.teakivy.teakstweaks.utils.lang;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;
import org.bukkit.ChatColor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Translatable {
    private static String lang = "en_us";
    private static final List<TranslatableLanguage> languages = new ArrayList<>();

    private static TranslatableLanguage currentLanguage;

    public static void init(String lang) {
        Translatable.lang = lang;
        languages.add(new TranslatableLanguage("en_us"));

        for (String file : listFilesUsingJavaIO(Main.getInstance().getDataFolder() + "/lang")) {
            boolean found = false;
            for (TranslatableLanguage language : languages) {
                if (file.contains(language.getLang())) {
                    found = true;
                    break;
                }
            }

            if (!found) languages.add(new TranslatableLanguage(file.replace(".json", "")));
        }

        for (TranslatableLanguage language : languages) {
            if (language.getLang().equals(lang)) {
                currentLanguage = language;
                break;
            }
        }

        if (currentLanguage == null) {
            currentLanguage = languages.get(0);
            Logger.log(
                    Logger.LogLevel.ERROR,
                    get("startup.lang.error.missing_language_file")
                            .replace("%lang%", lang)
                            .replace("%current_lang_name%", currentLanguage.getName())
                            .replace("%current_lang_key%", currentLanguage.getLang())
            );
        }

        if (Main.getInstance().devMode) {
            Logger.log(
                    Logger.LogLevel.INFO,
                    get("startup.lang.loaded")
                            .replace("%count%", String.valueOf(languages.size()))
            );
            Logger.log(
                    Logger.LogLevel.INFO,
                    get("startup.lang.current")
                            .replace("%current_lang_name%", currentLanguage.getName())
                            .replace("%current_lang_key%", currentLanguage.getLang())
            );
        }
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
}
