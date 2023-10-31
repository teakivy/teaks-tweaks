package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.craftingtweaks.AbstractRecipe;
import me.teakivy.teakstweaks.craftingtweaks.CraftingRegister;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import me.teakivy.teakstweaks.utils.gui.PaginatedGUI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MechanicsCommand extends AbstractCommand {

    public MechanicsCommand() {
        super(null, "mechanics", "/mechanics", CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        List<ItemStack> items = new ArrayList<>();

        for (String pk : TeaksTweaks.getRegister().getEnabledPacks()) {
            items.add(TeaksTweaks.getRegister().getPack(pk).getItem());
        }

        for (AbstractRecipe recipe : CraftingRegister.getEnabledRecipes()) {
            items.add(recipe.getItem());
        }


        PaginatedGUI gui = new PaginatedGUI(items, getString("gui.title"));

        gui.open(player);
    }
}
