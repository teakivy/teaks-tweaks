package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.Arg;
import me.teakivy.teakstweaks.utils.command.CommandEvent;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.config.Config;

import java.util.Arrays;

public class TeaksTweaksCommand extends AbstractCommand {

    public TeaksTweaksCommand() {
        super(CommandType.ALL, null, "teakstweaks", Arrays.asList("tweaks", "tt"), "teakstweakscommand", Arg.required("info", "version", "support", "update"));
    }

    @Override
    public void command(CommandEvent event) {
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
}
