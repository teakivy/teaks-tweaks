package me.teakivy.teakstweaks.utils.command;

import org.bukkit.entity.Player;

public class PlayerCommandEvent {
    private final Player player;
    private final String[] args;

    public PlayerCommandEvent(Player sender, String[] args) {
        this.player = sender;
        this.args = args;
    }

    public Player getPlayer() {
        return player;
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
