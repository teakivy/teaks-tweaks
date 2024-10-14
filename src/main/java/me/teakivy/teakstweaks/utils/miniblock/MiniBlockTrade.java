package me.teakivy.teakstweaks.utils.miniblock;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.MM;
import me.teakivy.teakstweaks.utils.config.Config;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

public class MiniBlockTrade {
    private final String name;
    private final String texture;
    private final Material material;

    public MiniBlockTrade(String name, String texture, Material material) {
        this.name = name;
        this.texture = texture;
        this.material = material;
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
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        GameProfile profile = new GameProfile(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), "MiniBlock");
        profile.getProperties().put("textures", new Property("textures", texture));
        Field field;
        try {
            field = headMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ignored) {}

        headMeta.setDisplayName(MM.toString(MiniMessage.miniMessage().deserialize("<yellow>" + this.name).decoration(TextDecoration.ITALIC, false)));

        head.setItemMeta(headMeta);
        return head;
    }
}
