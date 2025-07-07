package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class CraftingTableQuickCommand {

    public void register() {
        new CraftingTableQuickCommand.CraftingTableCommand().register();
    }


    class CraftingTableCommand extends AbstractCommand {
        public CraftingTableCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "craftingtable", Permission.COMMAND_CRAFTINGTABLE, List.of("craft", "workbench", "wb"), "quick_commands.craftingtable");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(MenuType.CRAFTING.create(target));
        }
    }
}
