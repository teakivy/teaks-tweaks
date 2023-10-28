package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BackCommand extends AbstractCommand {

    public BackCommand() {
        super("back", "back", "/back", CommandType.PLAYER_ONLY);

        setCooldownTime(getConfig().getInt("packs.back.teleport-cooldown"));
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (isOnCooldown(player)) {
            player.sendMessage(getError("on_cooldown").replace("%cooldown_seconds%", getCooldownTime() + ""));
            return;
        }

        if (!Back.backLoc.containsKey(player.getUniqueId())) {
            player.sendMessage(getError("no_back_location"));
            return;
        }

        setCooldown(player);
        player.sendMessage(getString("teleporting"));

        if (getConfig().getInt("packs.back.teleport-delay") > 0) {
            Location loc = player.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
                if (!player.getLocation().equals(loc)) {
                    player.sendMessage(getError("moved"));
                    return;
                }
                Back.tpBack(player);
            }, getConfig().getInt("packs.back.teleport-delay") * 20L);

            return;
        }

        Back.tpBack(player);
    }
}
