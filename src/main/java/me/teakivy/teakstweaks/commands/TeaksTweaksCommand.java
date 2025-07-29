package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.URLUtils;
import me.teakivy.teakstweaks.utils.Wiki;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.customitems.ItemHandler;
import me.teakivy.teakstweaks.utils.log.PasteManager;
import me.teakivy.teakstweaks.utils.log.PasteUploader;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TeaksTweaksCommand extends AbstractCommand {

    public TeaksTweaksCommand() {
        super(TTCommand.TEAKSTWEAKS, "teakstweakscommand", List.of("tt", "tweaks"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("teakstweaks")
                .executes(this::info)
                .then(Commands.literal("info")
                        .executes(this::info))
                .then(Commands.literal("version")
                        .executes(this::version))
                .then(Commands.literal("support")
                        .executes(this::support))
                .then(Commands.literal("update")
                        .executes(this::update))
                .then(Commands.literal("wiki")
                        .then(Commands.literal("packs")
                                .executes(ctx -> wiki(ctx, "Packs")))
                        .then(Commands.literal("craftingtweaks")
                                .executes(ctx -> wiki(ctx, "Crafting-Tweaks")))
                        .then(Commands.literal("commands")
                                .executes(ctx -> wiki(ctx, "Commands")))
                )
                .then(Commands.literal("paste")
                        .requires(perm(Permission.COMMAND_TEAKSTWEAKS_PASTE))
                        .then(Commands.argument("include logs", BoolArgumentType.bool())
                                .executes(this::paste))
                        .executes(ctx -> paste(ctx, Config.getBoolean("settings.send-log-in-paste"))))
                .then(Commands.literal("give")
                        .requires(perm(Permission.COMMAND_TEAKSTWEAKS_GIVE))
                        .then(Commands.argument("targets", ArgumentTypes.players())
                                .then(Commands.argument("item", StringArgumentType.word())
                                        .suggests(this::getGiveItemSuggestions)
                                        .then(Commands.argument("amount", IntegerArgumentType.integer(1, 64 * 9 * 4))
                                                .executes(this::give))))

                )
                .build();
    }

    private int info(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();

        ArrayList<Component> components = new ArrayList<>();
        components.add(getText("info.dashed_line"));
        components.add(Component.empty());
        components.add(getText("info.title", insert("version", getPlugin().getDescription().getVersion())));
        components.add(Component.empty());
        components.add(getText("info.author", insert("author", get("plugin.author"))));
        components.add(getText("info.config_version", insert("config_version", Config.getVersion())));
        components.add(getText("info.config_generated", insert("config_generated", Config.getCreatedVersion())));
        if (Config.isDevMode()) {
            components.add(getText("info.dev_mode_enabled"));
        }
        components.add(getText("info.support", insert("discord", URLUtils.clickable(URLUtils.getDiscord()))));
        components.add(Component.empty());
        components.add(getText("info.dashed_line"));

        for (Component component : components) {
            sender.sendMessage(component);
        }

        return Command.SINGLE_SUCCESS;
    }

    private int version(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();
        sender.sendMessage(getText("version", insert("version", getPlugin().getDescription().getVersion())));
        return Command.SINGLE_SUCCESS;
    }

    private int support(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();
        sender.sendMessage(getText("support", insert("discord", URLUtils.clickable(URLUtils.getDiscord()))));
        return Command.SINGLE_SUCCESS;
    }

    private int update(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();
        sender.sendMessage(getText("update", insert("url", URLUtils.clickable(URLUtils.getModrinth()))));
        return Command.SINGLE_SUCCESS;
    }

    private int wiki(CommandContext<CommandSourceStack> ctx, String page) {
        CommandSender sender = ctx.getSource().getSender();
        sender.sendMessage(getText("wiki", insert("wiki", URLUtils.clickable(Wiki.getWikiPage(page)))));
        return Command.SINGLE_SUCCESS;
    }

    private int paste(CommandContext<CommandSourceStack> ctx) {
        return paste(ctx, ctx.getArgument("include logs", boolean.class));
    }

    private int paste(CommandContext<CommandSourceStack> ctx, boolean logs) {
        CommandSender sender = ctx.getSource().getSender();
        sender.sendMessage(getText("paste.uploading", insert("service_name", "Pastebin")));

        String playerName = (sender instanceof Player) ? sender.getName() : "CONSOLE";

        String paste = PasteManager.getPasteContent(playerName, logs);
        try {
            String url = PasteUploader.uploadToPastebin(paste, "Support: " + playerName);
            if (sender instanceof Player) {
                sender.sendMessage(getText("paste.success", insert("url", URLUtils.clickable(url)), insert("service_name", "Pastebin")));
                return Command.SINGLE_SUCCESS;
            }
            sender.sendMessage(getText("paste.success.console", insert("url", url), insert("service_name", "Pastebin")));
            return Command.SINGLE_SUCCESS;
        } catch (IOException e) {
            sender.sendMessage(getText("paste.error", insert("service_name", "Pastebin")));
            e.printStackTrace();
            return 0;
        }

    }

    private CompletableFuture<Suggestions> getGiveItemSuggestions(final CommandContext<CommandSourceStack> ctx, final SuggestionsBuilder builder) {
        builder.restart();
        for (String key : ItemHandler.getAllKeys()) {
            if (key.toLowerCase().startsWith(builder.getRemainingLowerCase())) builder.suggest(key);
        }
        return builder.buildFuture();
    }

    private int give(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("targets", PlayerSelectorArgumentResolver.class);
        try {
            final List<Player> targets = targetResolver.resolve(ctx.getSource());
            String itemName = ctx.getArgument("item", String.class);
            int amount = ctx.getArgument("amount", Integer.class);

            ItemStack item = ItemHandler.getItem(itemName);
            if (item == null) {
                sender.sendMessage(getError("give.unknown_item", insert("item", ctx.getArgument("item", String.class))));
                return 0;
            }

            for (Player target : targets) {
                ItemStack giveItem = item.clone();
                for (int i = 0; i < amount; i++) {
                    target.give(giveItem);
                }
            }

            Component recipient = targets.size() == 1 ? Component.text(targets.getFirst().getName()) : getText("teakstweakscommand.give.all_players");

            sender.sendMessage(getText("give.success", insert("amount", amount), insert("item", itemName), insert("player", recipient)));
            return Command.SINGLE_SUCCESS;
        } catch (CommandSyntaxException e) {
            sender.sendMessage(ErrorType.UNKNOWN_ERROR.m());
            throw new RuntimeException(e);
        }
    }
}
