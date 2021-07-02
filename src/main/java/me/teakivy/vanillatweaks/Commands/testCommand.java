package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.MoreMobHeads.MobHeads;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class testCommand implements CommandExecutor {

    Main main = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("test")) {
            Player player = (Player) sender;
            World world = player.getWorld();
            player.getInventory().addItem(new ItemStack(Material.valueOf("RED_" + "CONCRETE_POWDER")));
        }
        return false;
    }
}
