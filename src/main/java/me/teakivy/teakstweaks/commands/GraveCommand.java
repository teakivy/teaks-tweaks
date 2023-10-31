package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.graves.GraveEvents;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class GraveCommand extends AbstractCommand {


    public GraveCommand() {
        super("graves", "grave", "/grave <locate|key|uninstall>", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1 || args[0].equals("locate")) {
            if (!checkPermission(player, "locate")) return;
            if (!getConfig().getBoolean("packs.graves.locatable")) {
                player.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
                return;
            }

            PersistentDataContainer data = player.getPersistentDataContainer();
            if (!data.has(Key.get("graves_last"), PersistentDataType.STRING)) {
                player.sendMessage(getError("no_grave"));
                return;
            }

            player.sendMessage(data.get(Key.get("graves_last"), PersistentDataType.STRING));
            return;
        }

        if (args[0].equals("key")) {
            if (!checkPermission(player, "key")) return;

            player.getInventory().addItem(GraveEvents.getGraveKey());
            player.sendMessage(getString("given_key"));
        }

        if (args[0].equals("uninstall")) {
            if (!checkPermission(player, "uninstall")) return;
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getScoreboardTags().contains("grave")) {
                        entity.remove();
                    }
                }
            }
            player.sendMessage(getString("removed_graves"));
        }
    }
    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("locate", "key", "uninstall");

        return null;
    }
}
