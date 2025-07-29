package me.teakivy.teakstweaks.packs.quickcommands.base;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

import java.util.List;

public class MenuQuickCommand extends AbstractCommand {
    private final String commandName;
    private final MenuType menuType;
    private final Permission permission;

    public MenuQuickCommand(String commandName, List<String> aliases, Permission permission, MenuType menuType) {
        super(TTPack.QUICK_COMMANDS, "quick-commands." + commandName, aliases);
        this.commandName = commandName;
        this.menuType = menuType;
        this.permission = permission;
    }

    public MenuQuickCommand(String commandName, Permission permission, MenuType menuType) {
        this(commandName, List.of(), permission, menuType);
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal(commandName)
                .requires(perm(permission))
                .executes(playerOnly(this::execute))
                .build();
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        MenuType.Typed<?, ?> m = (MenuType.Typed<?, ?>) menuType;
        player.openInventory(m.create(player));
        return Command.SINGLE_SUCCESS;
    }
}
