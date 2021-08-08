package me.teakivy.vanillatweaks.Utils.Logger;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;

public class Log {

    static Main main = Main.getPlugin(Main.class);

    public static void message(String msg) {
        Bukkit.getLogger().info(msg);
    }

    public static void warning(String msg) {
        Bukkit.getLogger().warning(msg);
    }

    public static void info(String msg) {
        Bukkit.getLogger().info(msg);
    }

    public static void severe(String msg) {
        Bukkit.getLogger().severe(msg);
    }

}
