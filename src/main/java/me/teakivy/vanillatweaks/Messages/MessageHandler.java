package me.teakivy.vanillatweaks.Messages;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.DataManager.MessageManager;

public class MessageHandler {
    static Main main = Main.getPlugin(Main.class);
    static MessageManager messageManager = main.messages;

    public static String getMessage(String path) {
        if (messageManager.getConfig().contains(path)) {
            return messageManager.getConfig().getString(path);
        }
        return "";
    }

}
