package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPing;
import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPingOption;
import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class DurabilityPingCommand extends AbstractCommand {

    public DurabilityPingCommand() {
        super(CommandType.PLAYER_ONLY, "durability-ping", "durabilityping", Permission.COMMAND_DURABILITYPING, List.of("duraping", "dp"), Arg.optional("preview", "set"), Arg.optional("option"), Arg.optional("value"));
    }

    @Override
    public void playerCommand(PlayerCommandEvent event) {
        Player player = event.getPlayer();
        if (!event.hasArgs()) {
            sendDuraPingConfig(player);
            return;
        }

        if (event.isArg(0, "preview")) {
            if (!event.hasArgs(2)) {
                sendError("missing_preview_selection");
                return;
            }

            if (!checkPermission(Permission.COMMAND_DURABILITYPING_PREVIEW)) return;

            switch (event.getArg(1)) {
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

        if (event.isArg(0, "set")) {
            if (!event.hasArgs(3) || DuraPingOption.fromString(event.getArg(1)) == null) {
                sendError("missing_set_selection");
                return;
            }

            if (!checkPermission(Permission.COMMAND_DURABILITYPING_SET)) return;

            switch (DuraPingOption.fromString(event.getArg(1))) {
                case PING_FOR_HAND_ITEMS, PING_FOR_ARMOR_ITEMS, PING_WITH_SOUND:
                    setScoreboardTag(player, DuraPingOption.fromString(event.getArg(1)), event.getArg(2));
                    break;
                case DISPLAY:
                    setDisplayTag(player, event.getArg(2));
                    break;
                default:
                    sendError("missing_set_selection");
                    break;
            }

            sendDuraPingConfig(player);
        }
    }

    @Override
    public List<String> tabComplete(TabCompleteEvent event) {
        if (event.isArgsSize(1)) return List.of("preview", "set");

        if (event.isArgsSize(2)) {
            if (event.isArg(0, "preview")) {
                return List.of("ping_with_sound", "display_subtitle", "display_title", "display_chat", "display_actionbar");
            }
            if (event.isArg(0, "set")) {
                return List.of("ping_for_hand_items", "ping_for_armor_items", "ping_with_sound", "display");
            }
        }

        if (event.isArgsSize(3)) {
            if (event.isArg(0, "set")) {
                if (event.isArg(1, "display")) {
                    return List.of("hidden", "subtitle", "title", "chat", "actionbar");
                }
                if (event.isArg(1, "ping_for_hand_items") || event.isArg(1, "ping_for_armor_items") || event.isArg(1, "ping_with_sound")) {
                    return List.of("true", "false");
                }
            }
        }

        return null;
    }

    public void sendDuraPingConfig(Player player) {
        sendStrike();

        sendOption(player, "ping_for_hand_items", false);
        sendOption(player, "ping_for_armor_items", false);
        sendOption(player, "ping_with_sound", true);
        sendOption(player, "display_hidden", false);
        sendOption(player, "display_subtitle", true);
        sendOption(player, "display_title", true);
        sendOption(player, "display_chat", true);
        sendOption(player, "display_actionbar", true);

        sendStrike();
    }

    public String newPreviewPanel(String command, String name) {
        return "<hover:show_text:\""
                + getString("preview_panel.hover").replace("<name>", name)
                + "\"><click:run_command:"
                + command
                + "><gray>[ ℹ ]</click></hover><reset> ";
    }

    public String createCheckBox(boolean checked, String command, String loreName, String loreDescription) {
        String replacement = loreName + (loreDescription.contains("durabilityping.") ? "" : "<newline><gray>" + loreDescription);

        String hover = "<hover:show_text:\"" + getString("checkbox." + (checked ? "yes" : "no") + ".hover") + "\">";

        String click = "<click:run_command:" + command + ">";
        return (hover + click + (checked ? "<green>[ ✔ ]" : "<red>[ ❌ ]")).replace("<name>", replacement) + "</click></hover><reset> ";
    }

    private void setScoreboardTag(Player player, DuraPingOption option, String value) {
        if (value.equals("true")) {
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

        String setCommand = "/duraping set " + option + " " + (tags.contains("dp_" + option) ? "false" : "true");

        if (option.contains("display_")) {
            setCommand = "/duraping set display " + option.replace("display_", "");
        }

        String message = createCheckBox(tags.contains("dp_" + option), setCommand,
                getString("config." + option + ".name"),
                getString("config." + option + ".description"));

        if (preview) {
            message += newPreviewPanel("/duraping preview " + option,
                    getString("config." + option + ".name"));
        }

        message += getString("config." + option + ".name");

        sendText(message);
    }

    private void sendStrike() {
        sendString("<dark_gray><strikethrough>"
                + "                                                                                "
        );
    }
}
