package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.datamanager.DataManager;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Objects;

public class MessageHandler {
    static DataManager messageManager = Main.getInstance().data;

    public static String getString(String path) {
        if (messageManager.getConfig().contains("messages." + path)) {
            String str = messageManager.getConfig().getString("messages." + path);
            str = str.replace("%prefix%", "");
            str = str.replace("%bland_prefix%", "");

            return str;
        }
        return "";
    }

    public static String getMessage(String path) {
        if (messageManager.getConfig().contains("messages." + path)) {
            String str = messageManager.getConfig().getString("messages." + path);
            str = str.replace("%prefix%", "");
            str = str.replace("%bland_prefix%", "");

            return ChatColor.translateAlternateColorCodes('&', str);
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
            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(
                    messageManager.getConfig().getString("commands." + cmd + ".messages." + path)
                            .replace("%prefix%", "")
                            .replace("%bland_prefix%", "")
            ));
        }
        return "";
    }

}
