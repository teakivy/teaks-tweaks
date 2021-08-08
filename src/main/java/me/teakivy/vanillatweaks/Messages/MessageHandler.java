package me.teakivy.vanillatweaks.Messages;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.DataManager.DataManager;

public class MessageHandler {
    static Main main = Main.getPlugin(Main.class);
    static DataManager messageManager = main.data;

    public static String getMessage(String path) {
        if (messageManager.getConfig().contains("messages." + path)) {
            return messageManager.getConfig().getString("messages." + path);
        }
        return "";
    }

}
