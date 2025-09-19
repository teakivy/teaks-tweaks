package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Transformation;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super(TTCommand.TEST, "test");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("test")
//                .requires(perm(Permission.TEST))
                .executes(this::test)
                .build();
    }

    private int test(CommandContext<CommandSourceStack> context) {
        CommandSender sender = context.getSource().getSender();
        sender.sendMessage(getText("test", insert("name", (sender instanceof Player p ? p.getName() : "Console"))));

        Player player = (sender instanceof Player p) ? p : null;
        if (player == null) return Command.SINGLE_SUCCESS;

        player.getLocation().getBlock().setType(Material.CAULDRON);
        player.getWorld().spawn(player.getLocation().getBlock().getLocation().add(0.4, 15f/16, 1), TextDisplay.class, display -> {
            display.text(Component.text(" "));
            display.setTextOpacity((byte) 0);
            display.setBackgroundColor(PotionEffectType.STRENGTH.getColor().setAlpha(30));
            display.setDefaultBackground(false);
            display.setRotation(0, -90);
            Transformation transformation = display.getTransformation();
            transformation.getScale().set(6.8, 4, 7);
            display.setTransformation(transformation);
        });
        return Command.SINGLE_SUCCESS;
    }
}
