package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class GrindstoneQuickCommand {

    public void register() {
        new GrindstoneQuickCommand.GrindstoneCommand().register();
    }


    class GrindstoneCommand extends AbstractCommand {
        public GrindstoneCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "grindstone", Permission.COMMAND_GRINDSTONE, List.of("grindstone"), "quick_commands.grindstone");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(MenuType.GRINDSTONE.create(target));
        }
    }
}
