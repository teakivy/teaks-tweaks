package me.teakivy.teakstweaks.Packs.Survival.Graves;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.Utils.Serializer.Base64Serializer;
import me.teakivy.teakstweaks.Utils.Serializer.ItemStackSerializer;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GraveCreator {

    public static Main main = Main.getPlugin(Main.class);

    public static Location findGraveLocation(Location location) {
        if (location.getY() < location.getWorld().getMinHeight() || location.getY() > location.getWorld().getMaxHeight()) {
            location = location.add(0, 0,  -1);
        }
        if (location.getY() > location.getWorld().getMaxHeight()) {
            for (int i = location.getWorld().getMaxHeight(); i > location.getWorld().getMinHeight(); i--) {
                Block current = location.getWorld().getBlockAt((int) location.getX(), i, (int) location.getZ());
                if (!getAirTypes().contains(current.getType())) {
                    return current.getLocation().add(0, 1, 0);
                }
            }

            if (main.getConfig().getBoolean("packs.graves.allow-void-graves")) {
                location.getWorld().getBlockAt((int) location.getX(), location.getWorld().getMinHeight(), (int) location.getZ()).setType(Material.GRASS_BLOCK);
                return location.getWorld().getBlockAt((int) location.getX(), location.getWorld().getMinHeight(), (int) location.getZ()).getLocation().add(0, 1, 0);
            }
        } else if (location.getY() < location.getWorld().getMinHeight()) {
            for (int i = location.getWorld().getMinHeight(); i < location.getWorld().getMaxHeight(); i++) {
                Block current = location.getWorld().getBlockAt((int) location.getX(), i, (int) location.getZ());
                Block above = location.getWorld().getBlockAt((int) location.getX(), i + 1, (int) location.getZ());
                if (!getAirTypes().contains(current.getType()) && getAirTypes().contains(above.getType())) {
                    return current.getLocation().add(0, 1, 0);
                }
            }

            if (main.getConfig().getBoolean("packs.graves.allow-void-graves")) {
                location.getWorld().getBlockAt((int) location.getX(), location.getWorld().getMinHeight(), (int) location.getZ()).setType(Material.GRASS_BLOCK);
                return location.getWorld().getBlockAt((int) location.getX(), location.getWorld().getMinHeight(), (int) location.getZ()).getLocation().add(0, 1, 0);
            }
        } else {
            for (int i = (int) location.getY(); i > location.getWorld().getMinHeight(); i--) {
                Block current = location.getWorld().getBlockAt((int) location.getX(), i, (int) location.getZ());
                if (!getAirTypes().contains(current.getType())) {
                    if (main.getConfig().getBoolean("packs.graves.create-above-fluids") && getFluidTypes().contains(current.getType())) {
                        for (int y = i; y < location.getWorld().getMaxHeight(); y++) {
                            current = location.getWorld().getBlockAt((int) location.getX(), y, (int) location.getZ());
                            if (!getFluidTypes().contains(current.getType())) {
                                return current.getLocation().add(0, 0, 0);
                            }
                        }
                    }
                    return current.getLocation().add(0, 1, 0);
                }
            }

            if (main.getConfig().getBoolean("packs.graves.allow-void-graves")) {
                location.getWorld().getBlockAt((int) location.getX(), location.getWorld().getMinHeight(), (int) location.getZ()).setType(Material.GRASS_BLOCK);
                return location.getWorld().getBlockAt((int) location.getX(), location.getWorld().getMinHeight(), (int) location.getZ()).getLocation().add(0, 1, 0);
            }
        }

        return null;
    }

    public static void createGrave(Location location, Player player, int xp) {
        Location loc = location.getBlock().getLocation().add(.5, 0, .5);
        ArmorStand as = (ArmorStand) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.add(0, -1.4, 0), EntityType.ARMOR_STAND);
        as.setGravity(false);
        as.setInvisible(true);
        as.setHelmet(new ItemStack(Material.STONE_BRICK_WALL));
        as.setInvulnerable(true);
        as.setCustomName(player.getName());
        as.addScoreboardTag("vt_grave");
        as.addScoreboardTag("vt_base64");
        as.setCustomNameVisible(true);

        PersistentDataContainer data = as.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_uuid");
        data.set(key, PersistentDataType.STRING, player.getUniqueId().toString());

        if (!Boolean.TRUE.equals(location.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY))) {
            NamespacedKey key2 = new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_items");
            data.set(key2, PersistentDataType.STRING, serializeItems(player));

            NamespacedKey key3 = new NamespacedKey(Main.getPlugin(Main.class), "vt_grave_owner_xp");
            data.set(key3, PersistentDataType.INTEGER, xp);
        }
    }

    public static ArrayList<Material> getAirTypes() {
        ArrayList<Material> airTypes = new ArrayList<>();
        airTypes.add(Material.AIR);
        airTypes.add(Material.CAVE_AIR);
        airTypes.add(Material.VOID_AIR);
        airTypes.add(Material.GRASS);
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
        return airTypes;
    }

    public static ArrayList<Material> getFluidTypes() {
        ArrayList<Material> fluidTypes = new ArrayList<>();
        fluidTypes.add(Material.WATER);
        fluidTypes.add(Material.LAVA);
        return fluidTypes;
    }


    public static String serializeItems(Player player) {
        ArrayList<ItemStack> items = new ArrayList<>(Arrays.asList(player.getInventory().getContents()));
        if (items.isEmpty()) return "";
        StringBuilder serialized = new StringBuilder();
        for (ItemStack item : items) {
            if (item == null) continue;
            String newSerItem = Base64Serializer.itemStackArrayToBase64(new ItemStack[]{item});
//            String serItem = ItemStackSerializer.serialize(item);
            serialized.append(newSerItem);
            serialized.append(" :%-=-%: ");
        }
        if (serialized.length() > " :%-=-%: ".length()) {
            return removeLastChars(serialized.toString(), " :%-=-%: ".length());
        }
        return serialized.toString();
    }

    public static ArrayList<ItemStack> deserializeItems(String serialized, boolean base64) throws IOException {
        ArrayList<ItemStack> items = new ArrayList<>();
        if (serialized.length() < 1) return items;
        for (String s : serialized.split(" :%-=-%: ", -1)) {
            if (!base64) {
                items.add(ItemStackSerializer.deserialize(s));
            } else {
                items.add(Base64Serializer.itemStackArrayFromBase64(s)[0]);
            }
        }
        return items;
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

}
