package me.teakivy.teakstweaks.packs.survival.coordshud;

import me.teakivy.teakstweaks.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DisplayHud {

    private static World world;
    static Main main = Main.getPlugin(Main.class);

    static String hudMessage;

    public static void init() {
        hudMessage = main.getConfig().getString("packs.coords-hud.message");
        world =  Bukkit.getWorld(main.getConfig().getString("packs.coords-hud.time-world"));
        if (world == null) world = Bukkit.getWorlds().get(0);
    }

    public static void showHud(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            String msg = ChatColor.translateAlternateColorCodes('&', hudMessage);
            Location loc = player.getLocation().getBlock().getLocation();
            if (Main.getInstance().getConfig().getBoolean("packs.coords-hud.use-player-position")) {
                loc = player.getLocation();
            }
            msg = msg.replace("%x%", ((int) loc.getX()) + "");
            msg = msg.replace("%y%", ((int) loc.getY()) + "");
            msg = msg.replace("%z%", ((int) loc.getZ()) + "");

            Location playerLocation = player.getLocation();
            float playerDirection = playerLocation.getYaw();
            String direction = getDirection(playerDirection);
            String directionAbbr = getDirectionAbbr(playerDirection);
            String twoPointDirection = get2PointDirection(playerDirection);
            String twoPointDirectionAbbr = get2PointDirectionAbbr(playerDirection);
            String worldTime = getWorldTime();
            String worldTime12Hr = getWorldTime12Hr();
            String worldTimeAmPm = getWorldTimeAmPm();
            String biome = getBiome(player);
            String slimeChunk = getSlimeChunk(player);
            String durability = getDurability(player);
            String durabilityMainHand = getDurabilityMainHand(player);
            String durabilityOffHand = getDurabilityOffHand(player);
            String durabilityHelmet = getDurabilityHelmet(player);
            String durabilityChestplate = getDurabilityChestplate(player);
            String durabilityLeggings = getDurabilityLeggings(player);
            String durabilityBoots = getDurabilityBoots(player);
            String targetBlock = getTargetBlock(player);
            String world = getWorld(player);
            String facingXZ = getFacingXZ(playerDirection);

            msg = msg.replace("%direction%", direction);
            msg = msg.replace("%direction_lower%", direction.toLowerCase());
            msg = msg.replace("%direction_upper%", direction.toUpperCase());

            msg = msg.replace("%direction_abbreviated%", directionAbbr);
            msg = msg.replace("%direction_abbreviated_lower%", directionAbbr.toLowerCase());
            msg = msg.replace("%direction_abbreviated_upper%", directionAbbr.toUpperCase());

            msg = msg.replace("%direction_two_point%", twoPointDirection);
            msg = msg.replace("%direction_two_point_lower%", twoPointDirection.toLowerCase());
            msg = msg.replace("%direction_two_point_upper%", twoPointDirection.toUpperCase());

            msg = msg.replace("%direction_abbreviated_two_point%", twoPointDirectionAbbr);
            msg = msg.replace("%direction_abbreviated_two_point_lower%", twoPointDirectionAbbr.toLowerCase());
            msg = msg.replace("%direction_abbreviated_two_point_upper%", twoPointDirectionAbbr.toUpperCase());

            msg = msg.replace("%world_time%", worldTime);
            msg = msg.replace("%world_time_12hr%", worldTime12Hr);

            msg = msg.replace("%am_pm%", worldTimeAmPm);
            msg = msg.replace("%am_pm_lower%", worldTimeAmPm.toLowerCase());
            msg = msg.replace("%am_pm_upper%", worldTimeAmPm.toUpperCase());

            msg = msg.replace("%yaw%", getYaw(player));
            msg = msg.replace("%pitch%", getPitch(player));

            msg = msg.replace("%biome%", biome);
            msg = msg.replace("%biome_lower%", biome.toLowerCase());
            msg = msg.replace("%biome_upper%", biome.toUpperCase());

            msg = msg.replace("%slime_chunk%", slimeChunk);
            msg = msg.replace("%slime_chunk_lower%", slimeChunk.toLowerCase());
            msg = msg.replace("%slime_chunk_upper%", slimeChunk.toUpperCase());

            msg = msg.replace("%item_durability%", durability);
            msg = msg.replace("%item_durability_main_hand%", durabilityMainHand);
            msg = msg.replace("%item_durability_off_hand%", durabilityOffHand);
            msg = msg.replace("%item_durability_helmet%", durabilityHelmet);
            msg = msg.replace("%item_durability_chestplate%", durabilityChestplate);
            msg = msg.replace("%item_durability_leggings%", durabilityLeggings);
            msg = msg.replace("%item_durability_boots%", durabilityBoots);

            msg = msg.replace("%target_block%", targetBlock);
            msg = msg.replace("%target_block_lower%", targetBlock.toLowerCase());
            msg = msg.replace("%target_block_upper%", targetBlock.toUpperCase());
            msg = msg.replace("%target_block_x%", getTargetBlockX(player));
            msg = msg.replace("%target_block_y%", getTargetBlockY(player));
            msg = msg.replace("%target_block_z%", getTargetBlockZ(player));

            msg = msg.replace("%world%", world);
            msg = msg.replace("%world_lower%", world.toLowerCase());
            msg = msg.replace("%world_upper%", world.toUpperCase());

            msg = msg.replace("%facing%", facingXZ);
            msg = msg.replace("%facing_lower%", facingXZ.toLowerCase());
            msg = msg.replace("%facing_upper%", facingXZ.toUpperCase());

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
        });
    }

    public static String get2PointDirection(float direction) {
        if (direction <= -157.5 || direction >= 157.5) return "North";
        if (direction >= 112.5 && direction <= 157.5) return "NorthWest";
        if (direction >= 67.5 && direction <= 112.5) return "West";
        if (direction >= 22.5 && direction <= 67.5) return "SouthWest";
        if (direction >= -22.5 && direction <= 22.5) return "South";
        if (direction >= -67.5 && direction <= -22.5) return "SouthEast";
        if (direction >= -112.5 && direction <= -67.5) return "East";
        if (direction >= -157.5 && direction <= -112.5) return "NorthEast";

        return "N/A";
    }

    public static String get2PointDirectionAbbr(float direction) {
        if (direction <= -157.5 || direction >= 157.5) return "N";
        if (direction >= 112.5 && direction <= 157.5) return "NW";
        if (direction >= 67.5 && direction <= 112.5) return "W";
        if (direction >= 22.5 && direction <= 67.5) return "SW";
        if (direction >= -22.5 && direction <= 22.5) return "S";
        if (direction >= -67.5 && direction <= -22.5) return "SE";
        if (direction >= -112.5 && direction <= -67.5) return "E";
        if (direction >= -157.5 && direction <= -112.5) return "NE";

        return "N/A";
    }

    public static String getDirection(float direction) {
        String dir = get2PointDirection(direction);
        if (dir.length() > 5) {
            return dir.replace("East", "").replace("West", "");
        }

        return dir;
    }

    public static String getDirectionAbbr(float direction) {
        return get2PointDirectionAbbr(direction).substring(0, 1);
    }

    public static String getWorldTime() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        int minutes = (int) ((ticks % 1000 / 10) * 0.6);
        return String.format("%02d:%02d", hours, minutes);
    }

    public static String getWorldTime12Hr() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        if (hours >= 12) hours -= 12;
        if (hours == 0) hours = 12;
        int minutes = (int) ((ticks % 1000 / 10) * 0.6);
        return String.format("%02d:%02d", hours, minutes);
    }

    public static String getWorldTimeAmPm() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        hours++;
        boolean am = hours <= 12;
        if (am) return "Am";
        return "Pm";
    }

    public static String getYaw(Player player) {
        return ((int) player.getLocation().getYaw()) + "";
    }

    public static String getPitch(Player player) {
        return ((int) player.getLocation().getPitch()) + "";
    }

    public static String getBiome(Player player) {
        return player.getLocation().getBlock().getBiome().toString() + "";
    }

    public static String getSlimeChunk(Player player) {
        return player.getLocation().getChunk().isSlimeChunk() + "";
    }

    public static String getDurability(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) return "0";
        return ((item.getType().getMaxDurability()) - (item.getDurability())) + "";
    }

    public static String getDurabilityMainHand(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) return "0";
        return ((item.getType().getMaxDurability()) - (item.getDurability())) + "";
    }

    public static String getDurabilityOffHand(Player player) {
        ItemStack item = player.getInventory().getItemInOffHand();
        if (item == null || item.getType() == Material.AIR) return "0";
        return ((item.getType().getMaxDurability()) - (item.getDurability())) + "";
    }

    public static String getDurabilityHelmet(Player player) {
        ItemStack item = player.getInventory().getHelmet();
        if (item == null || item.getType() == Material.AIR) return "0";
        return ((item.getType().getMaxDurability()) - (item.getDurability())) + "";
    }

    public static String getDurabilityChestplate(Player player) {
        ItemStack item = player.getInventory().getChestplate();
        if (item == null || item.getType() == Material.AIR) return "0";
        return ((item.getType().getMaxDurability()) - (item.getDurability())) + "";
    }

    public static String getDurabilityLeggings(Player player) {
        ItemStack item = player.getInventory().getLeggings();
        if (item == null || item.getType() == Material.AIR) return "0";
        return ((item.getType().getMaxDurability()) - (item.getDurability())) + "";
    }

    public static String getDurabilityBoots(Player player) {
        ItemStack item = player.getInventory().getBoots();
        if (item == null || item.getType() == Material.AIR) return "0";
        return ((item.getType().getMaxDurability()) - (item.getDurability())) + "";
    }

    public static String getTargetBlock(Player player) {
        return player.getTargetBlock(null, 5).getType().toString();
    }

    public static String getTargetBlockX(Player player) {
        return player.getTargetBlock(null, 5).getX() + "";
    }

    public static String getTargetBlockY(Player player) {
        return player.getTargetBlock(null, 5).getY() + "";
    }

    public static String getTargetBlockZ(Player player) {
        return player.getTargetBlock(null, 5).getZ() + "";
    }

    public static String getWorld(Player player) {
        return player.getWorld().getName();
    }

    public static String getFacingXZ(float direction) {
        if (direction <= -135 || direction >= 135) return "-Z";
        if (direction <= 135 && direction >= 45) return "-X";
        if (direction >= -45 && direction <= 45) return "+Z";
        if (direction <= -45 && direction >= -135) return "+X";

        return "N/A";
    }
}
