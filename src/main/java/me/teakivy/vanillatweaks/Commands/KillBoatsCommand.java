package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class KillBoatsCommand extends BukkitCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public KillBoatsCommand(String name) {
        super(name);
        this.setDescription("Kill all empty boats");
        this.usageMessage = "/killboats";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!main.getConfig().getBoolean("packs.kill-boats.enabled")) {
            sender.sendMessage(vt + ChatColor.RED + "This pack is not enabled!");
            return true;
        }
        if (!sender.isOp()) {
            sender.sendMessage(vt + ChatColor.RED + "You cannot use this command!");
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
        sender.sendMessage(vt + ChatColor.GREEN + "Removed " + ChatColor.GOLD.toString() + ChatColor.BOLD + boats + ChatColor.GREEN + " boats!");
        return false;
    }
}
