package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public TestCommand() {
        super("test", "test", "/test", "Teak's Tweaks Testing Command.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage(getString("test"));
        return false;
    }

    @Override
    public void register() {
        if (main.getConfig().getBoolean("config.dev-mode")) {
            super.register();
        }
    }
}
