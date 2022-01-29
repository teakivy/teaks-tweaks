package me.teakivy.teakstweaks.Packs.Teleportation.Back;

import me.teakivy.teakstweaks.Packs.BasePack;
import me.teakivy.teakstweaks.Utils.ErrorType;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class Back extends BasePack {

    public Back() {
        super("Back", "back");
    }

    public static HashMap<UUID, Location> backLoc = new HashMap<>();

    public static void tpBack(Player player) {
        if (!player.hasPermission("teakstweaks.back.teleport")) {
            player.sendMessage(ErrorType.MISSING_PERMISSION.m());
            return;
        }
        if (backLoc.containsKey(player.getUniqueId())) {
            player.teleport(backLoc.get(player.getUniqueId()));
        } else {
            player.sendMessage(MessageHandler.getMessage("pack.back.no-spot"));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!getConfig().getBoolean("save-death-location")) return;

        backLoc.put(player.getUniqueId(), player.getLocation());
    }

}
