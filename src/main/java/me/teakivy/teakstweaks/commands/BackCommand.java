package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BackCommand extends AbstractCommand {

    public BackCommand() {
        super(CommandType.PLAYER_ONLY, "back", "back", Permission.COMMAND_BACK);

        setCooldownTime(getPackConfig().getInt("teleport-cooldown"));
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

        int teleportDelay = getPackConfig().getInt("teleport-delay");

        if (teleportDelay <= 0) {
            Back.tpBack(player);
            sendMessage("teleporting");
            return;
        }
        sendMessage("teleporting_delayed", insert("time", teleportDelay));
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Bukkit.getScheduler().runTaskLater(TeaksTweaks.getInstance(), () -> {
            if (x != player.getLocation().getBlockX() || y != player.getLocation().getBlockY() || z != player.getLocation().getBlockZ()) {
                player.sendMessage(getError("teleport_moved"));
                return;
            }
            Back.tpBack(player);
            player.sendMessage(getText("teleporting"));
        }, teleportDelay * 20L);
        setCooldown();
    }
}
