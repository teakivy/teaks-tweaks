package me.teakivy.teakstweaks.utils.oldcommand;

import org.bukkit.command.CommandSender;

public class CommandEvent extends ArgumentEvent {
    private final CommandSender sender;

    public CommandEvent(CommandSender sender, String[] args) {
        super(args);
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }
}
