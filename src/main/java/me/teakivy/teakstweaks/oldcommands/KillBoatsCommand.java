package me.teakivy.teakstweaks.oldcommands;

import me.teakivy.teakstweaks.utils.oldcommand.AbstractCommand;
import me.teakivy.teakstweaks.utils.oldcommand.CommandEvent;
import me.teakivy.teakstweaks.utils.oldcommand.CommandType;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class KillBoatsCommand extends AbstractCommand {

    public KillBoatsCommand() {
        super(CommandType.ALL, "kill-boats", "killboats", Permission.COMMAND_KILLBOATS);
    }

    @Override
    public void command(CommandEvent event) {
        List<EntityType> boatTypes = new ArrayList<>();
        for (EntityType value : EntityType.values()) {
            if (value.name().contains("BOAT") || value.name().contains("RAFT")) {
                boatTypes.add(value);
            }
        }
        int boats = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (!boatTypes.contains(entity.getType())) continue;
                if (!entity.getPassengers().isEmpty()) continue;

                entity.remove();
                boats++;
            }
        }

        sendMessage("removed_boats", insert("count", boats));
    }
}
