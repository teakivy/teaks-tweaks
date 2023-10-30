package me.teakivy.teakstweaks.packs.teakstweaks.quickcommands;

import me.teakivy.teakstweaks.commands.AbstractCommand;
import me.teakivy.teakstweaks.commands.CommandType;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
            super("quick-commands", "message", "/message <player> <message>", Translatable.get("quick_commands.message.command_description"), null, List.of("msg", "tell", "whisper", "w"), CommandType.PLAYER_ONLY);
        }

        @Override
        public void playerCommand(Player player, String[] args) {
            if (args.length < 2) {
                sendUsage(player);
                return;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return;
            }

            StringBuilder message = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }

            target.sendMessage(get("quick_commands.message.whisper_to_you").replace("%player%", player.getName()).replace("%message%", message));
            player.sendMessage(get("quick_commands.message.whisper_to_player").replace("%player%", target.getName()).replace("%message%", message));

            lastMessage.put(player.getUniqueId(), target.getUniqueId());
            lastMessage.put(target.getUniqueId(), player.getUniqueId());
        }

        @Override
        public List<String> tabComplete(Player player, String[] args) {
            if (args.length == 1) {
                List<String> result = new ArrayList<>();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getUniqueId().equals(player.getUniqueId())) continue;
                    result.add(p.getName());
                }
                return result;
            }

            if (args.length == 2) return List.of("<message>");

            return null;
        }
    }

    class ReplyCommand extends AbstractCommand {
        public ReplyCommand() {
            super("quick-commands", "reply", "/reply <message>", Translatable.get("quick_commands.reply.command_description"), null, List.of("r"), CommandType.PLAYER_ONLY);
        }

        @Override
        public void playerCommand(Player player, String[] args) {
            if (args.length < 1) {
                sendUsage(player);
                return;
            }

            UUID targetUUID = lastMessage.get(player.getUniqueId());
            if (targetUUID == null) {
                player.sendMessage(get("quick_commands.reply.error.no_reply"));
                return;
            }

            Player target = player.getServer().getPlayer(targetUUID);
            if (target == null) {
                player.sendMessage(ErrorType.PLAYER_DNE.m());
                return;
            }

            StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(arg).append(" ");
            }

            target.sendMessage(get("quick_commands.message.whisper_to_you").replace("%player%", player.getName()).replace("%message%", message));
            player.sendMessage(get("quick_commands.message.whisper_to_player").replace("%player%", target.getName()).replace("%message%", message));

            lastMessage.put(player.getUniqueId(), targetUUID);
            lastMessage.put(targetUUID, player.getUniqueId());
        }

        @Override
        public List<String> tabComplete(Player player, String[] args) {
            if (args.length == 1) return List.of("<message>");

            return null;
        }

    }

}
