package me.teakivy.teakstweaks.packs.teakstweaks.quickcommands;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.Logger;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class QuickCommands extends BasePack {

    public QuickCommands() {
        super("Quick Commands", "quick-commands", PackType.TEAKSTWEAKS, Material.COMMAND_BLOCK, "Quickly execute long commands", "Command: /quickcommands");
    }

    @Override
    public void init() {
        super.init();

        if (config.getBoolean("gamemode")) {
            new AQuickCommand("gmc", "Change your gamemode to creative", "", true, null, List.of("gamemode creative")).register();
            new AQuickCommand("gms", "Change your gamemode to survival", "", true, null, List.of("gamemode survival")).register();
            new AQuickCommand("gma", "Change your gamemode to adventure", "", true, null, List.of("gamemode adventure")).register();
            new AQuickCommand("gmsp", "Change your gamemode to spectator", "", true, null, List.of("gamemode spectator")).register();
        }

        if (config.getBoolean("reply")) {
            new ReplyQuickCommand().register();
        }

        ConfigurationSection customCommands = config.getConfigurationSection("custom-commands");

        if (customCommands == null) return;

        for (String command : customCommands.getKeys(false)) {
            boolean enabled = customCommands.getBoolean(command + ".enabled", true);
            if (!enabled) continue;
            String description = customCommands.getString(command + ".description", "A Teak's Tweaks custom command");
            boolean requireOp = customCommands.getBoolean(command + ".require-op", false);
            List<String> aliases = customCommands.getStringList(command + ".aliases");
            List<String> toRun = customCommands.getStringList(command + ".commands");
            if (toRun.size() == 0) {
                Logger.log(Logger.LogLevel.ERROR, "Error in custom command '" + command + "': No commands to run");
                continue;
            }

            new AQuickCommand(command, description, null, requireOp, aliases, toRun).register();
        }


    }
}
