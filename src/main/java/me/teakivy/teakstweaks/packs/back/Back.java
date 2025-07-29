package me.teakivy.teakstweaks.packs.back;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Back extends BasePack {

    public Back() {
        super(TTPack.BACK, Material.REDSTONE_TORCH);
    }

    public static HashMap<UUID, Location> backLoc = new HashMap<>();

    public static void tpBack(Player player) {
        if (!Permission.COMMAND_BACK.check(player)) {
            player.sendMessage(ErrorType.MISSING_PERMISSION.m());
            return;
        }
        if (backLoc.containsKey(player.getUniqueId())) {
            player.teleportAsync(backLoc.get(player.getUniqueId()));
        } else {
            player.sendMessage(Component.translatable("back.error.no_back_location"));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!Permission.BACK_DEATH.check(player)) return;

        backLoc.put(player.getUniqueId(), player.getLocation());
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (!Permission.BACK_TELEPORT.check(event.getPlayer())) return;

        List<PlayerTeleportEvent.TeleportCause> causes =
                List.of(PlayerTeleportEvent.TeleportCause.SPECTATE,
                        PlayerTeleportEvent.TeleportCause.COMMAND,
                        PlayerTeleportEvent.TeleportCause.PLUGIN,
                        PlayerTeleportEvent.TeleportCause.CONSUMABLE_EFFECT,
                        PlayerTeleportEvent.TeleportCause.END_GATEWAY,
                        PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
        if (causes.contains(event.getCause())) {
            backLoc.put(event.getPlayer().getUniqueId(), event.getFrom());
        }
    }

}
