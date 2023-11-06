package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.homes.Home;
import me.teakivy.teakstweaks.packs.teleportation.homes.HomesPack;
import me.teakivy.teakstweaks.utils.command.*;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHomeCommand extends AbstractCommand {

    public SetHomeCommand() {
        super(CommandType.PLAYER_ONLY, "homes", "sethome", "home",  Arg.optional("name"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        List<Home> homes = HomesPack.getHomes(player);

        if (!event.hasArgs() && HomesPack.getHome(player, "home") != null) {
            sendError("missing_home_name");
            return;
        }

        String name = !event.hasArgs() ? "home" : event.getArg(0).toLowerCase();

        if (HomesPack.getHome(player, name) != null) {
            sendError("home_already_exists", insert("name", name));
            return;
        }

        int maxHomes = getPackConfig().getInt("max-homes");
        if (maxHomes > 0 && homes.size() >= maxHomes) {
            sendError("max_homes", insert("max_homes", maxHomes));
            return;
        }

        if (!HomesPack.setHome(player, name, player.getLocation())) {
            sendError("cant_set_home");
            return;
        }

        sendMessage("home.set_home", insert("name", name));
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (!event.isArgsSize(1)) return null;

        return List.of("[name]");
    }
}
