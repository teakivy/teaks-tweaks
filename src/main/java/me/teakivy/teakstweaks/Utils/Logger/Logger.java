package me.teakivy.teakstweaks.Utils.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {
    public static void log(LogLevel level, String message) {
        if (message == null) return;

        switch (level) {
            case ERROR:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c&lERROR&r&8] &f" + message));
                break;
            case WARNING:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lWARNING&r&8] &f" + message));
                break;
            case INFO:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&e&lINFO&r&8] &f" + message));
                break;
            case SUCCESS:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a&lSUCCESS&r&8] &f" + message));
                break;
            case OUTLINE:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7" + message));
                break;
        }
    }

    public enum LogLevel { ERROR, WARNING, INFO, SUCCESS, OUTLINE }

}
