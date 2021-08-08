package me.teakivy.vanillatweaks.Messages;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.DataManager.DataManager;
import org.bukkit.ChatColor;

import java.util.Objects;

public class MessageHandler {
    static Main main = Main.getPlugin(Main.class);
    static DataManager messageManager = main.data;

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

}
