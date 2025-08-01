package me.teakivy.teakstweaks.utils.metrics;

import me.teakivy.teakstweaks.utils.config.Config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CustomMetrics {
    private static final HashMap<String, Integer> enabledPacks = new HashMap<>();
    private static final HashMap<String, Integer> enabledCraftingTweaks = new HashMap<>();

    public static void addPackEnabled(String pack) {
        enabledPacks.put(pack, 1);
    }

    public static void addCraftingTweakEnabled(String tweak) {
        enabledCraftingTweaks.put(tweak, 1);
    }

    public static void registerCustomMetrics(Metrics metrics) {
        registerPackMetrics(metrics);
        registerCraftingTweaksMetrics(metrics);
        registerFirstUsedMetrics(metrics);
        registerDevModeMetrics(metrics);

        metrics.addCustomChart(new Metrics.AdvancedPie("server-language", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String, Integer> valueMap = new HashMap<>();
                String languageCode = Config.getLanguage();
                String language = "Unknown";
                valueMap.put(language + " ( " + languageCode + " )", 1);

                return valueMap;
            }
        }));
    }

    public static void registerPackMetrics(Metrics metrics) {
        metrics.addCustomChart(new Metrics.AdvancedPie("packs", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                return enabledPacks;
            }
        }));
    }

    public static void registerCraftingTweaksMetrics(Metrics metrics) {
        metrics.addCustomChart(new Metrics.AdvancedPie("crafting_tweaks", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                return enabledCraftingTweaks;
            }
        }));
    }

    public static void registerFirstUsedMetrics(Metrics metrics) {
        metrics.addCustomChart(new Metrics.AdvancedPie("first_used", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String, Integer> valueMap = new HashMap<>();
                valueMap.put(Config.getCreatedVersion(), 1);

                return valueMap;
            }
        }));
    }

    public static void registerDevModeMetrics(Metrics metrics) {
        metrics.addCustomChart(new Metrics.AdvancedPie("dev_mode", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String, Integer> valueMap = new HashMap<>();
                if (Config.isDevMode()) {
                    valueMap.put("Enabled", 1);
                } else {
                    valueMap.put("Disabled", 1);
                }

                return valueMap;
            }
        }));
    }

}
