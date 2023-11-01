package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHomeCommand extends AbstractCommand {

    public SetHomeCommand() {
        super("homes", "sethome", "/sethome [name]", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        List<Home> homes = HomesPack.getHomes(player);

        if (args.length < 1 && HomesPack.getHome(player, "home") != null) {
            player.sendMessage(get("home.error.missing_home_name"));
            return;
        }

        String name = args.length < 1 ? "home" : args[0].toLowerCase();

        if (HomesPack.getHome(player, name) != null) {
            player.sendMessage(get("home.error.home_already_exists", Placeholder.parsed("name", name)));
            return;
        }

        int maxHomes = getPackConfig().getInt("packs.homes.max-homes");
        if (maxHomes > 0 && homes.size() >= maxHomes) {
            player.sendMessage(get("home.error.max_homes", Placeholder.parsed("max_homes", maxHomes + "")));
            return;
        }

        if (!HomesPack.setHome(player, name, player.getLocation())) {
            player.sendMessage(get("home.error.cant_set_home"));
            return;
        }

        player.sendMessage(get("home.set_home", Placeholder.parsed("name", name)));
    }

    @Override
    public List<String> tabComplete(Player player, String[] args) {
        if (args.length != 1) return null;

        return List.of("[name]");
    }
}
