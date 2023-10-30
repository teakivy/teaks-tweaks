package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.utilities.itemaverages.ItemTracker;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class ItemAveragesCommand extends AbstractCommand {


    public ItemAveragesCommand() {
        super("item-averages", "itemaverages", "/itemaverages <create|uninstall>", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1) {
            sendUsage(player);
            return;
        }

        if (args[0].equals("create")) {
            if (!checkPermission(player, "create")) return;

            if (ItemTracker.inUse) {
                player.sendMessage(getError("tracker_in_use"));
                return;
            }

            player.sendMessage(getString("tracker_created")
                    .replace("%x%", "" + (int) player.getLocation().getX())
                    .replace("%y%", "" + (int) player.getLocation().getY())
                    .replace("%z%", "" + (int) player.getLocation().getZ())
            );
            ItemTracker.spawnTracker(player.getLocation().getBlock().getLocation(), player);
        }

        if (args[0].equals("uninstall")) {
            if (!checkPermission(player, "uninstall")) return;
            int count = 0;
            for (Entity entity : player.getWorld().getEntities()) {
                if (entity.getScoreboardTags().contains("tracker")) {
                    count++;
                    entity.remove();
                }
            }
            player.sendMessage(getString("tracker_mass_removed").replace("%count%", count + ""));
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        return List.of("create", "uninstall");
    }
}
