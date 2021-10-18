package me.teakivy.teakstweaks.Packs.Hermitcraft.TreasureGems;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gems implements Listener {

    final static List<String> chests = Stream.of(
            "minecraft:chests/desert_pyramid",
            "minecraft:chests/abandoned_mineshaft",
            "minecraft:chests/end_city_treasure",
            "minecraft:chests/igloo_chest",
            "minecraft:chests/jungle_temple",
            "minecraft:chests/nether_bridge",
            "minecraft:chests/simple_dungeon",
            "minecraft:chests/stronghold_corridor",
            "minecraft:chests/stronghold_crossing",
            "minecraft:chests/stronghold_library",
            "minecraft:chests/village_blacksmith",
            "minecraft:chests/woodland_mansion")
            .collect(Collectors.toList());

    @EventHandler
    public void onGenerate(LootGenerateEvent event) {
        if (chests.contains(event.getLootTable().toString())) {
            if (event.getInventoryHolder() != null) {
                genChest(event.getInventoryHolder().getInventory());
            }
        }
    }

    private void genChest(Inventory inv) {
        for (int i = 0; i < inv.getContents().length; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, randomAddGem());
            }
        }
    }


    private ItemStack randomAddGem() {
        Random rand = new Random();

        int chance = 125;

        if (rand.nextInt(chance) == 5) {
            return getRubyGem();
        }
        if (rand.nextInt(chance) == 5) {
            return getRubyGem();
        }

        if (rand.nextInt(chance) == 5) {
            return getTopazGem();
        }
        if (rand.nextInt(chance) == 5) {
            return getTopazGem();
        }

        if (rand.nextInt(chance) == 5) {
            return getSapphireGem();
        }
        if (rand.nextInt(chance) == 5) {
            return getSapphireGem();
        }

        if (rand.nextInt(chance) == 5) {
            return getAmethystGem();
        }
        if (rand.nextInt(chance) == 5) {
            return getAmethystGem();
        }

        if (rand.nextInt(chance) == 5) {
            return getAquamarineGem();
        }
        if (rand.nextInt(chance) == 5) {
            return getAquamarineGem();
        }

        return new ItemStack(Material.AIR);
    }

    public static ItemStack getRubyGem() {
        return getGem(ChatColor.RED + "Ruby Gem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjkyNGUyNzI2YTg1NDE4M2FhMTE5NWM0ZTk1NzQ4NzdiOGFlZTM1NWI1NzViNWMwYmJlMGQ0MDc1Y2ZlOThjOCJ9fX0");
    }

    public static ItemStack getSapphireGem() {
        return getGem(ChatColor.BLUE + "Sapphire Gem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWVlNDY5MzBmNDg2NTkyZGJlMzVjMjEzNDc5ODE0MDNlMTQ3MGVjMGYwODUwY2M5MzM1YTQ4OTA5ODJjOTEzMCJ9fX0");
    }

    public static ItemStack getTopazGem() {
        return getGem(ChatColor.YELLOW + "Topaz Gem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGQzYTI0MmRhM2FkYjE2MzE1Y2UyOWUxOWY3OWNjMjJmMzQwNTEwNTNhYmRhZDU2NjhlYWNhYWMxZWEwYjIyMiJ9fX0");
    }

    public static ItemStack getAmethystGem() {
        return getGem(ChatColor.DARK_PURPLE + "Amethyst Gem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJjYmZmMDZhZDJlMzg3ZDM0ZjZiOGE5YjkxYzI4MTcyMTViNzgxYWZlNjgzODk1ZjNkNmViNGUzZGE0MTgifX19");
    }

    public static ItemStack getAquamarineGem() {
        return getGem(ChatColor.AQUA + "Aquamarine Gem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE5ZTI4ZmFiN2QzZDAxMWJjODRiZjE2ZGFmMmY5NThhOGI4OGIwNmUwZDVlNTBjMjU0NDdiNWRmNGM2MGI3YyJ9fX0");
    }

    public static ItemStack getGem(String name, String texture) {
        Random rand = new Random();
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, rand.nextInt(2) + 1);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        GameProfile profile = new GameProfile(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), name);
        profile.getProperties().put("textures", new Property("textures", texture));
        Field field;
        try {
            field = headMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }

        headMeta.setDisplayName(name);

        head.setItemMeta(headMeta);
        return head;
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

}
