package me.teakivy.teakstweaks.Packs.Survival.DurabilityPing;

import me.teakivy.teakstweaks.Packs.BasePack;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class DuraPing extends BasePack {

    public DuraPing() {
        super("Durability Ping", "durability-ping");
    }

    private final HashMap<UUID, Long> pingCooldown = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getScoreboardTags().contains("dp_customized")) return;
        if (getConfig().getBoolean("default-settings.ping-for-hand-items")) {
            player.addScoreboardTag("dp_ping_for_hand_items");
        } else {
            player.removeScoreboardTag("dp_ping_for_hand_items");
        }
        if (getConfig().getBoolean("default-settings.ping-for-armor-items")) {
            player.addScoreboardTag("dp_ping_for_armor_items");
        } else {
            player.removeScoreboardTag("dp_ping_for_armor_items");
        }
        if (getConfig().getBoolean("default-settings.ping-with-sound")) {
            player.addScoreboardTag("dp_ping_with_sound");
        } else {
            player.removeScoreboardTag("dp_ping_with_sound");
        }

        if (player.getScoreboardTags().contains("dp_display_hidden") || player.getScoreboardTags().contains("dp_display_subtitle") || player.getScoreboardTags().contains("dp_display_title") || player.getScoreboardTags().contains("dp_display_chat") || player.getScoreboardTags().contains("dp_display_actionbar")) return;
        if (Objects.requireNonNull(getConfig().getString("default-settings.display")).equalsIgnoreCase("hidden")) {
            player.addScoreboardTag("dp_display_hidden");
        }
        if (Objects.requireNonNull(getConfig().getString("default-settings.display")).equalsIgnoreCase("subtitle")) {
            player.addScoreboardTag("dp_display_subtitle");
        }
        if (Objects.requireNonNull(getConfig().getString("default-settings.display")).equalsIgnoreCase("title")) {
            player.addScoreboardTag("dp_display_title");
        }
        if (Objects.requireNonNull(getConfig().getString("default-settings.display")).equalsIgnoreCase("chat")) {
            player.addScoreboardTag("dp_display_chat");
        }
        if (Objects.requireNonNull(getConfig().getString("default-settings.display")).equalsIgnoreCase("actionbar")) {
            player.addScoreboardTag("dp_display_actionbar");
        }
    }

    @EventHandler
    public void onItemUse(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        float durability = (event.getItem().getType().getMaxDurability() - (event.getItem().getDurability() + 1));
        float maxDurability = event.getItem().getType().getMaxDurability();
        int percentDamaged = (int) ((durability / maxDurability) * 100);

        if (getConfig().getInt("ping-at-percent") >= percentDamaged) {
            if (pingCooldown.containsKey(player.getUniqueId())) {
                if (pingCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) return;
            }
            if (player.getScoreboardTags().contains("dp_ping_for_hand_items") && player.getScoreboardTags().contains("dp_ping_for_armor_items")) {
                pingPlayer(player, item, durability);
            } else if (isArmor(item)) {
                if (player.getScoreboardTags().contains("dp_ping_for_armor_items")) {
                    pingPlayer(player, item, durability);
                }
            } else {
                if (player.getScoreboardTags().contains("dp_ping_for_hand_items")) {
                    pingPlayer(player, item, durability);
                }
            }
            pingCooldown.put(player.getUniqueId(), System.currentTimeMillis() + (main.getConfig().getInt("packs.durability-ping.ping-cooldown") * 1000L));
        }
    }

    public static void pingPlayer(Player player, ItemStack item, float durability, String style, boolean playSound) {
        float maxDurability = item.getType().getMaxDurability();
        if (durability == 0) return;
        if (getSetting(player, "ping_with_sound").equalsIgnoreCase("true")) {
            if (playSound) player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 2);
        }
        if (style.equalsIgnoreCase("hidden")) return;
        if (style.equalsIgnoreCase("subtitle")) {
            player.sendTitle(getDurabilityMessage("ping.subtitle.title", item, durability, maxDurability), getDurabilityMessage("ping.subtitle.subtitle", item, durability, maxDurability));
        }
        if (style.equalsIgnoreCase("title")) {
            player.sendTitle(getDurabilityMessage("ping.title.title", item, durability, maxDurability), getDurabilityMessage("ping.title.subtitle", item, durability, maxDurability));
        }
        if (style.equalsIgnoreCase("chat")) {
            player.sendMessage(getDurabilityMessage("ping.chat.message", item, durability, maxDurability));
        }
        if (style.equalsIgnoreCase("actionbar")) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(getDurabilityMessage("ping.actionbar.message", item, durability, maxDurability)).create());
        }
    }

    private static String getDurabilityMessage(String path, ItemStack item, float durability, float maxDurability) {
        String str = MessageHandler.getMessage("pack.durability-ping." + path);
        str = str.replace("%item_type%", getItemName(item));
        str = str.replace("%item_durability%", String.valueOf((int) Math.ceil(durability)));
        str = str.replace("%item_max_durability%", String.valueOf((int) maxDurability));
        return str;
    }

    public static void pingPlayer(Player player, ItemStack item, float durability) {
        pingPlayer(player, item, durability, getSetting(player, "display"), true);
    }


    private boolean isArmor(ItemStack item) {
        Material type = item.getType();

        if (type == Material.LEATHER_HELMET) return true;
        if (type == Material.LEATHER_CHESTPLATE) return true;
        if (type == Material.LEATHER_LEGGINGS) return true;
        if (type == Material.LEATHER_BOOTS) return true;

        if (type == Material.CHAINMAIL_HELMET) return true;
        if (type == Material.CHAINMAIL_CHESTPLATE) return true;
        if (type == Material.CHAINMAIL_LEGGINGS) return true;
        if (type == Material.CHAINMAIL_BOOTS) return true;

        if (type == Material.IRON_HELMET) return true;
        if (type == Material.IRON_CHESTPLATE) return true;
        if (type == Material.IRON_LEGGINGS) return true;
        if (type == Material.IRON_BOOTS) return true;

        if (type == Material.GOLDEN_HELMET) return true;
        if (type == Material.GOLDEN_CHESTPLATE) return true;
        if (type == Material.GOLDEN_LEGGINGS) return true;
        if (type == Material.GOLDEN_BOOTS) return true;

        if (type == Material.DIAMOND_HELMET) return true;
        if (type == Material.DIAMOND_CHESTPLATE) return true;
        if (type == Material.DIAMOND_LEGGINGS) return true;
        if (type == Material.DIAMOND_BOOTS) return true;

        if (type == Material.NETHERITE_HELMET) return true;
        if (type == Material.NETHERITE_CHESTPLATE) return true;
        if (type == Material.NETHERITE_LEGGINGS) return true;
        if (type == Material.NETHERITE_BOOTS) return true;

        if (type == Material.ELYTRA) return true;
        if (type == Material.TURTLE_HELMET) return true;
        return false;
    }

    public static String getSetting(Player player, String setting) {

        if (setting.equalsIgnoreCase("customized")) {
            if (player.getScoreboardTags().contains("dp_customized")) return "true";
            if (!player.getScoreboardTags().contains("dp_customized")) return "false";
        }
        if (setting.equalsIgnoreCase("ping_for_hand_items")) {
            if (player.getScoreboardTags().contains("dp_ping_for_hand_items")) return "true";
            if (!player.getScoreboardTags().contains("dp_ping_for_hand_items")) return "false";
        }
        if (setting.equalsIgnoreCase("ping_for_armor_items")) {
            if (player.getScoreboardTags().contains("dp_ping_for_armor_items")) return "true";
            if (!player.getScoreboardTags().contains("dp_ping_for_armor_items")) return "false";
        }
        if (setting.equalsIgnoreCase("ping_with_sound")) {
            if (player.getScoreboardTags().contains("dp_ping_with_sound")) return "true";
            if (!player.getScoreboardTags().contains("dp_ping_with_sound")) return "false";
        }

        if (setting.equalsIgnoreCase("display")) {
            if (player.getScoreboardTags().contains("dp_display_subtitle")) return "subtitle";
            if (player.getScoreboardTags().contains("dp_display_title")) return "title";
            if (player.getScoreboardTags().contains("dp_display_chat")) return "chat";
            if (player.getScoreboardTags().contains("dp_display_actionbar")) return "actionbar";
            return "hidden";
        }
        return null;
    }


    public static String getItemName(ItemStack itemStack) {
        return WordUtils.capitalize(itemStack.getType().toString().replace("_", " ").toLowerCase(Locale.ROOT));
    }

}
