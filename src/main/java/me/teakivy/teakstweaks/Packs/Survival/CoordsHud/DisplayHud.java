package me.teakivy.teakstweaks.Packs.Survival.CoordsHud;

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
        String msg = ChatColor.translateAlternateColorCodes('&', hudMessage);
        msg = msg.replace("%x%", ((int) player.getLocation().getX()) + "");
        msg = msg.replace("%y%", ((int) player.getLocation().getY()) + "");
        msg = msg.replace("%z%", ((int) player.getLocation().getZ()) + "");

        Location playerLocation = player.getLocation();
        float playerDirection = playerLocation.getYaw();

        msg = msg.replace("%direction%", getDirection(playerDirection));
        msg = msg.replace("%direction_lower%", getDirectionLower(playerDirection));
        msg = msg.replace("%direction_upper%", getDirectionUpper(playerDirection));

        msg = msg.replace("%direction_abbreviated%", getDirectionAbbr(playerDirection));
        msg = msg.replace("%direction_abbreviated_lower%", getDirectionAbbrLower(playerDirection));
        msg = msg.replace("%direction_abbreviated_upper%", getDirectionAbbrUpper(playerDirection));


        msg = msg.replace("%direction_two_point%", get2PointDirection(playerDirection));
        msg = msg.replace("%direction_two_point_lower%", get2PointDirectionLower(playerDirection));
        msg = msg.replace("%direction_two_point_upper%", get2PointDirectionUpper(playerDirection));

        msg = msg.replace("%direction_abbreviated_two_point%", get2PointDirectionAbbr(playerDirection));
        msg = msg.replace("%direction_abbreviated_two_point_lower%", get2PointDirectionAbbrLower(playerDirection));
        msg = msg.replace("%direction_abbreviated_two_point_upper%", get2PointDirectionAbbrUpper(playerDirection));


        msg = msg.replace("%world_time%", getWorldTime());
        msg = msg.replace("%world_time_12hr%", getWorldTime12Hr());

        msg = msg.replace("%am_pm%", getWorldTimeAmPm());
        msg = msg.replace("%am_pm_lower%", getWorldTimeAmPmLower());
        msg = msg.replace("%am_pm_upper%", getWorldTimeAmPmUpper());

        msg = msg.replace("%yaw%", getYaw(player));
        msg = msg.replace("%pitch%", getPitch(player));

        msg = msg.replace("%biome%", getBiome(player));
        msg = msg.replace("%biome_upper%", getBiomeUpper(player));
        msg = msg.replace("%biome_lower%", getBiomeLower(player));

        msg = msg.replace("%slime_chunk%", getSlimeChunk(player));
        msg = msg.replace("%slime_chunk_lower%", getSlimeChunkLower(player));
        msg = msg.replace("%slime_chunk_upper%", getSlimeChunkUpper(player));

        msg = msg.replace("%item_durability%", getDurability(player));
        msg = msg.replace("%item_durability_main_hand%", getDurabilityMainHand(player));
        msg = msg.replace("%item_durability_off_hand%", getDurabilityOffHand(player));

        msg = msg.replace("%item_durability_helmet%", getDurabilityHelmet(player));
        msg = msg.replace("%item_durability_chestplate%", getDurabilityChestplate(player));
        msg = msg.replace("%item_durability_leggings%", getDurabilityLeggings(player));
        msg = msg.replace("%item_durability_boots%", getDurabilityBoots(player));

        msg = msg.replace("%target_block%", getTargetBlock(player));
        msg = msg.replace("%target_block_lower%", getTargetBlockLower(player));
        msg = msg.replace("%target_block_upper%", getTargetBlockUpper(player));

        msg = msg.replace("%target_block_x%", getTargetBlockX(player));
        msg = msg.replace("%target_block_y%", getTargetBlockY(player));
        msg = msg.replace("%target_block_z%", getTargetBlockZ(player));

        msg = msg.replace("%world%", getWorld(player));
        msg = msg.replace("%world_lower%", getWorldLower(player));
        msg = msg.replace("%world_upper%", getWorldUpper(player));

        msg = msg.replace("%facing%", getFacingXZ(playerDirection));
        msg = msg.replace("%facing_lower%", getFacingXZLower(playerDirection));
        msg = msg.replace("%facing_upper%", getFacingXZUpper(playerDirection));

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
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

    public static String get2PointDirectionUpper(float direction) {
        if (direction <= -157.5 || direction >= 157.5) return "NORTH";
        if (direction >= 112.5 && direction <= 157.5) return "NORTHWEST";
        if (direction >= 67.5 && direction <= 112.5) return "WEST";
        if (direction >= 22.5 && direction <= 67.5) return "SOUTHWEST";
        if (direction >= -22.5 && direction <= 22.5) return "SOUTH";
        if (direction >= -67.5 && direction <= -22.5) return "SOUTHEAST";
        if (direction >= -112.5 && direction <= -67.5) return "EAST";
        if (direction >= -157.5 && direction <= -112.5) return "NORTHEAST";

        return "N/A";
    }

    public static String get2PointDirectionLower(float direction) {
        if (direction <= -157.5 || direction >= 157.5) return "north";
        if (direction >= 112.5 && direction <= 157.5) return "northwest";
        if (direction >= 67.5 && direction <= 112.5) return "west";
        if (direction >= 22.5 && direction <= 67.5) return "southwest";
        if (direction >= -22.5 && direction <= 22.5) return "south";
        if (direction >= -67.5 && direction <= -22.5) return "southeast";
        if (direction >= -112.5 && direction <= -67.5) return "east";
        if (direction >= -157.5 && direction <= -112.5) return "northeast";

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

    public static String get2PointDirectionAbbrUpper(float direction) {
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

    public static String get2PointDirectionAbbrLower(float direction) {
        if (direction <= -157.5 || direction >= 157.5) return "n";
        if (direction >= 112.5 && direction <= 157.5) return "nw";
        if (direction >= 67.5 && direction <= 112.5) return "w";
        if (direction >= 22.5 && direction <= 67.5) return "sw";
        if (direction >= -22.5 && direction <= 22.5) return "s";
        if (direction >= -67.5 && direction <= -22.5) return "se";
        if (direction >= -112.5 && direction <= -67.5) return "e";
        if (direction >= -157.5 && direction <= -112.5) return "ne";

        return "N/A";
    }

    public static String getDirection(float direction) {
        if (direction <= -135 || direction >= 135) return "North";
        if (direction <= 135 && direction >= 45) return "West";
        if (direction >= -45 && direction <= 45) return "South";
        if (direction <= -45 && direction >= -135) return "East";

        return "N/A";
    }

    public static String getDirectionLower(float direction) {
        if (direction <= -135 || direction >= 135) return "north";
        if (direction <= 135 && direction >= 45) return "west";
        if (direction >= -45 && direction <= 45) return "south";
        if (direction <= -45 && direction >= -135) return "east";

        return "n/a";
    }

    public static String getDirectionUpper(float direction) {
        if (direction <= -135 || direction >= 135) return "NORTH";
        if (direction <= 135 && direction >= 45) return "WEST";
        if (direction >= -45 && direction <= 45) return "SOUTH";
        if (direction <= -45 && direction >= -135) return "EAST";

        return "N/A";
    }

    public static String getDirectionAbbr(float direction) {
        if (direction <= -135 || direction >= 135) return "N";
        if (direction <= 135 && direction >= 45) return "W";
        if (direction >= -45 && direction <= 45) return "S";
        if (direction <= -45 && direction >= -135) return "E";

        return "N/A";
    }

    public static String getDirectionAbbrLower(float direction) {
        if (direction <= -135 || direction >= 135) return "n";
        if (direction <= 135 && direction >= 45) return "w";
        if (direction >= -45 && direction <= 45) return "s";
        if (direction <= -45 && direction >= -135) return "e";

        return "n/a";
    }

    public static String getDirectionAbbrUpper(float direction) {
        if (direction <= -135 || direction >= 135) return "N";
        if (direction <= 135 && direction >= 45) return "W";
        if (direction >= -45 && direction <= 45) return "S";
        if (direction <= -45 && direction >= -135) return "E";

        return "N/A";
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

    public static String getWorldTimeAmPmUpper() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        hours++;
        boolean am = hours <= 12;
        if (am) return "AM";
        return "PM";
    }

    public static String getWorldTimeAmPmLower() {
        long ticks = world.getTime();
        int hours = (int) (((ticks / 1000) + 6) % 24);
        hours++;
        boolean am = hours <= 12;
        if (am) return "am";
        return "pm";
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

    public static String getBiomeUpper(Player player) {
        return player.getLocation().getBlock().getBiome().toString().toUpperCase() + "";
    }

    public static String getBiomeLower(Player player) {
        return player.getLocation().getBlock().getBiome().toString().toLowerCase() + "";
    }

    public static String getSlimeChunk(Player player) {
        return player.getLocation().getChunk().isSlimeChunk() + "";
    }

    public static String getSlimeChunkUpper(Player player) {
        return (player.getLocation().getChunk().isSlimeChunk() + "").toUpperCase();
    }

    public static String getSlimeChunkLower(Player player) {
        return (player.getLocation().getChunk().isSlimeChunk() + "").toLowerCase();
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

    public static String getTargetBlockUpper(Player player) {
        return player.getTargetBlock(null, 5).getType().toString().toUpperCase();
    }

    public static String getTargetBlockLower(Player player) {
        return player.getTargetBlock(null, 5).getType().toString().toLowerCase();
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

    public static String getWorldUpper(Player player) {
        return player.getWorld().getName().toUpperCase();
    }

    public static String getWorldLower(Player player) {
        return player.getWorld().getName().toLowerCase();
    }

    public static String getFacingXZ(float direction) {
        if (direction <= -135 || direction >= 135) return "-Z";
        if (direction <= 135 && direction >= 45) return "-X";
        if (direction >= -45 && direction <= 45) return "+Z";
        if (direction <= -45 && direction >= -135) return "+X";

        return "N/A";
    }

    public static String getFacingXZLower(float direction) {
        if (direction <= -135 || direction >= 135) return "-z";
        if (direction <= 135 && direction >= 45) return "-x";
        if (direction >= -45 && direction <= 45) return "+z";
        if (direction <= -45 && direction >= -135) return "+x";

        return "N/A";
    }

    public static String getFacingXZUpper(float direction) {
        if (direction <= -135 || direction >= 135) return "-Z";
        if (direction <= 135 && direction >= 45) return "-X";
        if (direction >= -45 && direction <= 45) return "+Z";
        if (direction <= -45 && direction >= -135) return "+X";

        return "N/A";
    }
}
