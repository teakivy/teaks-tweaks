package me.teakivy.teakstweaks.packs.teleportation.back;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MessageHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class Back extends BasePack {

    public Back() {
        super("Back", "back", PackType.TELEPORTATION, Material.REDSTONE_TORCH, "Enter '/back' to go back to the last location you teleported from. (using Homes, Spawn, or TPA)", "You can also allow players to teleport back to their death location if that's enabled.");
    }

    public static HashMap<UUID, Location> backLoc = new HashMap<>();

    public static void tpBack(Player player) {
        if (!player.hasPermission("teakstweaks.back")) {
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
