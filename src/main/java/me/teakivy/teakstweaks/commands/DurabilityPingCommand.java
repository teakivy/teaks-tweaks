package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPing;
import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPingOption;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class DurabilityPingCommand extends AbstractCommand {

    public DurabilityPingCommand() {
        super("durability-ping", "durabilityping", "/durabilityping [preview|set] [option] [value]", List.of("duraping", "dp"), CommandType.PLAYER_ONLY);
    }

    @Override
    public void playerCommand(Player player, String[] args) {
        if (args.length < 1) {
            sendDuraPingConfig(player);
            return;
        }

        if (args[0].equalsIgnoreCase("preview")) {
            if (args.length < 2) {
                player.sendMessage(getError("missing_preview_selection"));
                return;
            }

            if (!checkPermission(player, "preview")) return;

            switch (args[1].toLowerCase()) {
                case "ping_with_sound":
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 2);
                    break;
                case "display_subtitle":
                    DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "subtitle", false);
                    break;
                case "display_title":
                    DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "title", false);
                    break;
                case "display_chat":
                    DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "chat", false);
                    break;
                case "display_actionbar":
                    DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "actionbar", false);
                    break;
            }
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length < 3) {
                player.sendMessage(getError("missing_set_selection"));
                return;
            }

            if (!checkPermission(player, "set")) return;

            if (DuraPingOption.fromString(args[1]) == null) {
                player.sendMessage(getError("missing_set_selection"));
                return;
            }

            switch (DuraPingOption.fromString(args[1])) {
                case PING_FOR_HAND_ITEMS, PING_FOR_ARMOR_ITEMS, PING_WITH_SOUND:
                    setScoreboardTag(player, DuraPingOption.fromString(args[1]), args[2]);
                    break;
                case DISPLAY:
                    setDisplayTag(player, args[2].toLowerCase());
                    break;
                default:
                    player.sendMessage(getError("missing_set_selection"));
                    break;
            }

            sendDuraPingConfig(player);
        }
    }

    @Override
    public List<String> tabComplete(String[] args) {
        if (args.length == 1) return List.of("preview", "set");

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("preview")) {
                return List.of("ping_with_sound", "display_subtitle", "display_title", "display_chat", "display_actionbar");
            }
            if (args[0].equalsIgnoreCase("set")) {
                return List.of("ping_for_hand_items", "ping_for_armor_items", "ping_with_sound", "display");
            }
        }

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("display")) {
                    return List.of("hidden", "subtitle", "title", "chat", "actionbar");
                }
                if (args[1].equalsIgnoreCase("ping_for_hand_items") || args[1].equalsIgnoreCase("ping_for_armor_items") || args[1].equalsIgnoreCase("ping_with_sound")) {
                    return List.of("true", "false");
                }
            }
        }

        return null;
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

    public TextComponent newText(String text) {
        TextComponent comp = new TextComponent(text);
        comp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ""));
        comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("")));
        return comp;
    }

    public TextComponent newPreviewPanel(String command, String name) {
        TextComponent comp = new TextComponent(ChatColor.GRAY + "[ ℹ ]");
        comp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(getString("preview_panel.hover").replace("%name%", name))));
        return comp;
    }

    public TextComponent createCheckBox(Boolean checked, String command, String loreName, String loreDescription) {
        TextComponent box;
        final String replacement = loreName + (loreDescription == null ? "" : "\n" + ChatColor.GRAY + loreDescription);
        if (checked) {
            box = new TextComponent(ChatColor.GREEN + "[ ✔ ]");
            box.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(getString("checkbox.yes.hover").replace("%name%", replacement))));
        } else {
            box = new TextComponent(ChatColor.RED + "[ ❌ ]");
            box.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(getString("checkbox.no.hover").replace("%name%", replacement))));
        }
        box.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return box;
    }

    private void setScoreboardTag(Player player, DuraPingOption option, String value) {
        if (value.equalsIgnoreCase("true")) {
            player.addScoreboardTag(option.getScoreboardTag());
            return;
        }
        player.removeScoreboardTag(option.getScoreboardTag());
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
        ComponentBuilder builder = new ComponentBuilder();

        String setCommand = "/duraping set " + option + " " + (tags.contains("dp_" + option) ? "false" : "true");

        if (option.contains("display_")) {
            setCommand = "/duraping set display " + option.replace("display_", "");
        }

        builder.append(createCheckBox(tags.contains("dp_" + option),
                setCommand,
                getString("config." + option + ".name"),
                getString("config." + option + ".description")));

        if (preview) {
            builder.append(newText(" "));
            builder.append(newPreviewPanel("/duraping preview " + option,
                    getString("config." + option + ".name")));
        }

        builder.append(newText(" " + getString("config." + option + ".name")));

        player.spigot().sendMessage(builder.create());
    }

    private void sendStrike(Player player) {
        player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH
                + "                                                                                "
        );
    }
}
