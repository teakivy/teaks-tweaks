package me.teakivy.teakstweaks.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class KillBoatsCommand extends AbstractCommand {

    public KillBoatsCommand() {
        super("kill-boats", "killboats", "/killboats", CommandType.ALL);
    }

    @Override
    public void command(CommandSender sender, String[] args) {
        int boats = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() != EntityType.BOAT) continue;
                if (!entity.getPassengers().isEmpty()) continue;

                entity.remove();
                boats++;
            }
        }

        sender.sendMessage(getString("removed_boats").replace("%count%", boats + ""));
    }
}
