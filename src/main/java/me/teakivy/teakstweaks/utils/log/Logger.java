package me.teakivy.teakstweaks.utils.log;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Logger {
    /**
     * Logs a message to the console
     * @param level The log level
     * @param message The message
     * @param toAdmins If the message should be sent to admins in-game
     */
    public static void log(LogLevel level, Component message, boolean toAdmins) {
        if (message == null) return;

        String text = "<dark_gray>[<gold><bold>TeaksTweaks</bold></gold>]<reset> " + getPrefix(level) + " <reset>" + MiniMessage.miniMessage().serialize(message);

        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(text));

        if (!toAdmins) return;

        Bukkit.getOnlinePlayers().stream().filter(Permission.MANAGE::check).forEach(player -> player.sendMessage(message));
    }

    public static void log(LogLevel level, String message, boolean toAdmins) {
        log(level, MiniMessage.miniMessage().deserialize(message), toAdmins);
    }

    private static String getPrefix(LogLevel level) {
        String prefix = "";
        switch (level) {
            case ERROR ->
                    prefix = "<dark_gray>[<red><bold>ERROR</bold></red>]";
            case WARNING ->
                    prefix = "<dark_gray>[<gold><bold>WARNING</bold></gold>]";
            case INFO ->
                    prefix = "<dark_gray>[<yellow><bold>INFO</bold></yellow>]";
            case SUCCESS ->
                    prefix = "<dark_gray>[<green><bold>SUCCESS</bold></green>]";
            case OUTLINE ->
                    prefix = "<dark_gray>[<gray><bold>OUTLINE</bold></gray>]";
        }
        return prefix;
    }

    /**
     * Logs a message to the console
     * @param level The log level
     * @param message The message
     */
    public static void log(LogLevel level, Component message) {
        log(level, message, false);
    }

    /**
     * Logs a message to the console
     * @param level The log level
     * @param message The message
     */
    @Deprecated
    public static void log(LogLevel level, String message) {
        log(level, message, false);
    }

    /**
     * Logs an error message to the console
     * @param message The message
     */
    public static void error(Component message) {
        log(LogLevel.ERROR, message);
    }


    /**
     * Logs an error message to the console
     * @param message The message
     */
    @Deprecated
    public static void error(String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Logs a warning message to the console
     * @param message The message
     */
    public static void warning(Component message) {
        log(LogLevel.WARNING, message);
    }

    /**
     * Logs a warning message to the console
     * @param message The message
     */
    @Deprecated
    public static void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    /**
     * Logs an info message to the console
     * @param message The message
     */
    public static void info(Component message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs an info message to the console
     * @param message The message
     */
    @Deprecated
    public static void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Logs a success message to the console
     * @param message The message
     */
    public static void success(Component message) {
        log(LogLevel.SUCCESS, message);
    }

    /**
     * Logs a success message to the console
     * @param message The message
     */
    @Deprecated
    public static void success(String message) {
        log(LogLevel.SUCCESS, message);
    }

    /**
     * Logs an outline message to the console
     * @param message The message
     */
    public static void outline(Component message) {
        log(LogLevel.OUTLINE, message);
    }

    /**
     * Logs an outline message to the console
     * @param message The message
     */
    @Deprecated
    public static void outline(String message) {
        log(LogLevel.OUTLINE, message);
    }

    /**
     * Logger levels
     */
    public enum LogLevel { ERROR, WARNING, INFO, SUCCESS, OUTLINE }


    public static String getLogMessagesAsString() {
        File file = TeaksTweaks.getInstance().getServer().getWorldContainer().toPath().resolve("logs/latest.log").toFile();
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
