package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.hermitcraft.thundershrine.Shrine;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.Arg;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.io.IOException;

public class ShrineCommand extends AbstractCommand {

    public ShrineCommand() {
        super(CommandType.PLAYER_ONLY, "thunder-shrine", "shrine", Arg.required("create", "remove", "uninstall"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (event.isArg(0, "create")) {
            if (!checkPermission("create")) return;

            Location loc = event.getPlayer().getLocation();
            String world = loc.getWorld().getName();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();
            try {
                Shrine.createShrine(new Location(loc.getWorld(), x, y, z));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sendMessage("created", insert("x", x), insert("y", y), insert("z", z), insert("world", world));
        }

        if (event.isArg(0, "remove")) {
            if (!checkPermission("remove")) return;

            Entity shrine = null;
            for (Entity entity : event.getPlayer().getNearbyEntities(3, 3, 3)) {
                if (Shrine.getShrines().contains(entity)) {
                    shrine = entity;
                }
            }

            if (shrine == null) {
                sendError("none_nearby");
                return;
            }

            shrine.remove();
            sendMessage("removed",
                    insert("x", shrine.getLocation().getBlockX()),
                    insert("y", shrine.getLocation().getBlockY()),
                    insert("z", shrine.getLocation().getBlockZ()),
                    insert("world", shrine.getLocation().getWorld().getName()));
        }

        if (event.isArg(0, "uninstall")) {
            if (!checkPermission("uninstall")) return;

            for (Entity shrine : Shrine.getShrines()) {
                shrine.remove();
            }
            sendMessage("shrines_mass_removed");
        }
    }
}
