package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.ErrorType;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class ConduitPowerCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);

    public ConduitPowerCommand() {
        super("spectator-conduit-power", "conduitpower", "/conduitpower", "Toggle Conduit Power as a spectator.", List.of("cp"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!main.getConfig().getBoolean("packs.spectator-conduit-power.enabled")) {
            sender.sendMessage(ErrorType.PACK_NOT_ENABLED.m());
            return true;
        }

        if (!sender.hasPermission(permission+".toggle")) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
                else player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 10000000, 0, true, true));
                player.sendMessage(getString("toggled"));
            } else {
                player.sendMessage(getString("error.wrong_gamemode"));
            }
        } else {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
        }
        return false;
    }
}
