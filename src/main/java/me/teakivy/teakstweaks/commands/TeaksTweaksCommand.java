package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class TeaksTweaksCommand extends AbstractCommand {

    public TeaksTweaksCommand() {
        super(null, "teakstweaks", "/teakstweaks <info|version|support|update>", Arrays.asList("tweaks", "tt"), CommandType.ALL);
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sendUsage(sender);
            return;
        }

        switch (args[0]) {
            case "info":
                sendInfoMessage(sender);
                return;
            case "version":
                sender.sendMessage(getString("version").replace("%version%", TeaksTweaks.getInstance().getDescription().getVersion()));
                return;
            case "support":
                sender.sendMessage(getString("support", Placeholder.parsed("discord", getString("plugin.discord"))));
                return;
            case "update":
                sender.sendMessage(getString("update", Placeholder.parsed("url", getString("plugin.url"))));
                return;
            default:
                sendUsage(sender);
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length != 1) return null;

        return List.of("info", "version", "support", "update");
    }

    public void sendInfoMessage(CommandSender sender) {
        sender.sendMessage(getString("info.dashed_line"));
        sender.sendMessage("");
        sender.sendMessage(getString("info.title").replace("%version%", TeaksTweaks.getInstance().getDescription().getVersion()));
        sender.sendMessage("");
        sender.sendMessage(getString("info.author", Placeholder.parsed("author", getString("plugin.author"))));
        sender.sendMessage(getString("info.config_version").replace("%config_version%", TeaksTweaks.getInstance().getConfig().getString("config.version")));
        sender.sendMessage(getString("info.config_generated").replace("%config_generated%", TeaksTweaks.getInstance().getConfig().getString("config.created-version")));
        if (TeaksTweaks.getInstance().getConfig().getBoolean("config.dev-mode")) {
            sender.sendMessage(getString("info.dev_mode_enabled"));
        }
        sender.sendMessage(getString("info.support", Placeholder.parsed("discord", getString("plugin.discord"))));
        sender.sendMessage("");
        sender.sendMessage(getString("info.dashed_line"));
    }

    public String getString(String key) {
        return Translatable.getLegacy("teakstweakscommand." + key);
    }
}
