package me.teakivy.teakstweaks.utils.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEvent {
    private CommandSender sender;
    private String[] args;

    public CommandEvent(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String[] getArgs() {
        return args;
    }

    public String getArg(int index) {
        return args[index];
    }

    public int getArgsLength() {
        return args.length;
    }

    public boolean argsLengthEquals(int length) {
        return args.length == length;
    }
}
