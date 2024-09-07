package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HealQuickCommand {

    public void register() {
        new HealQuickCommand.HealCommand().register();
    }


    class HealCommand extends AbstractCommand {
        public HealCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "heal", Permission.COMMAND_HEAL, "quick_commands.heal", Arg.optional("player"));
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = event.getPlayer();
            if (event.getArgs().length > 0) {
                target = Bukkit.getPlayer(event.getArg(0));
            }
            if (target == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }

            target.setHealth(target.getHealthScale());
        }

        @Override
        public List<String> tabComplete(TabCompleteEvent event) {
            if (event.isArg(0)) {
                List<String> result = new ArrayList<>();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getUniqueId().equals(event.getPlayer().getUniqueId())) continue;
                    result.add(p.getName());
                }
                return result;
            }

            return null;
        }
    }
}
