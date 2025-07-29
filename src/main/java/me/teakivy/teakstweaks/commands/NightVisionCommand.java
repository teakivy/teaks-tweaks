package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightVisionCommand extends AbstractCommand {

    public NightVisionCommand() {
        super(TTCommand.NIGHTVISION, "nightvision");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("nightvision")
                .requires(perm(Permission.COMMAND_NIGHTVISION))
                .executes(playerOnly(this::nightvision))
                .build();
    }

    private int nightvision(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        if (player.getGameMode() != GameMode.SPECTATOR) {
            player.sendMessage(getError("wrong_gamemode"));
            return Command.SINGLE_SUCCESS;
        }

        player.sendMessage(getText("toggled"));
        if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            return Command.SINGLE_SUCCESS;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, false, true));
        return Command.SINGLE_SUCCESS;
    }
}
