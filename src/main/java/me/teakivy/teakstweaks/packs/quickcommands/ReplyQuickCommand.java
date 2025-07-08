package me.teakivy.teakstweaks.packs.quickcommands;

import me.teakivy.teakstweaks.utils.command.*;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.permission.Permission;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
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
            super(CommandType.PLAYER_ONLY, "quick-commands", "message", null, List.of("msg", "tell", "whisper", "w"), "quick_commands.message", Arg.required("player"), Arg.required("message"));
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player target = Bukkit.getPlayer(event.getArg(0));
            Player player = event.getPlayer();
            if (target == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }

            StringBuilder message = new StringBuilder();
            for (int i = 1; i < event.getArgs().length; i++) {
                message.append(event.getArg(i)).append(" ");
            }

            target.sendMessage(get("quick_commands.message.whisper_to_you", Placeholder.parsed("player", player.getName()), Placeholder.parsed("message", message.toString())));
            sendMessage("whisper_to_player", Placeholder.parsed("player", player.getName()), Placeholder.parsed("message", message.toString()));

            lastMessage.put(player.getUniqueId(), target.getUniqueId());
            lastMessage.put(target.getUniqueId(), player.getUniqueId());
        }

        @Override
        public List<String> tabComplete(TabCompleteEvent event) {
            if (event.isArg(0)) {
                List<String> result = new ArrayList<>();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getUniqueId().equals(event.getPlayer().getUniqueId())) continue;
                    result.add(p.getName());
                }
                return result;
            }

            if (event.isArg(1)) return List.of("<message>");

            return null;
        }
    }

    class ReplyCommand extends AbstractCommand {
        public ReplyCommand() {
            super(CommandType.PLAYER_ONLY, "quick-commands", "reply", Permission.COMMAND_REPLY, List.of("r"), "quick_commands.reply", Arg.required("message"));
        }

        @Override
        public void playerCommand(PlayerCommandEvent event) {
            Player player = event.getPlayer();
            UUID targetUUID = lastMessage.get(player.getUniqueId());
            if (targetUUID == null) {
                sendError("no_reply");
                return;
            }

            Player target = player.getServer().getPlayer(targetUUID);
            if (target == null) {
                sendError(ErrorType.PLAYER_DNE);
                return;
            }

            StringBuilder message = new StringBuilder();
            for (String arg : event.getArgs()) {
                message.append(arg).append(" ");
            }

            target.sendMessage(get("quick_commands.message.whisper_to_you", Placeholder.parsed("player", player.getName()), Placeholder.parsed("message", message.toString())));
            sendMessage(get("quick_commands.message.whisper_to_player", Placeholder.parsed("player", player.getName()), Placeholder.parsed("message", message.toString())));

            lastMessage.put(player.getUniqueId(), targetUUID);
            lastMessage.put(targetUUID, player.getUniqueId());
        }

        @Override
        public List<String> tabComplete(TabCompleteEvent event) {
            if (event.isArg(0)) return List.of("<message>");

            return null;
        }

    }

}
