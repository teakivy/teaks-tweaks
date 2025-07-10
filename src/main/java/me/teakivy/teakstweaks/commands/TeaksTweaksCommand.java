package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Wiki;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.customitems.ItemHandler;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import me.teakivy.teakstweaks.utils.log.PasteUploader;
import me.teakivy.teakstweaks.utils.log.PasteManager;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.*;

public class TeaksTweaksCommand extends AbstractCommand {
    private static final int PASTE_COOLDOWN = 10 * 60 * 1000;
    private static long lastPaste = 0;


    public TeaksTweaksCommand() {
        super(CommandType.ALL, null, "teakstweaks", Permission.COMMAND_TEAKSTWEAKS, Arrays.asList("tweaks", "tt"), "teakstweakscommand", Arg.optional("info", "v", "version", "support", "update", "wiki", "paste", "give"), Arg.optional("packs", "craftingtweaks", "commands", "true", "false", "player"), Arg.optional("item"), Arg.optional("amount"));
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
            case "v", "version":
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
            case "give":
                handleGive(event);
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
        if (!checkPermission(Permission.COMMAND_TEAKSTWEAKS_PASTE)) return;
        if (System.currentTimeMillis() - lastPaste < PASTE_COOLDOWN && !Config.isDevMode()) {
            sendMessage("paste.error.cooldown", insert("service_name", "Pastebin"));
            return;
        }

        sendMessage("paste.uploading", insert("service_name", "Pastebin"));

        String playerName = event.getSender().getName();
        boolean logs = Config.getBoolean("settings.send-log-in-paste");

        if (event.isArg(1)) {
            if (event.getArg(1).equalsIgnoreCase("false")) logs = false;
            if (event.getArg(1).equalsIgnoreCase("true")) logs = true;
        }

        String paste = PasteManager.getPasteContent(playerName, logs);
        try {
            String url = PasteUploader.uploadToPastebin(paste, "Support: " + playerName);
            lastPaste = System.currentTimeMillis();
            if (event.getSender() instanceof Player) {
                sendMessage("paste.success", insert("url", url), insert("service_name", "Pastebin"));
                return;
            }
            sendMessage("paste.success.console", insert("url", url), insert("service_name", "Pastebin"));
        } catch (IOException e) {
            sendMessage("paste.error");
            e.printStackTrace();
        }
    }

    public void handleGive(CommandEvent event) {
        if (!checkPermission(Permission.COMMAND_TEAKSTWEAKS_GIVE)) return;
        if (!event.hasArgs(2) && !event.isArg(3)) {
            sendUsage();
            return;
        }
        String playerName = event.getArg(1);
        String itemName = event.getArg(2);
        int amount = 1;
        if (event.isArg(3)) {
            try {
                amount = Integer.parseInt(event.getArg(3));
            } catch (NumberFormatException e) {
                sendUsage();
                return;
            }
        }

        if (amount < 1) {
            sendMessage("give.error.amount.lt_1");
            return;
        }

        if (amount > 6400) {
            sendMessage("give.error.amount.gt_6400");
            return;
        }

        List<Player> players = new ArrayList<>();
        switch (playerName) {
            case "@a":
                players.addAll(Bukkit.getOnlinePlayers());
                break;
            case "@s":
                if (event.getSender() instanceof Player) {
                    players.add((Player) event.getSender());
                } else {
                    sendError(ErrorType.NOT_PLAYER);
                    return;
                }
                break;
            case "@r":
                players.add(Bukkit.getOnlinePlayers().stream().findAny().orElse(null));
                break;
            default:
                Player player = Bukkit.getPlayer(playerName);
                if (player == null) {
                    sendError(ErrorType.PLAYER_DNE);
                    return;
                }
                players.add(player);
        }

        ItemStack item = ItemHandler.getItem(itemName);
        if (item == null) {
            sendMessage("give.unknown_item", insert("item", itemName));
            return;
        }


        for (Player player : players) {
            for (int i = 0; i < amount; i++) {
                player.getInventory().addItem(ItemHandler.getItem(itemName));
            }
        }

        String recipient = players.size() == 1 ? players.getFirst().getName() : Translatable.getString("teakstweakscommand.give.all_players");

        sendMessage("give.success", insert("amount", amount), insert("item", itemName), insert("player", recipient));
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArg(0)) return Arrays.asList("info", "version", "support", "update", "wiki", "paste", "give");
        if (event.isArg(1) && event.isArg(0, "wiki")) return Arrays.asList("packs", "craftingtweaks", "commands");
        if (event.isArg(1) && event.isArg(0, "paste")) return Arrays.asList("true", "false");

        if (event.isArg(0, "give")) {
            if (event.isArg(1)) {
                List<String> players = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    players.add(player.getName());
                }
                players.add("@a");
                players.add("@s");
                players.add("@r");
                return players;
            }
            if (event.isArg(2)) {
                return ItemHandler.getAllKeys();
            }
            if (event.isArg(3)) return List.of("<amount>");
        }

        return null;
    }
}
