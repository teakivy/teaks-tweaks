package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyQuickCommand {

    public void register() {
        new FlyQuickCommand.FlyCommand().register();
    }


    class FlyCommand extends AbstractCommand {
        public FlyCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "fly", Permission.COMMAND_FLY, "quick_commands.fly", Arg.optional("player"));
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

            target.setAllowFlight(!target.getAllowFlight());
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
