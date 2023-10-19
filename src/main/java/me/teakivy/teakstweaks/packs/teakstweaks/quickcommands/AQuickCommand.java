package me.teakivy.teakstweaks.packs.teakstweaks.quickcommands;

import me.teakivy.teakstweaks.commands.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AQuickCommand extends AbstractCommand {
    String command;
    String description;
    String usage;
    String permission;
    boolean requireOp;
    List<String> aliases;
    List<String> toRun;

    public AQuickCommand(String command, String description, String permission, boolean requireOp, List<String> aliases, List<String> toRun) {
        super("quick-commands", command, "/" + command, description, aliases);

        this.command = command;
        this.description = description;
        this.usage = "/" + command;
        this.permission = permission;
        this.requireOp = requireOp;
        this.aliases = aliases;
        this.toRun = toRun;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (this.requireOp && !sender.isOp()) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (this.permission != null && !sender.hasPermission(this.permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        for (String command : this.toRun) {
            if (command.toLowerCase().startsWith("[console]")) {
                command = command.substring(9).trim();
                if (command.startsWith("/")) command = command.substring(1);
                sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), command);
                continue;
            }

            if (command.startsWith("/")) command = command.substring(1);

            sender.getServer().dispatchCommand(sender, command);
        }

        return true;
    }
}
