package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BackCommand extends AbstractCommand {

    public BackCommand() {
        super("back", "back", CommandType.PLAYER_ONLY);

        setCooldownTime(getConfig().getInt("packs.back.teleport-cooldown"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (isOnCooldown()) {
           sendError("on_cooldown", insert("cooldown_seconds", getCooldownTime()));
            return;
        }

        if (!Back.backLoc.containsKey(player.getUniqueId())) {
            sendError("no_back_location");
            return;
        }

        setCooldown();
        sendMessage("teleporting");

        if (getConfig().getInt("packs.back.teleport-delay") > 0) {
            Location loc = player.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
                if (!player.getLocation().equals(loc)) {
                    sendError("moved");
                    return;
                }
                Back.tpBack(player);
            }, getConfig().getInt("packs.back.teleport-delay") * 20L);

            return;
        }

        Back.tpBack(player);
    }
}
