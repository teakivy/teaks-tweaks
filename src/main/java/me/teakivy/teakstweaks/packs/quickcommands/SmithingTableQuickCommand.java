package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class SmithingTableQuickCommand {

    public void register() {
        new SmithingTableQuickCommand.SmithingTableCommand().register();
    }


    class SmithingTableCommand extends AbstractCommand {
        public SmithingTableCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "smithingtable", Permission.COMMAND_SMITHINGTABLE, List.of("smith"), "quick_commands.smithingtable");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(MenuType.SMITHING.create(target));
        }
    }
}
