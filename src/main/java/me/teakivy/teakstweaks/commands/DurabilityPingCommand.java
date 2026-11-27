package me.teakivy.teakstweaks.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.teakivy.teakstweaks.packs.durabilityping.DurabilityPing;
import me.teakivy.teakstweaks.packs.durabilityping.DuraPingOption;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTCommand;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DurabilityPingCommand extends AbstractCommand {

    public DurabilityPingCommand() {
        super(TTCommand.DURABILITYPING, "durabilityping", List.of("duraping"));
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("durabilityping")
                .requires(perm(Permission.COMMAND_DURABILITYPING))
                .executes(playerOnly(this::durabilityping))
                .then(Commands.literal("preview")
                        .then(Commands.argument("option", StringArgumentType.word())
                                .requires(perm(Permission.COMMAND_DURABILITYPING_PREVIEW))
                                .suggests(createSuggestions(
                                        List.of(
                                                "ping_with_sound",
                                                "display_subtitle",
                                                "display_title",
                                                "display_chat",
                                                "display_actionbar"
                                        )
                                ))
                                .executes(playerOnly(this::preview))))
                .then(Commands.literal("set")
                        .then(Commands.argument("option", StringArgumentType.word())
                                .suggests(createSuggestions(
                                        List.of(
                                                "ping_for_hand_items",
                                                "ping_for_armor_items",
                                                "ping_with_sound",
                                                "display"
                                        )
                                ))
                                .then(Commands.argument("value", StringArgumentType.word())
                                        .suggests(this::valueSuggestions)
                                        .requires(perm(Permission.COMMAND_DURABILITYPING_SET))
                                        .executes(playerOnly(this::set)))))
                .build();
    }

    private int durabilityping(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        sendDuraPingConfig(player);
        return Command.SINGLE_SUCCESS;
    }

    private int preview(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        String option = context.getArgument("option", String.class);
        switch (option) {
            case "ping_with_sound":
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 2);
                break;
            case "display_subtitle":
                DurabilityPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "subtitle", false);
                break;
            case "display_title":
                DurabilityPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "title", false);
                break;
            case "display_chat":
                DurabilityPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "chat", false);
                break;
            case "display_actionbar":
                DurabilityPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "actionbar", false);
                break;
        }
        return Command.SINGLE_SUCCESS;
    }

    private int set(CommandContext<CommandSourceStack> context) {
        Player player = (Player) context.getSource().getSender();
        String option = context.getArgument("option", String.class);
        String value = context.getArgument("value", String.class);
        switch (DuraPingOption.fromString(option)) {
            case PING_FOR_HAND_ITEMS, PING_FOR_ARMOR_ITEMS, PING_WITH_SOUND:
                setScoreboardTag(player, DuraPingOption.fromString(option), value);
                break;
            case DISPLAY:
                setDisplayTag(player, value);
                break;
            case null:
            default:
                player.sendMessage(getError("missing_set_selection"));
                break;
        }

        sendDuraPingConfig(player);
        return Command.SINGLE_SUCCESS;
    }

    public void sendDuraPingConfig(Player player) {
        sendStrike(player);

        sendOption(player, "ping_for_hand_items", false);
        sendOption(player, "ping_for_armor_items", false);
        sendOption(player, "ping_with_sound", true);
        sendOption(player, "display_hidden", false);
        sendOption(player, "display_subtitle", true);
        sendOption(player, "display_title", true);
        sendOption(player, "display_chat", true);
        sendOption(player, "display_actionbar", true);

        sendStrike(player);
    }

    public String newPreviewPanel(Player player, String command, String name) {
        return "<hover:show_text:\""
                + TranslationManager.getString(player, "durabilityping.preview_panel.hover").replace("<name>", name)
                + "\"><click:run_command:"
                + command
                + "><gray>[ ℹ ]</click></hover><reset> ";
    }

    public String createCheckBox(Player player, boolean checked, String command, String loreName, String loreDescription) {
        String replacement = loreName + (loreDescription != null && loreDescription.contains("durabilityping.") ? "" : "<newline><gray>" + loreDescription);

        String hover = "<hover:show_text:\"" + TranslationManager.getString(player, "durabilityping.checkbox." + (checked ? "yes" : "no") + ".hover") + "\">";

        String click = "<click:run_command:" + command + ">";
        return (hover + click + (checked ? "<green>[ ✔ ]" : "<red>[ ❌ ]")).replace("<name>", replacement) + "</click></hover><reset> ";
    }

    private void setScoreboardTag(Player player, DuraPingOption option, String value) {
        if (value.equals("true")) {
            player.addScoreboardTag(option.getScoreboardTag());
        } else {
            player.removeScoreboardTag(option.getScoreboardTag());
        }

        player.addScoreboardTag("dp_customized");
    }

    private void setDisplayTag(Player player, String type) {
        player.removeScoreboardTag("dp_display_hidden");
        player.removeScoreboardTag("dp_display_subtitle");
        player.removeScoreboardTag("dp_display_title");
        player.removeScoreboardTag("dp_display_chat");
        player.removeScoreboardTag("dp_display_actionbar");

        player.addScoreboardTag("dp_display_" + type);
    }

    private void sendOption(Player player, String option, boolean preview) {
        Set<String> tags = player.getScoreboardTags();

        String setCommand = "/duraping set " + option + " " + (tags.contains("dp_" + option) ? "false" : "true");

        if (option.contains("display_")) {
            setCommand = "/duraping set display " + option.replace("display_", "");
        }

        String message = createCheckBox(player, tags.contains("dp_" + option), setCommand,
                TranslationManager.getString(player, "durabilityping.config." + option + ".name"),
                TranslationManager.getString(player, "durabilityping.config." + option + ".description"));

        if (preview) {
            message += newPreviewPanel(player, "/duraping preview " + option,
                    TranslationManager.getString(player, "durabilityping.config." + option + ".name"));
        }

        message += TranslationManager.getString(player, "durabilityping.config." + option + ".name");

        player.sendRichMessage(message);
    }

    private void sendStrike(Player player) {
        player.sendRichMessage("<dark_gray><strikethrough>"
                + "                                                                                "
        );
    }

    public CompletableFuture<Suggestions> valueSuggestions(final CommandContext<CommandSourceStack> ctx, final SuggestionsBuilder builder) {
        builder.restart();
        List<String> values = new ArrayList<>();
        if (ctx.getArgument("option", String.class).equals("display")) {
            values.add("hidden");
            values.add("subtitle");
            values.add("title");
            values.add("chat");
            values.add("actionbar");
        } else {
            values.add("true");
            values.add("false");
        }
        if (!(ctx.getSource().getSender() instanceof Player)) return builder.buildFuture();
        for (String value : values) {
            if (value.toLowerCase().startsWith(builder.getRemainingLowerCase())) builder.suggest(value);
        }
        return builder.buildFuture();
    }
}
