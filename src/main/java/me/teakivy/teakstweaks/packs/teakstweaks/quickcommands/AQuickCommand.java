package me.teakivy.teakstweaks.packs.teakstweaks.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AQuickCommand extends AbstractCommand {
    private static final String CONSOLE_PREFIX = "[console]";
    private final List<String> toRun;

    public AQuickCommand(String command, List<String> toRun) {
        super(CommandType.PLAYER_ONLY, "quick-commands", command, "quick_commands." + command);
        this.toRun = toRun;
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (!event.getPlayer().isOp()) {
            sendError(ErrorType.MISSING_COMMAND_PERMISSION);
            return;
        }

        for (String command : this.toRun) {
            CommandSender sender = event.getPlayer();

            if (command.toLowerCase().startsWith(CONSOLE_PREFIX)) {
                command = command.substring(CONSOLE_PREFIX.length()).trim();
                sender = sender.getServer().getConsoleSender();
            }

            if (command.startsWith("/")) command = command.substring(1);

            sender.getServer().dispatchCommand(sender, command);
        }
    }
}
