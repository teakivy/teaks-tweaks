package me.teakivy.teakstweaks.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Logger {
    /**
     * Logs a message to the console
     * @param level The log level
     * @param message The message
     * @param toAdmins If the message should be sent to admins in-game
     */
    public static void log(LogLevel level, String message, boolean toAdmins) {
        if (message == null) return;

        String prefix = "";
        switch (level) {
            case ERROR ->
                    prefix = "&8[&c&lERROR&r&8]";
            case WARNING ->
                    prefix = "&8[&6&lWARNING&r&8]";
            case INFO ->
                    prefix = "&8[&e&lINFO&r&8]";
            case SUCCESS ->
                    prefix = "&8[&a&lSUCCESS&r&8]";
            case OUTLINE ->
                    prefix = "&8[&7&lOUTLINE&r&8]";
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lTeaksTweaks&r&8] " + prefix + " &f" + message));

        if (!toAdmins) return;

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission("teakstweaks.manage")).forEach(player -> player.sendMessage(ChatColor.YELLOW + message));
    }

    /**
     * Logs a message to the console
     * @param level The log level
     * @param message The message
     */
    public static void log(LogLevel level, String message) {
        log(level, message, false);
    }

    /**
     * Logs an error message to the console
     * @param message The message
     */
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a warning message to the console
     * @param message The message
     */
    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    /**
     * Logs an info message to the console
     * @param message The message
     */
    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a success message to the console
     * @param message The message
     */
    public static void success(String message) {
        log(LogLevel.SUCCESS, message);
    }

    /**
     * Logs an outline message to the console
     * @param message The message
     */
    public static void outline(String message) {
        log(LogLevel.OUTLINE, message);
    }

    /**
     * Logger levels
     */
    public enum LogLevel { ERROR, WARNING, INFO, SUCCESS, OUTLINE }

}
