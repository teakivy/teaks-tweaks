package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public TestCommand() {
        super("test", "/test", "Testing Command");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        World world = player.getWorld();
        if (main.getConfig().getBoolean("config.dev-mode")) {
            player.sendMessage(vt + ChatColor.GREEN + "Test!");
        } else {
            player.sendMessage(vt + ChatColor.YELLOW + "Hey! Looks like you found my test command! :D");
            if (player.isOp()) {
                player.sendMessage(ChatColor.YELLOW + "This command is used to test new features! Enable it by marking " + ChatColor.GRAY + "config.dev-mode: true" + ChatColor.YELLOW + "!");
            }
        }
        return false;
    }
}
