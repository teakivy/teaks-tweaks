package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.itemaverages.ItemTracker;
import me.teakivy.teakstweaks.packs.thundershrine.Shrine;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ItemAveragesCommand extends AbstractCommand {

    public ItemAveragesCommand() {
        super("item-averages", "itemaverages");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("itemaverages")
                .requires(perm(Permission.COMMAND_ITEMAVERAGES))
                .then(Commands.literal("create")
                    .requires(perm(Permission.COMMAND_ITEMAVERAGES))
                    .executes(playerOnly(this::create)))
                .then(Commands.literal("uninstall")
                    .requires(perm(Permission.COMMAND_ITEMAVERAGES_UNINSTALL))
                    .executes(playerOnly(this::uninstall)))
                .build();
    }

    private int create(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        if (ItemTracker.inUse) {
            player.sendMessage(getError("tracker_in_use"));
            return Command.SINGLE_SUCCESS;
        }

        Location loc = player.getLocation().getBlock().getLocation();
        player.sendMessage(getText("tracker_created",
                insert("x", loc.getBlockX()),
                insert("y", loc.getBlockY()),
                insert("z", loc.getBlockZ())));
        ItemTracker.spawnTracker(loc, player);
        return Command.SINGLE_SUCCESS;
    }

    private int uninstall(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        int count = 0;
        for (Entity entity : player.getWorld().getEntities()) {
            if (entity.getScoreboardTags().contains("tracker")) {
                count++;
                entity.remove();
            }
        }
        player.sendMessage(getText("tracker_mass_removed", insert("count", count)));
        return Command.SINGLE_SUCCESS;
    }
}
