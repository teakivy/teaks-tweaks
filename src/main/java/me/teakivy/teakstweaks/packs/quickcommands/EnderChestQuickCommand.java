package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.oldcommand.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;

import java.util.List;

public class EnderChestQuickCommand {

    public void register() {
        new EnderChestQuickCommand.EnderChestCommand().register();
    }


    class EnderChestCommand extends AbstractCommand {
        public EnderChestCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "enderchest", Permission.COMMAND_ENDERCHEST, List.of("echest", "ec"), "quick_commands.enderchest");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(target.getEnderChest());
        }
    }
}
