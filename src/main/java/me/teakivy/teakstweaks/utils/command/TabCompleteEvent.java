package me.teakivy.teakstweaks.utils.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TabCompleteEvent extends ArgumentEvent {
    private final CommandSender sender;

    public TabCompleteEvent(CommandSender sender, String[] args) {
        super(args);
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Player getPlayer() {
        if (!(sender instanceof Player)) return null;
        return (Player) sender;
    }
}
