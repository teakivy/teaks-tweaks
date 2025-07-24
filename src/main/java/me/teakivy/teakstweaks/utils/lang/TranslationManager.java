package me.teakivy.teakstweaks.utils.lang;

import com.destroystokyo.paper.ClientOption;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.log.Logger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.minimessage.translation.MiniMessageTranslationStore;
import net.kyori.adventure.translation.GlobalTranslator;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TranslationManager {

    private final File dataFolder;
    private final List<String> supportedLanguages = List.of(
            "en_US", "de_DE", "fi_FI", "fr_FR", "nl_NL", "pl_PL", "ru_RU"
    );

    public TranslationManager(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    /**
     * Call this in onEnable().
     */
    public void initialize() {
        saveDefaultTranslations();
        loadTranslations();
    }

    /**
     * Copies default translations from JAR to disk if missing.
     */
    private void saveDefaultTranslations() {
        File translationsFolder = new File(dataFolder, "translations");
        if (!translationsFolder.exists()) {
            translationsFolder.mkdirs();
        }

        ClassLoader classLoader = getClass().getClassLoader();

        for (String lang : supportedLanguages) {
            File outFile = new File(translationsFolder, lang + ".properties");

            String resourcePath = "translations/" + lang + ".properties";
            Properties jarProps = loadPropertiesFromJar(resourcePath);
            if (jarProps == null) {
                Logger.error(Component.text("Missing default translation resource: " + resourcePath));
                continue;
            }

            if (!outFile.exists()) {
                // If file doesn't exist, write JAR default
                savePropertiesToFile(jarProps, outFile, "Default translation generated");
                Logger.info(Component.text("Created default translation: " + outFile.getName()));
                continue;
            }

            // File exists, load it
            Properties diskProps = loadPropertiesFromDisk(outFile);
            if (diskProps == null) {
                Logger.error(Component.text("Failed to read existing translation: " + outFile.getName()));
                continue;
            }

            boolean isModified = Boolean.parseBoolean(diskProps.getProperty("meta.modified", "false"));

            if (!isModified) {
                // User has not modified → overwrite
                savePropertiesToFile(jarProps, outFile, "Overwritten with new default because meta.modified=false");
            } else {
                // User has modified → merge
                Properties merged = new Properties();

                // Always preserve meta.modified=true
                merged.setProperty("meta.modified", "true");

                // Add all keys from JAR
                for (String key : jarProps.stringPropertyNames()) {
                    if (key.equalsIgnoreCase("meta.modified")) continue;
                    if (diskProps.containsKey(key)) {
                        merged.setProperty(key, diskProps.getProperty(key));
                    } else {
                        merged.setProperty(key, jarProps.getProperty(key));
                    }
                }

                // Write back
                savePropertiesToFile(merged, outFile, "Merged with new keys from JAR; meta.modified kept on top");
                Logger.info(Component.text("Merged translation updates into: " + outFile.getName()));
            }
        }
    }

    /**
     * Loads all translations from the plugin's data folder and registers them with Adventure.
     */
    private void loadTranslations() {
        MiniMessageTranslationStore store = MiniMessageTranslationStore.create(Key.get("translations"));
        File translationsFolder = new File(dataFolder, "translations");

        for (String lang : supportedLanguages) {
            String[] parts = lang.split("_");
            String language = parts[0];
            String country = parts[1];
            Locale locale = new Locale(language, country);

            File translationFile = new File(translationsFolder, lang + ".properties");
            if (!translationFile.exists()) {
                Logger.error(Component.text("Translation file not found on disk: " + translationFile.getAbsolutePath()));
                continue;
            }

            Properties properties = new Properties();
            try (InputStreamReader reader = new InputStreamReader(new FileInputStream(translationFile), StandardCharsets.UTF_8)) {
                properties.load(reader);
            } catch (IOException e) {
                Logger.error(Component.text("Error reading translation file: " + translationFile.getAbsolutePath()));
                e.printStackTrace();
                continue;
            }

            Map<String, String> map = new HashMap<>();
            for (String key : properties.stringPropertyNames()) {
                map.put(key, properties.getProperty(key));
            }

            store.registerAll(locale, map);
            Logger.info(Component.text("Loaded translations for " + lang));
        }

        GlobalTranslator.translator().addSource(store);
        Logger.info(Component.text("All translations registered with GlobalTranslator."));
    }

    private Properties loadPropertiesFromJar(String resourcePath) {
        Properties props = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) return null;
            props.load(new InputStreamReader(in, StandardCharsets.UTF_8));
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Properties loadPropertiesFromDisk(File file) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(new InputStreamReader(fis, StandardCharsets.UTF_8));
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void savePropertiesToFile(Properties props, File file, String comments) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            // Optional comment header
            if (comments != null && !comments.isEmpty()) {
                writer.write("# " + comments);
                writer.newLine();
            }

            // Write meta.modified first
            String metaValue = props.getProperty("meta.modified", "false");
            writer.write("meta.modified=" + metaValue);
            writer.newLine();

            // Write all other keys in sorted order for consistency
            List<String> keys = new ArrayList<>(props.stringPropertyNames());
            Collections.sort(keys);
            for (String key : keys) {
                if (key.equalsIgnoreCase("meta.modified")) continue;
                writer.write(key + "=" + props.getProperty(key));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTranslationString(String localeName, String key) {
        if (key == null || localeName == null) {
            return null;
        }

        // Normalize locale name, e.g. "en_us" -> "en", "US"
        String[] parts = localeName.split("_");
        String language = parts[0];
        String country = (parts.length > 1) ? parts[1] : "";
        Locale locale = new Locale(language, country);

        // Create a translatable component
        TranslatableComponent translatable = Component.translatable(key);

        // Translate it
        Component resolved = GlobalTranslator.translator().translate(translatable, locale);
        TextComponent textComponent = (TextComponent) resolved;
        if (resolved == null) {
            return null;
        }
        return textComponent.content();
    }

    public static String getString(String localeName, String key) {
        TeaksTweaks.getInstance();
        String str = TeaksTweaks.getTranslationManager().getTranslationString(localeName, key);
        return str == null ? key : str;
    }

    public static String getString(Player player, String key) {
        TeaksTweaks.getInstance();
        String str = TeaksTweaks.getTranslationManager().getTranslationString(player.getClientOption(ClientOption.LOCALE), key);
        return str == null ? key : str;
    }
}