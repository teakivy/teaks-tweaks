package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class HomeCommand extends AbstractCommand {

    public HomeCommand() {
        super(CommandType.PLAYER_ONLY, "homes", "home", Arg.optional("set", "delete", "home"), Arg.optional("home"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        List<Home> homes = HomesPack.getHomes(player);

        if (!event.hasArgs()) {
            if (homes.isEmpty() && HomesPack.getHome(player, "home") == null) {
                sendError("no_homes_yet");
                return;
            }

            Home home = HomesPack.getHome(player, "home");
            if (home == null) {
                home = homes.get(0);
            }

            home.teleport();
            return;
        }

        if (event.isArg(0, "set")) {
            if (!event.hasArgs(2) && HomesPack.getHome(player, "home") != null) {
                sendError("missing_home_name");
                return;
            }
            if (!checkPermission("set")) return;
            String name = !event.hasArgs(2) ? "home" : event.getArg(1).toLowerCase();

            if (HomesPack.getHome(player, name) != null) {
                sendError("home_already_exists", insert("name", name));
                return;
            }

            int maxHomes = getPackConfig().getInt("max-homes");
            if (maxHomes > 0 && homes.size() >= maxHomes) {
                sendError("max_homes", insert("max_homes", maxHomes));
                return;
            }

            if (HomesPack.setHome(player, name, player.getLocation())) {
                sendMessage("set_home", insert("name", name));
            } else {
                sendError("cant_set_home");
            }

            return;
        }

        if (event.isArg(0, "delete")) {
            if (!event.hasArgs(2) && HomesPack.getHome(player, "home") == null) {
                sendError("missing_home_name");
                return;
            }
            if (!checkPermission("delete")) return;
            String name = !event.hasArgs(2) ? "home" : event.getArg(1).toLowerCase();

            Home home = HomesPack.getHome(player, name);
            if (home == null) {
                sendError("home_dne", insert("name", name));
                return;
            }

            if (!HomesPack.removeHome(player, name)) {
                sendError("cant_delete_home");
                return;
            }
            sendMessage("deleted_home", insert("name", name));
            return;
        }

        String name = event.getArg(0).toLowerCase();

        Home home = HomesPack.getHome(player, name);
        if (home == null) {
            sendError("home_dne", insert("name", name));
            return;
        }

        home.teleport();
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArg(0)) {
            List<String> arguments = new ArrayList<>(List.of("set", "delete"));

            for (Home home : HomesPack.getHomes(event.getPlayer())) {
                arguments.add(home.getName());
            }
            return arguments;
        }

        if (event.isArg(1) && event.isArg(0, "delete")) {
            List<String> homes = new ArrayList<>();
            for (Home home : HomesPack.getHomes(event.getPlayer())) {
                homes.add(home.getName());
            }
            return homes;
        }

        if (event.isArg(1) && event.isArg(0, "set")) {
            return List.of("[name]");
        }

        return null;
    }
}
