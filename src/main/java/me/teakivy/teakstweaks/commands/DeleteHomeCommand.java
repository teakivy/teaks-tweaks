package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.command.TabCompleteEvent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteHomeCommand extends AbstractCommand {

    public DeleteHomeCommand() {
        super("homes", "deletehome", "[name]", List.of("rmhome", "delhome"), CommandType.PLAYER_ONLY, "home");
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (event.getArgsLength() < 1 && HomesPack.getHome(player, "home") == null) {
            sendError("missing_home_name");
            return;
        }

        String name = event.hasArgs() ? "home" : event.getArg(0).toLowerCase();

        Home home = HomesPack.getHome(player, name);
        if (home == null) {
            sendError("home_dne", insert("name", name));
            return;
        }

        if (!HomesPack.removeHome(player, name)) {
            sendError("cant_delete_home");
        }

        sendMessage("deleted_home", insert("name", name));
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.getArgsLength() != 1) return null;

        List<String> arguments = new ArrayList<>();

        for (Home home : HomesPack.getHomes(event.getPlayer())) {
            arguments.add(home.getName());
        }
        return arguments;
    }
}
