package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.graves.GraveEvents;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.Arg;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.command.PlayerCommandEvent;
import me.teakivy.teakstweaks.utils.customitems.TItem;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GraveCommand extends AbstractCommand {


    public GraveCommand() {
        super(CommandType.PLAYER_ONLY, "graves", "grave", Permission.COMMAND_GRAVE, Arg.required("locate", "key", "uninstall"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        if (event.isArg(0, "locate")) {
            if (!checkPermission(Permission.COMMAND_GRAVE_LOCATE)) return;
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
            if (!checkPermission(Permission.COMMAND_GRAVE_KEY)) return;

            event.getPlayer().getInventory().addItem(TItem.GRAVE_KEY.getItem());
            sendMessage("given_key");
        }

        if (event.isArg(0, "uninstall")) {
            if (!checkPermission(Permission.COMMAND_GRAVE_UNINSTALL)) return;
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
