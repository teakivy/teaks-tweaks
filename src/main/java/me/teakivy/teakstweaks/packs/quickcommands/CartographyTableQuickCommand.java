package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class CartographyTableQuickCommand {

    public void register() {
        new CartographyTableQuickCommand.CartographyTable().register();
    }


    class CartographyTable extends AbstractCommand {
        public CartographyTable() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "cartographytable", Permission.COMMAND_CARTOGRAPHYTABLE, List.of("cartography"), "quick_commands.cartographytable");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(MenuType.CARTOGRAPHY_TABLE.create(target));
        }
    }
}
