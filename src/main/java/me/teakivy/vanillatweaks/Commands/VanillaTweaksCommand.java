package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Utils.Register.Register;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.Collections;
import java.util.Objects;

public class VanillaTweaksCommand extends BukkitCommand {

    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public VanillaTweaksCommand(String name) {
        super(name);
        this.setDescription("Vanilla Tweaks Main Command!");
        this.setAliases(Collections.singletonList("vt"));
        this.usageMessage = "/vanillatweaks reload";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(vt + ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(vt + ChatColor.RED + "Usage: /" + commandLabel + " reload");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            Main.getPlugin(Main.class).reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Main.getPlugin(Main.class).getConfig().getString("reload.message"))));
        }

        Register.unregisterAll();
        Register.registerAll();
        return false;
    }
}
