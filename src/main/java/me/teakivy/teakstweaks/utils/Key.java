package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.TeaksTweaks;
import org.bukkit.NamespacedKey;

public class Key {
    /**
     * Get a NamespacedKey
     * @param key The key
     * @return The NamespacedKey
     */
    public static NamespacedKey get(String key) {
        return new NamespacedKey(TeaksTweaks.getInstance(), key);
    }
}
