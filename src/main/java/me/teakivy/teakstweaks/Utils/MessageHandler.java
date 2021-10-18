package me.teakivy.teakstweaks.Utils;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.DataManager.DataManager;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Objects;

public class MessageHandler {
    static Main main = Main.getPlugin(Main.class);
    static DataManager messageManager = main.data;
    public static String prefix = messageManager.getConfig().getString("messages.plugin.message-prefix");

    public static String getString(String path) {
        if (messageManager.getConfig().contains("messages." + path)) {
            return messageManager.getConfig().getString("messages." + path);
        }
        return "";
    }

    public static String getMessage(String path) {
        if (messageManager.getConfig().contains("messages." + path)) {
            String msg = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(messageManager.getConfig().getString("messages." + path)));
            msg = replace(msg, "%bland_prefix%", messageManager.getConfig().getString("messages.plugin.bland-prefix"));
            msg = replace(msg, "%prefix%", messageManager.getConfig().getString("messages.plugin.message-prefix"));
            return msg;
        }
        return "";
    }

    public static String replace(String str, String oldStr, String newStr) {
        str = str.replace(oldStr, ChatColor.translateAlternateColorCodes('&', newStr));
        return str;
    }

    public static String getCmdName(String cmd) {
        if (messageManager.getConfig().contains("commands." + cmd + ".name")) {
            return messageManager.getConfig().getString("commands." + cmd + ".name");
        }
        return "";
    }

    public static String getCmdUsage(String cmd) {
        if (messageManager.getConfig().contains("commands." + cmd + ".usage")) {
            return messageManager.getConfig().getString("commands." + cmd + ".usage");
        }
        return "";
    }

    public static String getCmdDescription(String cmd) {
        if (messageManager.getConfig().contains("commands." + cmd + ".description")) {
            return messageManager.getConfig().getString("commands." + cmd + ".description");
        }
        return "";
    }

    public static List<String> getCmdAliases(String cmd) {
        if (messageManager.getConfig().contains("commands." + cmd + ".name")) {
            return messageManager.getConfig().getStringList("commands." + cmd + ".aliases");
        }
        return null;
    }

    public static String getCmdMessage(String cmd, String path) {
        if (messageManager.getConfig().contains("commands." + cmd + ".messages." + path)) {
            String msg = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(messageManager.getConfig().getString("commands." + cmd + ".messages." + path)));
            msg = replace(msg, "%bland_prefix%", messageManager.getConfig().getString("messages.plugin.bland-prefix"));
            msg = replace(msg, "%prefix%", messageManager.getConfig().getString("messages.plugin.message-prefix"));
            return msg;
        }
        return "";
    }

}
