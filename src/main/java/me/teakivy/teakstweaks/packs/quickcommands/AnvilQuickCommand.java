package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class AnvilQuickCommand {

    public void register() {
        new AnvilQuickCommand.AnvilCommand().register();
    }


    class AnvilCommand extends AbstractCommand {
        public AnvilCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "anvil", Permission.COMMAND_ANVIL, List.of("anvil"), "quick_commands.anvil");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(MenuType.ANVIL.create(target));
        }
    }
}
