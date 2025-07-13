package me.teakivy.teakstweaks.utils.oldcommand;

import org.bukkit.entity.Player;

public class PlayerCommandEvent extends ArgumentEvent {
    private final Player player;

    public PlayerCommandEvent(Player sender, String[] args) {
        super(args);
        this.player = sender;
    }

    public Player getPlayer() {
        return player;
    }
}
