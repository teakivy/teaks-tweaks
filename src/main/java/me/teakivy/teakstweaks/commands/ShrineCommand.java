package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.hermitcraft.thundershrine.Shrine;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class ShrineCommand extends AbstractCommand {

    public ShrineCommand() {
        super("thunder-shrine", "shrine", "/shrine <create|remove|uninstall>", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1) {
            sendUsage(player);
            return;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!checkPermission(player, "create")) return;

            Location loc = player.getLocation();
            String world = loc.getWorld().getName();
            int x = (int) Math.floor(loc.getX());
            int y = (int) Math.floor(loc.getY());
            int z = (int) Math.floor(loc.getZ());
            try {
                Shrine.createShrine(player.getLocation());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            player.sendMessage(getString("created")
                    .replace("%x%", x + "")
                    .replace("%y%", y + "")
                    .replace("%z%", z + "")
                    .replace("%world%", world)
            );
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (!checkPermission(player, "remove")) return;

            Entity shrine = null;
            for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                if (Shrine.getShrines().contains(entity)) {
                    shrine = entity;
                }
            }

            if (shrine == null) {
                player.sendMessage(getError("none_nearby"));
                return;
            }

            shrine.remove();
            player.sendMessage(getString("removed")
                    .replace("%x%", (int) shrine.getLocation().getX() + "")
                    .replace("%y%", (int) shrine.getLocation().getY() + "")
                    .replace("%z%", (int) shrine.getLocation().getZ() + "")
                    .replace("%world%", shrine.getLocation().getWorld().getName())
            );
        }

        if (args[0].equalsIgnoreCase("uninstall")) {
            if (!checkPermission(player, "uninstall")) return;

            for (Entity shrine : Shrine.getShrines()) {
                shrine.remove();
            }
            player.sendMessage(getString("shrines_mass_removed"));
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("create", "remove", "uninstall");

        return null;
    }
}
