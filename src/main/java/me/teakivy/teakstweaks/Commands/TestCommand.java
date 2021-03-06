package me.teakivy.teakstweaks.Commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.AbstractCommand;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public TestCommand() {
        super(MessageHandler.getCmdName("test"), MessageHandler.getCmdUsage("test"), MessageHandler.getCmdDescription("test"), MessageHandler.getCmdAliases("test"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!sender.hasPermission("teakstweaks.test.execute")) {
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
}
