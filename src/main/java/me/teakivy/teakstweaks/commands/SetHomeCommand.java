package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHomeCommand extends AbstractCommand {

    public SetHomeCommand() {
        super("homes", "sethome", "/sethome", "Set a home to teleport to at a later date");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        List<Home> homes = HomesPack.getHomes(player);

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        if (args.length < 1 && HomesPack.getHome(player, "home") != null) {
            player.sendMessage(get("homes.error.missing_home_name"));
            return true;
        }

        String name = args.length < 1 ? "home" : args[0].toLowerCase();

        if (HomesPack.getHome(player, name) != null) {
            player.sendMessage(get("homes.error.home_already_exists").replace("%name%", name));
            return true;
        }

        int maxHomes = TeaksTweaks.getInstance().getConfig().getInt("packs.homes.max-homes");
        if (maxHomes > 0 && homes.size() >= maxHomes) {
            player.sendMessage(get("homes.error.max_homes").replace("%max_homes%", maxHomes + ""));
            return true;
        }

        if (HomesPack.setHome(player, name, player.getLocation())) {
            player.sendMessage(getString("homes.set_home").replace("%name%", name));
        } else {
            player.sendMessage(get("homes.error.cant_set_home"));
        }
        return true;
    }
}
