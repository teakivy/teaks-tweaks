package me.teakivy.vanillatweaks.Commands;

import me.teakivy.vanillatweaks.Main;
import me.teakivy.vanillatweaks.Packs.Survival.DurabilityPing.DuraPing;
import me.teakivy.vanillatweaks.Utils.AbstractCommand;
import me.teakivy.vanillatweaks.Utils.MessageHandler;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class DurabilityPingCommand extends AbstractCommand {

    Main main = Main.getPlugin(Main.class);
    String vt = ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] ";

    public DurabilityPingCommand() {
        super(MessageHandler.getCmdName("durabilityping"), MessageHandler.getCmdUsage("durabilityping"), MessageHandler.getCmdDescription("durabilityping"), MessageHandler.getCmdAliases("durabilityping"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!main.getConfig().getBoolean("packs.durability-ping.enabled")) {
            sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD.toString() + ChatColor.BOLD + "VT" + ChatColor.GRAY + "] " + ChatColor.RED + "This pack is not enabled!");
            return true;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[VT] You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            sendDuraPingConfig(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("preview")) {
            if (args.length < 2) {
                player.sendMessage(vt + ChatColor.RED + "Please enter which section to preview!");
                return true;
            }

            if (args[1].equalsIgnoreCase("sound")) {
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 2);
            }

            if (args[1].equalsIgnoreCase("display_subtitle")) {
                DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "subtitle", false);
            }

            if (args[1].equalsIgnoreCase("display_title")) {
                DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "title", false);
            }

            if (args[1].equalsIgnoreCase("display_chat")) {
                DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "chat", false);
            }

            if (args[1].equalsIgnoreCase("display_actionbar")) {
                DuraPing.pingPlayer(player, new ItemStack(Material.DIAMOND_PICKAXE), 156, "actionbar", false);
            }
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length < 3) {
                player.sendMessage(vt + ChatColor.RED + "Please enter which section to set!");
                return true;
            }

            if (args[1].equalsIgnoreCase("ping_for_hand_items")) {
                if (args[2].equalsIgnoreCase("false")) player.removeScoreboardTag("dp_ping_for_hand_items");
                if (args[2].equalsIgnoreCase("true")) player.addScoreboardTag("dp_ping_for_hand_items");
            }

            if (args[1].equalsIgnoreCase("ping_for_armor_items")) {
                if (args[2].equalsIgnoreCase("false")) player.removeScoreboardTag("dp_ping_for_armor_items");
                if (args[2].equalsIgnoreCase("true")) player.addScoreboardTag("dp_ping_for_armor_items");
            }

            if (args[1].equalsIgnoreCase("ping_with_sound")) {
                if (args[2].equalsIgnoreCase("false")) player.removeScoreboardTag("dp_ping_with_sound");
                if (args[2].equalsIgnoreCase("true")) player.addScoreboardTag("dp_ping_with_sound");
            }

            if (args[1].equalsIgnoreCase("display")) {
                if (args[2].equalsIgnoreCase("hidden")) {
                    player.removeScoreboardTag("dp_display_hidden");
                    player.removeScoreboardTag("dp_display_subtitle");
                    player.removeScoreboardTag("dp_display_title");
                    player.removeScoreboardTag("dp_display_chat");
                    player.removeScoreboardTag("dp_display_actionbar");

                    player.addScoreboardTag("dp_display_hidden");
                }
                if (args[2].equalsIgnoreCase("subtitle")) {
                    player.removeScoreboardTag("dp_display_hidden");
                    player.removeScoreboardTag("dp_display_subtitle");
                    player.removeScoreboardTag("dp_display_title");
                    player.removeScoreboardTag("dp_display_chat");
                    player.removeScoreboardTag("dp_display_actionbar");

                    player.addScoreboardTag("dp_display_subtitle");
                }
                if (args[2].equalsIgnoreCase("title")) {
                    player.removeScoreboardTag("dp_display_hidden");
                    player.removeScoreboardTag("dp_display_subtitle");
                    player.removeScoreboardTag("dp_display_title");
                    player.removeScoreboardTag("dp_display_chat");
                    player.removeScoreboardTag("dp_display_actionbar");

                    player.addScoreboardTag("dp_display_title");
                }
                if (args[2].equalsIgnoreCase("chat")) {
                    player.removeScoreboardTag("dp_display_hidden");
                    player.removeScoreboardTag("dp_display_subtitle");
                    player.removeScoreboardTag("dp_display_title");
                    player.removeScoreboardTag("dp_display_chat");
                    player.removeScoreboardTag("dp_display_actionbar");

                    player.addScoreboardTag("dp_display_chat");
                }
                if (args[2].equalsIgnoreCase("actionbar")) {
                    player.removeScoreboardTag("dp_display_hidden");
                    player.removeScoreboardTag("dp_display_subtitle");
                    player.removeScoreboardTag("dp_display_title");
                    player.removeScoreboardTag("dp_display_chat");
                    player.removeScoreboardTag("dp_display_actionbar");

                    player.addScoreboardTag("dp_display_actionbar");
                }
            }
            sendDuraPingConfig(player);
        }
        return false;
    }

    public void sendDuraPingConfig(Player player) {
        Set<String> tags = player.getScoreboardTags();

        BaseComponent[] pingForHandItems = new ComponentBuilder(createCheckBox(tags.contains("dp_ping_for_hand_items"), "/duraping set ping_for_hand_items " + (tags.contains("dp_ping_for_hand_items") ? "false" : "true"), "Ping for Hand Items", "Inculdes any item with durability in the mainhand or offhand slots")).append(newText(" Ping for Hand Items")).create();
        BaseComponent[] pingForArmorItems = new ComponentBuilder(createCheckBox(tags.contains("dp_ping_for_armor_items"), "/duraping set ping_for_armor_items " + (tags.contains("dp_ping_for_armor_items") ? "false" : "true"), "Ping for Armor Items", "Inculdes any item with durability in armor slots")).append(newText(" Ping for Armor Items")).create();
        BaseComponent[] pingWithSound = new ComponentBuilder(createCheckBox(tags.contains("dp_ping_with_sound"), "/duraping set ping_with_sound " + (tags.contains("dp_ping_with_sound") ? "false" : "true"), "Ping with Sound", null)).append(newText(" ")).append(newPreviewPanel("/duraping preview sound", "Ping with Sound")).append(newText(" Ping with Sound")).create();
        BaseComponent[] pingDisplayHidden = new ComponentBuilder(createCheckBox(tags.contains("dp_display_hidden"), "/duraping set display hidden", "Display: Hidden", null)).append(newText(" Display: Hidden")).create();
        BaseComponent[] pingDisplaySubtitle = new ComponentBuilder(createCheckBox(tags.contains("dp_display_subtitle"), "/duraping set display subtitle", "Display: Subtitle", null)).append(newText(" ")).append(newPreviewPanel("/duraping preview display_subtitle", "Display: Subtitle")).append(newText(" Display: Subtitle")).create();
        BaseComponent[] pingDisplayTitle = new ComponentBuilder(createCheckBox(tags.contains("dp_display_title"), "/duraping set display title", "Display: Title", null)).append(newText(" ")).append(newPreviewPanel("/duraping preview display_title", "Display: Title")).append(newText(" Display: Title")).create();
        BaseComponent[] pingDisplayChat = new ComponentBuilder(createCheckBox(tags.contains("dp_display_chat"), "/duraping set display chat", "Display: Chat", null)).append(newText(" ")).append(newPreviewPanel("/duraping preview display_chat", "Display: Chat")).append(newText(" Display: Chat")).create();
        BaseComponent[] pingDisplayActionbar = new ComponentBuilder(createCheckBox(tags.contains("dp_display_actionbar"), "/duraping set display actionbar", "Display: Action Bar", null)).append(newText(" ")).append(newPreviewPanel("/duraping preview display_actionbar", "Display: Action Bar")).append(newText(" Display: Action Bar")).create();

        player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "                                                                                ");
        player.spigot().sendMessage(pingForHandItems);
        player.spigot().sendMessage(pingForArmorItems);
        player.spigot().sendMessage(pingWithSound);
        player.spigot().sendMessage(pingDisplayHidden);
        player.spigot().sendMessage(pingDisplaySubtitle);
        player.spigot().sendMessage(pingDisplayTitle);
        player.spigot().sendMessage(pingDisplayChat);
        player.spigot().sendMessage(pingDisplayActionbar);
        player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "                                                                                ");
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
        comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Click to preview " + ChatColor.WHITE + name)));
        return comp;
    }

    public TextComponent createCheckBox(Boolean checked, String command, String loreName, String loreDescription) {
        TextComponent box;
        if (checked) {
            box = new TextComponent(ChatColor.GREEN + "[ ✔ ]");
            box.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.RED + "Click to disable " + ChatColor.WHITE + loreName + (loreDescription == null ? "" : "\n" + ChatColor.GRAY + loreDescription))));
        } else {
            box = new TextComponent(ChatColor.RED + "[ ❌ ]");
            box.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GREEN + "Click to enable " + ChatColor.WHITE + loreName + (loreDescription == null ? "" : "\n" + ChatColor.GRAY + loreDescription))));
        }
        box.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return box;
    }

    List<String> arguments1 = new ArrayList<String>();
    List<String> previewArguments = new ArrayList<String>();
    List<String> setArguments = new ArrayList<String>();
    List<String> displayArguments = new ArrayList<String>();
    List<String> tfArguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return null;

        Player player = (Player) sender;

        if (arguments1.isEmpty()) {
            arguments1.add("config");
            arguments1.add("preview");
            arguments1.add("set");
        }

        if (previewArguments.isEmpty()) {
            previewArguments.add("sound");
            previewArguments.add("display_subtitle");
            previewArguments.add("display_title");
            previewArguments.add("display_chat");
            previewArguments.add("display_actionbar");
        }

        if (setArguments.isEmpty()) {
            setArguments.add("ping_for_hand_items");
            setArguments.add("ping_for_armor_items");
            setArguments.add("ping_with_sound");
            setArguments.add("display");
        }

        if (tfArguments.isEmpty()) {
            tfArguments.add("true");
            tfArguments.add("false");
        }

        if (displayArguments.isEmpty()) {
            displayArguments.add("hidden");
            displayArguments.add("subtitle");
            displayArguments.add("title");
            displayArguments.add("chat");
            displayArguments.add("actionbar");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args[0].equalsIgnoreCase("preview") && args.length == 2) {
            for (String a : previewArguments) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args[0].equalsIgnoreCase("set") && args.length == 2) {
            for (String a : setArguments) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if (args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("display")) {
            for (String a : displayArguments) {
                if (a.toLowerCase().startsWith(args[2].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args[0].equalsIgnoreCase("set") && (args[1].equalsIgnoreCase("ping_for_hand_items") || args[1].equalsIgnoreCase("ping_for_armor_items") || args[1].equalsIgnoreCase("ping_with_sound"))) {
            for (String a : tfArguments) {
                if (a.toLowerCase().startsWith(args[2].toLowerCase()))
                    result.add(a);
            }
            return result;
        }


        return null;
    }
}
