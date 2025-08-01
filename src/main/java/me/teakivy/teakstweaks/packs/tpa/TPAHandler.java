package me.teakivy.teakstweaks.packs.tpa;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.TranslationManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TPAHandler {
    private static final List<TPARequest> tpaRequests = new ArrayList<>();

    public static List<TPARequest> getRequests() {
        return tpaRequests;
    }

    public static TPARequest getRequest(Player sender, Player target) {
        for (TPARequest request : tpaRequests) {
            if (!request.getSender().getUniqueId().equals(sender.getUniqueId())) continue;
            if (!request.getTarget().getUniqueId().equals(target.getUniqueId())) continue;

            return request;
        }
        return null;
    }

    public static List<TPARequest> getPendingRequests(Player target) {
        List<TPARequest> requests = new ArrayList<>();
        for (TPARequest request : tpaRequests) {
            if (!request.getTarget().getUniqueId().equals(target.getUniqueId())) continue;
            requests.add(request);
        }
        return requests;
    }

    public static boolean hasOutgoingRequest(Player sender) {
        for (TPARequest request : tpaRequests) {
            if (!request.getSender().getUniqueId().equals(sender.getUniqueId())) continue;
            return true;
        }
        return false;
    }

    public static TPARequest getOutgoingRequest(Player sender) {
        for (TPARequest request : tpaRequests) {
            if (!request.getSender().getUniqueId().equals(sender.getUniqueId())) continue;
            return request;
        }
        return null;
    }

    public static TPARequest getMostRecentRequest(Player target) {
        TPARequest mostRecent = null;
        for (TPARequest request : tpaRequests) {
            if (!request.getTarget().getUniqueId().equals(target.getUniqueId())) continue;
            if (mostRecent == null || request.getTime() > mostRecent.getTime()) {
                mostRecent = request;
            }
        }
        return mostRecent;
    }

    public static void sendRequest(TPARequest request) {
        tpaRequests.add(request);
        Player sender = request.getSender();
        Player target = request.getTarget();

        String text = "<hover:show_text:\"" + getString("request_message.hover") + "\"><click:run_command:/tpa accept " + sender.getName() + ">" + getString(request.getType().getKey() + ".request_message") + "</click></hover>";
        Component targetMessage = MiniMessage.miniMessage().deserialize(text, Placeholder.parsed("player", sender.getName()));
        target.sendMessage(targetMessage);

        Component senderMessage = MiniMessage.miniMessage().deserialize(getString(request.getType().getKey() + ".request_sent"), Placeholder.parsed("player", target.getName()));
        sender.sendMessage(senderMessage);

        Bukkit.getScheduler().scheduleSyncDelayedTask(TeaksTweaks.getInstance(), () -> {
            if (request.isAccepted()) return;

            expireRequest(request);
        }, 60 * 20L);
    }

    public static void acceptRequest(TPARequest request) {
        request.accept();

        tpaRequests.remove(request);
    }

    private static String getString(String key) {
        return TranslationManager.getString(Config.getLanguage(), "tpa." + key);
    }

    public static void expireRequest(TPARequest request) {
        Component senderMessage = MiniMessage.miniMessage().deserialize(getString("request_expired"), Placeholder.parsed("player", request.getTarget().getName()));
        request.getSender().sendMessage(senderMessage);
        tpaRequests.remove(request);
    }

    public static void cancelRequest(TPARequest request) {
        Component senderMessage = MiniMessage.miniMessage().deserialize(getString("request_cancelled"), Placeholder.parsed("player", request.getTarget().getName()));
        request.getSender().sendMessage(senderMessage);
        tpaRequests.remove(request);
    }
}
