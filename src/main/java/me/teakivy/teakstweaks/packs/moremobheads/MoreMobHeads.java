package me.teakivy.teakstweaks.packs.moremobheads;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.moremobheads.types.HeadDataLoader;
import me.teakivy.teakstweaks.packs.moremobheads.types.HeadEntry;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MoreMobHeads extends BasePack {
    private static HashMap<String, HeadEntry> headData;

    public MoreMobHeads() {
        super("more-mob-heads", Material.ZOMBIE_HEAD);
    }

    @Override
    public void init() {
        super.init();
        headData = new HashMap<>();
        List<HeadEntry> heads = HeadDataLoader.loadHeadData();
        for (HeadEntry head : heads) {
            headData.put(head.key(), head);
        }

        HeadRegister.registerAll();
    }

    public static HashMap<String, HeadEntry> getHeadData() {
        return headData;
    }

    public static HeadEntry getHead(String key) {
        return headData.get(key);
    }

    public static boolean shouldDrop(Player player, String key) {
        if (Config.isDevMode()) return true;
        HeadEntry entry = getHead(key);
        if (entry == null) {
            throw new IllegalArgumentException("No head found for key: " + key);
        }
        double chance = entry.chance();
        double lootingBonus = entry.looting_bonus();
        Random rand = new Random();
        if (player == null) return false;
        if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
            for (int i = 0; i < player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.LOOTING); i++) {
                chance += lootingBonus;
            }
        } else {
            if (player.getInventory().getItemInOffHand().getItemMeta() != null) {
                for (int i = 0; i < player.getInventory().getItemInOffHand().getItemMeta().getEnchantLevel(Enchantment.LOOTING); i++) {
                    chance += lootingBonus;
                }
            }
        }
        if (chance > 1) chance = 1;
        double num = rand.nextDouble();
        return num < chance;
    }

    public static URL getUrlFromBase64(String base64) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(base64));
        return new URL(decoded.substring("{\"textures\":{\"SKIN\":{\"url\":\"".length(), decoded.length() - "\"}}}".length()));
    }
}