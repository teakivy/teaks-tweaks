package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.homes.Home;
import me.teakivy.teakstweaks.packs.homes.HomesPack;
import me.teakivy.teakstweaks.utils.Wiki;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.log.PasteBookUploader;
import me.teakivy.teakstweaks.utils.log.PasteManager;
import me.teakivy.teakstweaks.utils.permission.Permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeaksTweaksCommand extends AbstractCommand {
    private static final int PASTE_COOLDOWN = 30 * 60 * 60 * 1000;
    private static long lastPaste = 0;


    public TeaksTweaksCommand() {
        super(CommandType.ALL, null, "teakstweaks", Permission.COMMAND_TEAKSTWEAKS, Arrays.asList("tweaks", "tt"), "teakstweakscommand", Arg.optional("info", "version", "support", "update", "wiki", "paste"), Arg.optional("packs", "craftingtweaks", "commands", "true", "false"));
    }

    @Override
    public void command(CommandEvent event) {
        if (!event.hasArgs()) {
            sendInfoMessage();
            return;
        }
        switch (event.getArg(0)) {
            case "info":
                sendInfoMessage();
                return;
            case "version":
                sendMessage("version", insert("version", TeaksTweaks.getInstance().getDescription().getVersion()));
                return;
            case "support":
                sendMessage("support", insert("discord", get("plugin.discord")));
                return;
            case "update":
                sendMessage("update", insert("url", get("plugin.url")));
                return;
            case "wiki":
                handleWiki(event);
                return;
            case "paste":
                handlePaste(event);
                return;
            default:
                sendUsage();
        }
    }

    public void sendInfoMessage() {
        sendMessage("info.dashed_line");
        sendText("");
        sendMessage("info.title", insert("version", TeaksTweaks.getInstance().getDescription().getVersion()));
        sendText("");
        sendMessage("info.author", insert("author", get("plugin.author")));
        sendMessage("info.config_version", insert("config_version", Config.getVersion()));
        sendMessage("info.config_generated", insert("config_generated", Config.getCreatedVersion()));
        if (Config.isDevMode()) {
            sendMessage("info.dev_mode_enabled");
        }
        sendMessage("info.support", insert("discord", get("plugin.discord")));
        sendText("");
        sendMessage("info.dashed_line");
    }

    public void handleWiki(CommandEvent event) {
        if (!event.isArg(1)) {
            sendMessage("wiki", insert("url", Wiki.getWiki()));
            return;
        }
        String type = event.getArg(1);
        switch (type) {
            case "packs":
                sendMessage("wiki", insert("url", Wiki.getWikiPage("Packs")));
                return;
            case "craftingtweaks":
                sendMessage("wiki", insert("url", Wiki.getWikiPage("Crafting-Tweaks")));
                return;
            case "commands":
                sendMessage("wiki", insert("url", Wiki.getWikiPage("Commands")));
                return;
            default:
                sendUsage();
        }
    }

    public void handlePaste(CommandEvent event) {
        System.out.println("Handling paste");
        if (!checkPermission(Permission.COMMAND_TEAKSTWEAKS_PASTE)) return;
        System.out.println("No permission");
        if (System.currentTimeMillis() - lastPaste < PASTE_COOLDOWN) {
            sendMessage("paste.error.cooldown");
            return;
        }
        System.out.println("No cooldown");

        sendMessage("paste.uploading");

        String playerName = event.getSender().getName();
        boolean logs = true;

        if (event.isArg(1)) {
            if (event.getArg(1).equalsIgnoreCase("false")) logs = false;
        }

        String paste = PasteManager.getPasteContent(playerName, logs);
        try {
            String url = PasteBookUploader.uploadText(paste, "Teak's Tweaks Support: " + playerName);
            sendMessage("paste.success", insert("url", url));
            lastPaste = System.currentTimeMillis();
        } catch (IOException e) {
            sendMessage("paste.error");
            e.printStackTrace();
        }
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArg(0)) return Arrays.asList("info", "version", "support", "update", "wiki", "paste");
        if (event.isArg(1) && event.isArg(0, "wiki")) return Arrays.asList("packs", "craftingtweaks", "commands");
        if (event.isArg(1) && event.isArg(0, "paste")) return Arrays.asList("true", "false");

        return null;
    }
}
