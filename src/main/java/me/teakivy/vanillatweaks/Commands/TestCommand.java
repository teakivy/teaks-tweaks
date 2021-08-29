package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.ErrorType;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public TestCommand() {
        super(MessageHandler.getCmdName("test"), MessageHandler.getCmdUsage("test"), MessageHandler.getCmdDescription("test"), MessageHandler.getCmdAliases("test"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        World world = player.getWorld();
        if (!sender.hasPermission("vanillatweaks.test.execute")) {
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
