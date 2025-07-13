package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.packs.itemaverages.ItemTracker;
import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.Arg;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.oldcommand.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ItemAveragesCommand extends AbstractCommand {


    public ItemAveragesCommand() {
        super(CommandType.PLAYER_ONLY, "item-averages", "itemaverages", Permission.COMMAND_ITEMAVERAGES, Arg.required("create", "uninstall"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (event.isArg(0, "create")) {

            if (ItemTracker.inUse) {
                sendError("tracker_in_use");
                return;
            }

            Location loc = player.getLocation().getBlock().getLocation();
            sendMessage("tracker_created",
                    insert("x", loc.getBlockX()),
                    insert("y", loc.getBlockY()),
                    insert("z", loc.getBlockZ()));
            ItemTracker.spawnTracker(loc, player);
        }

        if (event.isArg(0, "uninstall")) {
            if (!checkPermission(Permission.COMMAND_ITEMAVERAGES_UNINSTALL)) return;
            int count = 0;
            for (Entity entity : player.getWorld().getEntities()) {
                if (entity.getScoreboardTags().contains("tracker")) {
                    count++;
                    entity.remove();
                }
            }
            sendMessage("tracker_mass_removed", insert("count", count));
        }
    }
}
