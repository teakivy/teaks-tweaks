package me.teakivy.teakstweaks.packs.graves;

import me.teakivy.teakstweaks.packs.armoredelytra.ArmoredElytras;
import me.teakivy.teakstweaks.utils.Base64Serializer;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class GraveCreator {

    public static ConfigurationSection config = Config.getPackConfig("graves");

    public static Location findGraveLocation(Location loc) {
        Location ogLoc = loc.clone();

        if (loc.getY() > loc.getWorld().getMaxHeight()) loc.setY(loc.getWorld().getMaxHeight());
        if (loc.getY() < loc.getWorld().getMinHeight()) {
            if (!config.getBoolean("allow-void-graves")) return null;
            loc.setY(loc.getWorld().getMaxHeight());
        }

        if (getTopBlock(loc, loc.getWorld().getMaxHeight()) == null) {
            if (!config.getBoolean("allow-void-graves")) return null;
            loc.setY(loc.getWorld().getMinHeight());

            loc.getBlock().setType(Material.GRASS_BLOCK);

            loc.setY(loc.getY() + 1);
            return loc;
        }

        loc = getNextAir(loc);

        if (loc == null) {
            if (!config.getBoolean("allow-void-graves")) return null;
            loc = new Location(ogLoc.getWorld(), ogLoc.getX(), ogLoc.getWorld().getMinHeight(), ogLoc.getZ());
        }

        Location tb = getTopBlock(loc, loc.getBlockY());
        if (tb != null && tb.getBlockY() < loc.getBlockY()) {
            loc.setY(tb.getY() + 1);
        }

        return loc;
    }

    public static void createGrave(Location location, Player player, int xp) throws IOException {
        Location loc = location.getBlock().getLocation().add(.5, 0, .5);
        ArmorStand as = (ArmorStand) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.add(0, -1.4, 0), EntityType.ARMOR_STAND);
        as.setGravity(false);
        as.setInvisible(true);
        as.setHelmet(new ItemStack(Material.STONE_BRICK_WALL));
        as.setInvulnerable(true);
        as.setCustomName(player.getName());
        as.addScoreboardTag("grave");
        as.setCustomNameVisible(true);

        PersistentDataContainer data = as.getPersistentDataContainer();
        NamespacedKey key = Key.get("grave_owner_uuid");
        data.set(key, PersistentDataType.STRING, player.getUniqueId().toString());

        if (!Boolean.TRUE.equals(location.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY))) {
            NamespacedKey key2 = Key.get("grave_owner_items");
            data.set(key2, PersistentDataType.STRING, serializeItems(player));

            NamespacedKey key3 = Key.get("grave_owner_xp");
            data.set(key3, PersistentDataType.INTEGER, xp);
        }

        if (!config.getBoolean("console-info")) return;

        Logger.info(Translatable.get("graves.log.created",
                Placeholder.parsed("player", player.getName()),
                Placeholder.parsed("x", loc.getBlockX() + ""),
                Placeholder.parsed("y", loc.getBlockY() + ""),
                Placeholder.parsed("z", loc.getBlockZ() + ""),
                Placeholder.parsed("world", loc.getWorld().getName())));
        int items = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null) continue;
            items += item.getAmount();
        }
        Logger.info(Translatable.get("graves.log.contains",
                Placeholder.parsed("item_count", items + ""),
                Placeholder.parsed("xp_count", xp + "")));
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null) continue;
            String enchantString = "";
            if (item.hasItemMeta()) {
                Map<Enchantment, Integer> enchantments = item.getEnchantments();
                if (!enchantments.isEmpty()) {
                    StringBuilder enchantStringBuilder = new StringBuilder();
                    for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                        enchantStringBuilder.append(entry.getKey().getKey().getKey()).append(":").append(entry.getValue()).append(", ");
                    }
                    enchantString = " [ " + enchantStringBuilder.substring(0, enchantStringBuilder.length() - 2) + " ]";
                }
            }
            Logger.info(Translatable.get("graves.log.item",
                    Placeholder.parsed("item", item.getType().toString()),
                    Placeholder.parsed("amount", item.getAmount() + ""),
                    Placeholder.parsed("enchantments", enchantString)));
        }
    }

    public static ArrayList<Material> getAirTypes() {
        ArrayList<Material> airTypes = new ArrayList<>();
        airTypes.add(Material.AIR);
        airTypes.add(Material.CAVE_AIR);
        airTypes.add(Material.VOID_AIR);
        airTypes.add(Material.SHORT_GRASS);
        airTypes.add(Material.TALL_GRASS);
        airTypes.add(Material.SEAGRASS);
        airTypes.add(Material.POPPY);
        airTypes.add(Material.DANDELION);
        airTypes.add(Material.BLUE_ORCHID);
        airTypes.add(Material.ALLIUM);
        airTypes.add(Material.AZURE_BLUET);
        airTypes.add(Material.RED_TULIP);
        airTypes.add(Material.ORANGE_TULIP);
        airTypes.add(Material.WHITE_TULIP);
        airTypes.add(Material.PINK_TULIP);
        airTypes.add(Material.OXEYE_DAISY);
        airTypes.add(Material.CORNFLOWER);
        airTypes.add(Material.LILY_OF_THE_VALLEY);
        airTypes.add(Material.WITHER_ROSE);
        airTypes.add(Material.SUNFLOWER);
        airTypes.add(Material.LILAC);
        airTypes.add(Material.ROSE_BUSH);
        airTypes.add(Material.PEONY);
        airTypes.add(Material.VINE);
        airTypes.add(Material.WARPED_FUNGUS);
        airTypes.add(Material.CRIMSON_FUNGUS);
        airTypes.add(Material.RED_MUSHROOM);
        airTypes.add(Material.BROWN_MUSHROOM);
        airTypes.add(Material.WARPED_ROOTS);
        airTypes.add(Material.NETHER_SPROUTS);
        airTypes.add(Material.CRIMSON_ROOTS);
        airTypes.add(Material.SNOW);
        airTypes.add(Material.LADDER);
        airTypes.add(Material.BAMBOO_SAPLING);
        airTypes.add(Material.BAMBOO);
        airTypes.add(Material.GLOW_LICHEN);
        airTypes.add(Material.FERN);
        airTypes.add(Material.LARGE_FERN);
        airTypes.add(Material.TORCH);
        airTypes.add(Material.COBWEB);
        airTypes.add(Material.SEA_PICKLE);
        if (config.getBoolean("allow-water-graves")) {
            airTypes.add(Material.WATER);
            airTypes.add(Material.BUBBLE_COLUMN);
        }
        return airTypes;
    }

    public static Location getTopBlock(Location location, int max) {
        Location loc = location.clone();
        loc.setY(max);
        while (loc.getY() > loc.getWorld().getMinHeight()) {
            if (!getAirTypes().contains(loc.getBlock().getType())) {
                return loc;
            }
            loc.setY(loc.getY() - 1);
        }
        return null;
    }

    public static Location getNextAir(Location location) {
        Location loc = location.clone();
        while (loc.getY() <= loc.getWorld().getMaxHeight()) {
            if (getAirTypes().contains(loc.getBlock().getType())) {
                return loc;
            }
            loc.setY(loc.getY() + 1);
        }

        return null;
    }


    public static String serializeItems(Player player) throws IOException {
        ArrayList<ItemStack> items = new ArrayList<>(Arrays.asList(player.getInventory().getContents()));

        ArrayList<ItemStack> items2 = new ArrayList<>();

        ArrayList<ItemStack> toRemove = new ArrayList<>();

        for (ItemStack item : items) {
            if (item == null) continue;
            if (!item.getType().equals(Material.ELYTRA)) continue;

            if (!item.hasItemMeta()) continue;
            if (!item.getItemMeta().getPersistentDataContainer().has(Key.get("armored_elytra"), PersistentDataType.STRING)) continue;

            items2.add(ArmoredElytras.getB64ChestplateFromArmoredElytra(item));
            items2.add(ArmoredElytras.getB64ElytraFromArmoredElytra(item));

            toRemove.add(item);
        }

        items.addAll(items2);

        for (ItemStack item : items) {
            if (item == null) continue;

            if (!item.hasItemMeta()) continue;
            ItemMeta meta = item.getItemMeta();
            if (meta == null) continue;
            if (meta.getEnchantLevel(Enchantment.VANISHING_CURSE) > 0) {
                toRemove.add(item);
            }
        }

        items.removeAll(toRemove);

        if (items.isEmpty()) return "";
        StringBuilder serialized = new StringBuilder();
        for (ItemStack item : items) {
            if (item == null) continue;
            String newSerItem = Base64Serializer.itemStackArrayToBase64(new ItemStack[]{item});
            serialized.append(newSerItem);
            serialized.append(" :%-=-%: ");
        }
        if (serialized.length() > " :%-=-%: ".length()) {
            return removeLastChars(serialized.toString(), " :%-=-%: ".length());
        }
        return serialized.toString();
    }

    public static ArrayList<ItemStack> deserializeItems(String serialized) throws IOException {
        ArrayList<ItemStack> items = new ArrayList<>();
        if (serialized.length() < 1) return items;
        for (String s : serialized.split(" :%-=-%: ", -1)) {
            items.add(Base64Serializer.itemStackArrayFromBase64(s)[0]);
        }
        return items;
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }
}
