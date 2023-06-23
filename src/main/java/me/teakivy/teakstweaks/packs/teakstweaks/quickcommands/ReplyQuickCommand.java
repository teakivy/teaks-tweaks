package me.teakivy.teakstweaks.packs.teakstweaks.quickcommands;

import me.teakivy.teakstweaks.utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ReplyQuickCommand {

    private final HashMap<UUID, UUID> lastMessage = new HashMap<>();

    public void register() {
        new MessageCommand().register();
        new ReplyCommand().register();
    }


    class MessageCommand extends AbstractCommand {
        public MessageCommand() {
            super("quick-commands", "message", "/message <player> <message>", "Send a private message to a player", null, List.of("msg", "tell", "whisper", "w"));
        }

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /message <player> <message>");
                return true;
            }

            Player target = sender.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online!");
                return true;
            }

            StringBuilder message = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }

            target.sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + sender.getName() + " whispers to you: " + message);
            sender.sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + "You whisper to " + target.getName() + ": " + message);

            lastMessage.put(((Player) sender).getUniqueId(), target.getUniqueId());
            lastMessage.put(target.getUniqueId(), ((Player) sender).getUniqueId());
            return false;
        }

        List<String> arguments1 = new ArrayList<>();

        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

            if (arguments1.isEmpty()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    arguments1.add(player.getName());
                }
            }

            List<String> result = new ArrayList<>();
            if (args.length == 1) {
                for (String a : arguments1) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                        result.add(a);
                }
                return result;
            }

            return List.of("<message>");
        }
    }

    class ReplyCommand extends AbstractCommand {
        public ReplyCommand() {
            super("quick-commands", "reply", "/reply <message>", "Reply to the most recent message", null, List.of("r"));
        }

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /reply <message>");
                return true;
            }

            UUID targetUUID = lastMessage.get(((Player) sender).getUniqueId());
            if (targetUUID == null) {
                sender.sendMessage(ChatColor.RED + "You have no one to reply to!");
                return true;
            }

            Player target = sender.getServer().getPlayer(targetUUID);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online!");
                return true;
            }

            StringBuilder message = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }

            target.sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + sender.getName() + " whispers to you: " + message);
            sender.sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + "You whisper to " + target.getName() + ": " + message);

            lastMessage.put(((Player) sender).getUniqueId(), targetUUID);
            lastMessage.put(targetUUID, ((Player) sender).getUniqueId());
            return false;
        }

        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            return List.of("<message>");
        }

    }

}
