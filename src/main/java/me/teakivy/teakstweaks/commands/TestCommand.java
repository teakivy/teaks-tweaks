package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public TestCommand() {
        super("test", MessageHandler.getCmdName("test"), MessageHandler.getCmdUsage("test"), MessageHandler.getCmdDescription("test"), MessageHandler.getCmdAliases("test"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!sender.hasPermission("teakstweaks.manage")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }
        if (main.getConfig().getBoolean("config.dev-mode")) {
            player.sendMessage(MessageHandler.getCmdMessage("test", "msg"));
        } else {
            player.sendMessage(MessageHandler.getCmdMessage("test", "test-command-found"));
            if (player.isOp()) {
                player.sendMessage(MessageHandler.getCmdMessage("test", "enable-dev-mode"));
            }
        }
        return false;
    }

    @Override
    public void register() {
        if (main.getConfig().getBoolean("config.dev-mode")) {
            super.register();
        }
    }
}
