package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.gui.PaginatedGUI;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.Register;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import me.teakivy.teakstweaks.utils.register.TTCraftingTweak;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MechanicsCommand extends AbstractCommand {

    public MechanicsCommand() {
        super(TTCommand.MECHANICS, "mechanics");
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

        for (TTPack pk : Register.getEnabledPacks()) {
            items.add(pk.getItem());
        }

        for (TTCraftingTweak recipe : Register.getEnabledCraftingTweaks()) {
            items.add(recipe.getItem());
        }


        PaginatedGUI gui = new PaginatedGUI(items, getText("gui.title"));

        gui.open((Player) ctx.getSource().getSender());

        return Command.SINGLE_SUCCESS;
    }
}
