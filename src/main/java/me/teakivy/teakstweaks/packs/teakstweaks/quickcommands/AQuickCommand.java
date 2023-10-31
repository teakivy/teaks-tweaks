package me.teakivy.teakstweaks.packs.teakstweaks.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AQuickCommand extends AbstractCommand {
    private static final String CONSOLE_PREFIX = "[console]";
    private final List<String> toRun;

    public AQuickCommand(String command, List<String> toRun) {
        super("quick-commands", command, "/" + command, Translatable.getLegacy("quick_commands." + command + ".command_description"), null, CommandType.PLAYER_ONLY);
        this.toRun = toRun;
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (!player.isOp()) {
            player.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return;
        }

        for (String command : this.toRun) {
            CommandSender sender = player;

            if (command.toLowerCase().startsWith(CONSOLE_PREFIX)) {
                command = command.substring(CONSOLE_PREFIX.length()).trim();
                sender = player.getServer().getConsoleSender();
            }

            if (command.startsWith("/")) command = command.substring(1);

            player.getServer().dispatchCommand(sender, command);
        }
    }
}
