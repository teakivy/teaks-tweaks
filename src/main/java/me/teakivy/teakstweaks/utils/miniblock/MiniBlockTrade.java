package me.teakivy.teakstweaks.utils.miniblock;

import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.customitems.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.util.UUID;

import static me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads.getUrlFromBase64;

public class MiniBlockTrade {
    private final String name;
    private final String texture;
    private final Material material;

    public MiniBlockTrade(String name, String texture, Material material) {
        this.name = name;
        this.texture = texture;
        this.material = material;

        ItemStack head = getSkull(1);
        CustomItem customItem = new CustomItem(name.toLowerCase().replaceAll(" ", "_") + "_mini_block", head);
        customItem.register();
    }

    public MerchantRecipe getMerchantTrade() {
        MerchantRecipe recipe = new MerchantRecipe(getSkull(Config.getInt("packs.wandering-trades.mini-blocks.per-trade")), Config.getInt("packs.wandering-trades.mini-blocks.amount-of-trades"));

        recipe.addIngredient(new ItemStack(Material.EMERALD, 1));
        recipe.addIngredient(new ItemStack(material));

        return recipe;
    }

    public StonecuttingRecipe getStonecuttingRecipe() {
        String n = name.toLowerCase().replaceAll(" ", "_");
        return new StonecuttingRecipe(Key.get("sc_mini_blocks_" + n), getSkull(Config.getInt("packs.mini-blocks.per-cut")), material);
    }

    public ItemStack getSkull(int amount) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, amount);
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), "MiniBlock");
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + name + " Mini Block");
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(getUrlFromBase64(texture));
        } catch (MalformedURLException var8) {
            var8.printStackTrace();
        }

        meta.setOwnerProfile(profile);
        head.setItemMeta(meta);
        return head;
    }
}
