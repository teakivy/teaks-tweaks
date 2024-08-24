package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.teleportation.back.Back;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
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

        Back.tpBack(player);
        sendMessage("teleporting");
        setCooldown();
    }
}
