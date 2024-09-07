package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EnderChestQuickCommand {

    public void register() {
        new EnderChestQuickCommand.EnderChestCommand().register();
    }


    class EnderChestCommand extends AbstractCommand {
        public EnderChestCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "enderchest", Permission.COMMAND_ENDERCHEST, "quick_commands.enderchest");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(target.getEnderChest());
        }
    }
}
