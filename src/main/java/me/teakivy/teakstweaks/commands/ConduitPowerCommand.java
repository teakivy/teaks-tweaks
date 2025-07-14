package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConduitPowerCommand extends AbstractCommand {

    public ConduitPowerCommand() {
        super("spectator-conduit-power", "conduitpower");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("conduitpower")
                .requires(sender -> sender.getSender().hasPermission(Permission.COMMAND_CONDUITPOWER.getPermission()))
                .executes(playerOnly(this::conduitPower))
                .build();
    }

    private int conduitPower(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        if (player.getGameMode() != GameMode.SPECTATOR) {
            player.sendMessage(getError("wrong_gamemode"));
            return Command.SINGLE_SUCCESS;
        }

        player.sendMessage(getText("toggled"));
        if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) {
            player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
            return Command.SINGLE_SUCCESS;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, PotionEffect.INFINITE_DURATION, 0, false, true));
        return Command.SINGLE_SUCCESS;
    }
}
