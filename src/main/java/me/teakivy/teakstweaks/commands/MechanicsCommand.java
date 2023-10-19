package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.craftingtweaks.CraftingRegister;
import me.teakivy.teakstweaks.utils.AbstractCommand;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.gui.PaginatedGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MechanicsCommand extends AbstractCommand {

    public MechanicsCommand() {
        super(null, "mechanics", "/mechanics", "View Mechanics added by Teak's Tweaks");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ErrorType.NOT_PLAYER.m());
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ErrorType.MISSING_COMMAND_PERMISSION.m());
            return true;
        }

        Player player = (Player) sender;

        List<ItemStack> items = new ArrayList<>();

        for (String pk : Main.getRegister().getEnabledPacks()) {
            items.add(Main.getRegister().getPack(pk).getItem());
        }

        for (AbstractRecipe recipe : CraftingRegister.getEnabledRecipes()) {
            items.add(recipe.getItem());
        }


        PaginatedGUI gui = new PaginatedGUI(items, getString("gui.title"));

        gui.open(player);

        return true;
    }
}
