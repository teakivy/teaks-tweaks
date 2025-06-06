package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class DisposalCommand extends AbstractCommand {

    public DisposalCommand() {
        super(CommandType.PLAYER_ONLY, "disposal", "disposal", Permission.COMMAND_DISPOSAL, List.of("trash", "bin", "garbage", "rubbish"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        player.openInventory(
                Bukkit.createInventory(
                        null, 27, "Disposal"
                )
        );
    }
}
