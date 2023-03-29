package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import me.teakivy.teakstweaks.utils.gui.PaginatedGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TestCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public TestCommand() {
        super("test", MessageHandler.getCmdName("test"), MessageHandler.getCmdUsage("test"), MessageHandler.getCmdDescription("test"), MessageHandler.getCmdAliases("test"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!sender.hasPermission("teakstweaks.test.execute")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }
//        if (main.getConfig().getBoolean("config.dev-mode")) {
//            player.sendMessage(MessageHandler.getCmdMessage("test", "msg"));
//        } else {
//            player.sendMessage(MessageHandler.getCmdMessage("test", "test-command-found"));
//            if (player.isOp()) {
//                player.sendMessage(MessageHandler.getCmdMessage("test", "enable-dev-mode"));
//            }
//        }

        List<ItemStack> items = new ArrayList<>();

        for (String pk : Main.getRegister().getEnabledPacks()) {
            items.add(Main.getRegister().getPack(pk).getItem());
        }


        PaginatedGUI gui = new PaginatedGUI(items);

        gui.open(player);

        return false;
    }

    @Override
    public void register() {
        if (main.getConfig().getBoolean("config.dev-mode")) {
            super.register();
        }
    }
}
