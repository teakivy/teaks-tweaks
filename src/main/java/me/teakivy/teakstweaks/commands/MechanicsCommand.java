package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.craftingtweaks.AbstractCraftingTweak;
import me.teakivy.teakstweaks.craftingtweaks.CraftingRegister;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.gui.PaginatedGUI;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MechanicsCommand extends AbstractCommand {

    public MechanicsCommand() {
        super(null, "mechanics");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("mechanics")
                .requires(perm(Permission.COMMAND_MECHANICS))
                .executes(playerOnly(this::mechanics))
                .build();
    }

    private int mechanics(CommandContext<CommandSourceStack> ctx) {
        List<ItemStack> items = new ArrayList<>();

        for (String pk : TeaksTweaks.getRegister().getEnabledPacks()) {
            items.add(TeaksTweaks.getRegister().getPack(pk).getItem());
        }

        for (AbstractCraftingTweak recipe : CraftingRegister.getEnabledRecipes()) {
            items.add(recipe.getItem());
        }


        PaginatedGUI gui = new PaginatedGUI(items, getText("gui.title"));

        gui.open((Player) ctx.getSource().getSender());

        return Command.SINGLE_SUCCESS;
    }
}
