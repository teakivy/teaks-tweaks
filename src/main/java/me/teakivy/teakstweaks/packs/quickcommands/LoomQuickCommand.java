package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class LoomQuickCommand {

    public void register() {
        new LoomQuickCommand.LoomCommand().register();
    }


    class LoomCommand extends AbstractCommand {
        public LoomCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "loom", Permission.COMMAND_LOOM, List.of("loom"), "quick_commands.loom");
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();

            target.openInventory(MenuType.LOOM.create(target));
        }
    }
}
