package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class KillBoatsCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public KillBoatsCommand() {
        super("kill-boats", "killboats", "/killboats", "Kill all empty boats");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.kill-boats.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }
        int boats = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityType.BOAT) {
                    if (entity.getPassengers().isEmpty()) {
                        entity.remove();
                        boats++;
                    }
                }
            }
        }
        sender.sendMessage(getString("removed_boats").replace("%count%", boats + ""));
        return false;
    }
}
