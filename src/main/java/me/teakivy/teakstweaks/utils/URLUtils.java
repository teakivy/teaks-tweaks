package me.teakivy.teakstweaks.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class URLUtils {

    public static String getDiscord() {
        return "https://discord.gg/wfP4SkZx6s";
    }

    public static String getModrinth() {
        return "https://modrinth.com/plugin/teaks-tweaks";
    }

    public static Component clickable(String url) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize("<click:open_url:" + url + ">" + url + "</click>");
    }

    public static Component copyable(String text) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize("<click:copy_to_clipboard:" + text + ">" + text + "</click>");
    }
}
