package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteHomeCommand extends AbstractCommand {

    public DeleteHomeCommand() {
        super("homes", "deletehome", "/deletehome [name]", List.of("rmhome", "delhome"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1 && HomesPack.getHome(player, "home") == null) {
            player.sendMessage(get("home.error.missing_home_name"));
            return;
        }

        String name = args.length < 1 ? "home" : args[0].toLowerCase();

        Home home = HomesPack.getHome(player, name);
        if (home == null) {
            player.sendMessage(get("home.error.home_dne", Placeholder.parsed("name", name)));
            return;
        }

        if (!HomesPack.removeHome(player, name)) {
            player.sendMessage(get("home.error.cant_delete_home"));
        }

        player.sendMessage(get("home.deleted_home", Placeholder.parsed("name", name)));
    }

    @Override
    public List<String> tabComplete(Player player, String[] args) {
        if (args.length != 1) return null;

        List<String> arguments = new ArrayList<>();

        for (Home home : HomesPack.getHomes(player)) {
            arguments.add(home.getName());
        }
        return arguments;
    }
}
