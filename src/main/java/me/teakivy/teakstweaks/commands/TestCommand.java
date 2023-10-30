package me.teakivy.teakstweaks.commands;

import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super(null, "test", "/test", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        player.sendMessage(getString("test"));
    }
}
