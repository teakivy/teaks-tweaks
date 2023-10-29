package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class HomeCommand extends AbstractCommand {

    public HomeCommand() {
        super("homes", "home", "/home [set|delete|name] [name]", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        List<Home> homes = HomesPack.getHomes(player);

        if (args.length < 1) {
            if (homes.isEmpty() && HomesPack.getHome(player, "home") == null) {
                player.sendMessage(getError("no_homes_yet"));
                return;
            }

            Home home = HomesPack.getHome(player, "home");
            if (home == null) {
                home = homes.get(0);
            }

            home.teleport();
            return;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length < 2 && HomesPack.getHome(player, "home") != null) {
                player.sendMessage(getError("missing_home_name"));
                return;
            }
            if (!checkPermission(player, "set")) return;
            String name = args.length < 2 ? "home" : args[1].toLowerCase();

            if (HomesPack.getHome(player, name) != null) {
                player.sendMessage(getError("home_already_exists").replace("%name%", name));
                return;
            }

            int maxHomes = TeaksTweaks.getInstance().getConfig().getInt("packs.homes.max-homes");
            if (maxHomes > 0 && homes.size() >= maxHomes) {
                player.sendMessage(getError("max_homes").replace("%max_homes%", String.valueOf(maxHomes)));
                return;
            }

            if (HomesPack.setHome(player, name, player.getLocation())) {
                player.sendMessage(getString("set_home").replace("%name%", name));
            } else {
                player.sendMessage(getError("cant_set_home"));
            }

            return;
        }

        if (args[0].equalsIgnoreCase("delete")) {
            if (args.length < 2 && HomesPack.getHome(player, "home") == null) {
                player.sendMessage(getError("missing_home_name"));
                return;
            }
            if (!checkPermission(player, "delete")) return;
            String name = args.length < 2 ? "home" : args[1].toLowerCase();

            Home home = HomesPack.getHome(player, name);
            if (home == null) {
                player.sendMessage(getError("home_dne").replace("%name%", name));
                return;
            }

            if (!HomesPack.removeHome(player, name)) {
                player.sendMessage(getError("cant_delete_home"));
            } else {
                player.sendMessage(getString("deleted_home").replace("%name%", name));
            }
            return;
        }

        String name = args[0].toLowerCase();

        Home home = HomesPack.getHome(player, name);
        if (home == null) {
            player.sendMessage(getError("home_dne").replace("%name%", name));
            return;
        }

        home.teleport();
    }

    @Override
    public List<String> tabComplete(Player player, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>(List.of("set", "delete"));

            for (Home home : HomesPack.getHomes(player)) {
                arguments.add(home.getName());
            }
            return arguments;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
            List<String> homes = new ArrayList<>();
            for (Home home : HomesPack.getHomes(player)) {
                homes.add(home.getName());
            }
            return homes;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            return List.of("[name]");
        }

        return null;
    }
}
