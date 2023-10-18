package me.teakivy.teakstweaks.packs.teleportation.homes;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Home {
    private String name;
    private UUID owner;
    private Location loc;

    public Home(String name, UUID owner, Location loc) {
        this.name = name;
        this.owner = owner;
        this.loc = loc;
    }

    public Home(String name, UUID owner, String locString) {
        this.name = name;
        this.owner = owner;
        String world = locString.split("world=")[1].split(";")[0];
        double x = Double.parseDouble(locString.split("x=")[1].split(";")[0]);
        double y = Double.parseDouble(locString.split("y=")[1].split(";")[0]);
        double z = Double.parseDouble(locString.split("z=")[1].split(";")[0]);
        float yaw = Float.parseFloat(locString.split("yaw=")[1].split(";")[0]);
        float pitch = Float.parseFloat(locString.split("pitch=")[1].split(";")[0]);

        this.loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public String getName() {
        return name;
    }

    public UUID getOwner() {
        return owner;
    }

    public Location getLoc() {
        return loc;
    }

    public String getLocString() {
        return "world=" + loc.getWorld().getName() + ";x=" + loc.getX() + ";y=" + loc.getY() + ";z=" + loc.getZ() + ";yaw=" + loc.getYaw() + ";pitch=" + loc.getPitch();
    }

    public void teleport() {
        Player player = Bukkit.getPlayer(owner);
        if (player == null) return;

        if (HomesPack.onCooldown(player)) {
            player.sendMessage(Translatable.get("homes.error.on_cooldown").replace("%time%", String.valueOf(HomesPack.getCooldown(player))));
            return;
        }

        if (Main.getInstance().getConfig().getInt("packs.homes.teleport-delay") > 0) {
            player.sendMessage(Translatable.get("homes.teleporting_in").replace("%time%", Main.getInstance().getConfig().getInt("packs.homes.teleport-delay") + ""));
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                player.teleport(loc);
                player.sendMessage(Translatable.get("homes.teleported").replace("%home%", name));
            }, Main.getInstance().getConfig().getInt("packs.homes.teleport-delay") * 20L);
        } else {
            player.teleport(loc);
            player.sendMessage(Translatable.get("homes.teleported").replace("%home%", name));
        }
    }

    public void delete() {
        Player player = Bukkit.getPlayer(owner);
        if (player == null) return;

        PersistentDataContainer data = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "homes");
        String homes = data.get(key, PersistentDataType.STRING);
        if (homes == null) return;

        String[] homesArray = homes.split(",");
        String newHomes = "";
        for (String home : homesArray) {
            if (!home.equals(name)) {
                newHomes += home + ",";
            }
        }

        data.set(key, PersistentDataType.STRING, newHomes);

        data.remove(new NamespacedKey(Main.getInstance(), "home." + name));

        player.sendMessage(Translatable.get("homes.deleted").replace("%home%", name));
    }

}