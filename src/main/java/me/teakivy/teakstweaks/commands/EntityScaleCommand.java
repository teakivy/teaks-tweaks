package me.teakivy.teakstweaks.commands;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class EntityScaleCommand extends AbstractCommand {

    public EntityScaleCommand() {
        super(TTCommand.ENTITYSCALE, "entity_scale");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("entityscale")
                .requires(perm(Permission.COMMAND_ENTITYSCALE))
                .then(Commands
                        .argument("scale",
                                DoubleArgumentType.doubleArg(getPackConfig().getDouble("min-scale"),
                                        getPackConfig().getDouble("max-scale")))
                        .executes(playerOnly(this::entityscale)))
                .build();
    }

    private static final MiniMessage MM = MiniMessage.miniMessage();

    private int entityscale(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();

        var eye = player.getEyeLocation();
        double reach = Math.max(0.1, Math.min(64.0, getPackConfig().getDouble("reach-distance")));

        RayTraceResult result = player.getWorld().rayTraceEntities(
                eye,
                eye.getDirection(),
                reach,
                entity -> entity != player);

        LivingEntity target;
        if (result == null || result.getHitEntity() == null) {
            target = player;
        } else {
            Entity hitEntity = result.getHitEntity();
            if (!(hitEntity instanceof LivingEntity)) {
                player.sendMessage(getText("entity_scale.not_living_entity"));
                return Command.SINGLE_SUCCESS;
            }
            target = (LivingEntity) hitEntity;
        }

        double scaleVal = context.getArgument("scale", Double.class);
        AttributeInstance scale = target.getAttribute(Attribute.SCALE);

        if (scale == null) {
            player.sendMessage(getText("entity_scale.no_scale_attribute"));
            return Command.SINGLE_SUCCESS;
        }

        scale.setBaseValue(scaleVal);
        player.sendMessage(MM.deserialize(
                TranslationManager.getString(Config.getLanguage(), "entity_scale.success"),
                Placeholder.unparsed("entity", target.getType().name()),
                Placeholder.unparsed("scale", String.valueOf(scaleVal))));

        return Command.SINGLE_SUCCESS;
    }
}
