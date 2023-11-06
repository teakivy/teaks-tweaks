package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.graves.GraveEvents;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.Arg;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GraveCommand extends AbstractCommand {


    public GraveCommand() {
        super(CommandType.PLAYER_ONLY, "graves", "grave", Arg.required("locate", "key", "uninstall"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (event.isArg(0, "locate")) {
            if (!checkPermission("locate")) return;
            if (!getPackConfig().getBoolean("locatable")) {
                sendError(ErrorType.COMMAND_DISABLED);
                return;
            }

            PersistentDataContainer data = event.getPlayer().getPersistentDataContainer();
            if (!data.has(Key.get("graves_last"), PersistentDataType.STRING)) {
                sendError("no_grave");
                return;
            }

            sendString(data.get(Key.get("graves_last"), PersistentDataType.STRING));
            return;
        }

        if (event.isArg(0, "key")) {
            if (!checkPermission("key")) return;

            event.getPlayer().getInventory().addItem(GraveEvents.getGraveKey());
            sendMessage("given_key");
        }

        if (event.isArg(0, "uninstall")) {
            if (!checkPermission("uninstall")) return;
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getScoreboardTags().contains("grave")) {
                        entity.remove();
                    }
                }
            }
            sendMessage("removed_graves");
        }
    }
}
