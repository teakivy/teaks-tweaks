package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class ConduitPowerCommand extends BukkitCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public ConduitPowerCommand(String name) {
        super(name);
        this.setDescription("Toggle Conduit Power as a spectator");
        this.setAliases(Collections.singletonList("cp"));
        this.usageMessage = "/conduitpower";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!main.getConfig().getBoolean("packs.spectator-conduit-power.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                if (player.hasPotionEffect(PotionEffectType.CONDUIT_POWER)) player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
                else player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 10000000, 0, true, true));
                player.sendMessage(vt + ChatColor.GREEN + "Toggled Conduit Power");
            } else {
                player.sendMessage(vt + ChatColor.RED + "You must be in Spectator Mode to use this command!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
        }
        return false;
    }
}
