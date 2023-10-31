package me.teakivy.teakstweaks.commands;

import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPing;
import me.teakivy.teakstweaks.packs.survival.durabilityping.DuraPingOption;
import me.teakivy.teakstweaks.utils.command.AbstractCommand;
import me.teakivy.teakstweaks.utils.command.CommandType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
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

        if (args[0].equals("preview")) {
            if (args.length < 2) {
                player.sendMessage(getError("missing_preview_selection"));
                return;
            }

            if (!checkPermission(player, "preview")) return;

            switch (args[1]) {
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

        if (args[0].equals("set")) {
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
                    setDisplayTag(player, args[2]);
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
            if (args[0].equals("preview")) {
                return List.of("ping_with_sound", "display_subtitle", "display_title", "display_chat", "display_actionbar");
            }
            if (args[0].equals("set")) {
                return List.of("ping_for_hand_items", "ping_for_armor_items", "ping_with_sound", "display");
            }
        }

        if (args.length == 3) {
            if (args[0].equals("set")) {
                if (args[1].equals("display")) {
                    return List.of("hidden", "subtitle", "title", "chat", "actionbar");
                }
                if (args[1].equals("ping_for_hand_items") || args[1].equals("ping_for_armor_items") || args[1].equals("ping_with_sound")) {
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

    public Component newPreviewPanel(String command, String name) {
        return newText(" <hover:show_text:<hover_text>><click:run_command:"+ command + "><gray>[ ℹ ]", Placeholder.parsed("name", name), Placeholder.parsed("command", command), Placeholder.parsed("hover_text", getString("preview_panel.hover")));
    }

    public Component createCheckBox(boolean checked, String command, String loreName, String loreDescription) {
        String replacement = loreName + (loreDescription == null ? "" : "<newline><gray>" + loreDescription);

        String hover = "<hover:show_text:" + getText("checkbox." + (checked ? "yes" : "no") + ".hover",
                Placeholder.parsed("name", replacement)) + ">";

        String click = "<click:run_command:" + command + ">";
        return newText(hover + click + (checked ? "<green>[ ✔ ]" : "<red>[ ❌ ]"));
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

        Component component = createCheckBox(tags.contains("dp_" + option), setCommand,
                getString("config." + option + ".name"),
                getString("config." + option + ".description"));


        if (preview) {
            component = component.append(newPreviewPanel("/duraping preview " + option,
                    getString("config." + option + ".name")));
        }

        component = component.append(newText(" " + getString("config." + option + ".name")));

        player.sendMessage(component);
    }

    private void sendStrike(Player player) {
        player.sendMessage("<dark_gray><strikethrough>"
                + "                                                                                "
        );
    }
}
