package me.teakivy.teakstweaks.packs.moremobheads;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.moremobheads.types.HeadDataLoader;
import me.teakivy.teakstweaks.packs.moremobheads.types.HeadEntry;
import me.teakivy.teakstweaks.packs.moremobheads.types.TexturedHead;
import me.teakivy.teakstweaks.utils.advancements.AdvancementManager;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MoreMobHeads extends BasePack {
    private static HashMap<String, HeadEntry> headData;
    private static HashMap<String, AdvancementKey> advancementKeys = new HashMap<>();

    public MoreMobHeads() {
        super(TTPack.MORE_MOB_HEADS, Material.ZOMBIE_HEAD);
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
        registerAdvancements();
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

    public static ItemStack getHeadItem(String key, Sound sound) {
        HeadEntry entry = MoreMobHeads.getHead(key);
        if (entry == null) return null;
        if (!(entry instanceof TexturedHead texturedHead)) return null;
        String texture = texturedHead.texture();
        String n = texturedHead.name();

        return createhead(texture, n, sound);
    }

    public static ItemStack createhead(String texture, String name, Sound sound) {
        ItemStack head = ItemStack.of(Material.PLAYER_HEAD);
        PlayerProfile profile = Bukkit.createProfileExact(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), "MobHead");
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        Component n = MiniMessage.miniMessage().deserialize("<yellow>" + name).decoration(TextDecoration.ITALIC, false);
        meta.displayName(n);
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(MoreMobHeads.getUrlFromBase64(texture));
        } catch (MalformedURLException ignored) {
            ignored.printStackTrace();
        }

        profile.setTextures(textures);
        meta.setPlayerProfile(profile);
        if (sound != null) meta.setNoteBlockSound(Registry.SOUNDS.getKey(sound));
        head.setItemMeta(meta);
        return head;
    }

    private void registerAdvancements() {
        if (!Config.getBoolean("packs.more-mob-heads.advancements.enabled")) return;
        List<String> keys = new ArrayList<>();
        for (HeadEntry head : headData.values()) {
            if (head instanceof TexturedHead texturedHead) {
                keys.add(texturedHead.key());
            }
        }
        keys.sort(String::compareTo);
        List<AdvancementManager.MobHeadAdvancement> advancements = new ArrayList<>();
        int number = 0;
        for (String key : keys) {
            HeadEntry head = getHead(key);
            if (head instanceof TexturedHead texturedHead) {
                AdvancementManager.MobHeadAdvancement advancement = TeaksTweaks.getAdvancementManager().getMobHeadAdvancement(texturedHead, number);
                if (advancement == null) {
                    throw new IllegalArgumentException("Advancement for key '" + key + "' could not be created.");
                }
                advancements.add(advancement);
                advancementKeys.put(key, advancement.getKey());
                number++;
            }
        }

        TeaksTweaks.getAdvancementManager().registerMoreMobHeadsAdvancements(advancements);
    }

    public static void grant(Player player, String key) {
        if (!advancementKeys.containsKey(key)) {
            throw new IllegalArgumentException("No advancement key found for: " + key);
        }
        AdvancementKey advancementKey = advancementKeys.get(key);
        if (advancementKey == null) {
            throw new IllegalArgumentException("Advancement key for " + key + " is null.");
        }
        TeaksTweaks.getAdvancementManager().grant(player, advancementKey);
    }
}