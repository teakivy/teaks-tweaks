package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteHomeCommand extends AbstractCommand {

    public DeleteHomeCommand() {
    super(CommandType.PLAYER_ONLY, "homes", "deletehome", Permission.COMMAND_HOME_DELETE, List.of("rmhome", "delhome"), "home", Arg.optional("home"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (!event.hasArgs() && HomesPack.getHome(player, "home") == null) {
            sendError("missing_home_name");
            return;
        }

        String name = event.hasArgs() ? event.getArg(0).toLowerCase() : "home";

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

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (!event.isArgsSize(1)) return null;

        List<String> arguments = new ArrayList<>();

        for (Home home : HomesPack.getHomes(event.getPlayer())) {
            arguments.add(home.getName());
        }
        return arguments;
    }
}
