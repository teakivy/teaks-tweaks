package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandEvent;
import me.teakivy.teakstweaks.utils.command.CommandType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class KillBoatsCommand extends AbstractCommand {

    public KillBoatsCommand() {
        super(CommandType.ALL, "kill-boats", "killboats");
    }

    @Override
    public void command(CommandEvent event) {
        int boats = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity.getType() != EntityType.BOAT) continue;
                if (!entity.getPassengers().isEmpty()) continue;

                entity.remove();
                boats++;
            }
        }

        sendMessage("removed_boats", insert("count", boats));
    }
}
