package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.command.TabCompleteEvent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteHomeCommand extends AbstractCommand {

    public DeleteHomeCommand() {
        super("homes", "deletehome", "[name]", List.of("rmhome", "delhome"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (event.getArgsLength() < 1 && HomesPack.getHome(player, "home") == null) {
            sendMessage(get("home.error.missing_home_name"));
            return;
        }

        String name = event.getArgsLength() < 1 ? "home" : event.getArg(0).toLowerCase();

        Home home = HomesPack.getHome(player, name);
        if (home == null) {
            sendMessage(get("home.error.home_dne", Placeholder.parsed("name", name)));
            return;
        }

        if (!HomesPack.removeHome(player, name)) {
            sendMessage(get("home.error.cant_delete_home"));
        }

        sendMessage(get("home.deleted_home", Placeholder.parsed("name", name)));
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
