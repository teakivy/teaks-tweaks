package me.teakivy.teakstweaks.packs.moremobheads;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.MM;
import me.teakivy.teakstweaks.utils.config.Config;
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BaseMobHead implements Listener {
    protected EntityType entity;
    protected String key;
    protected Sound sound;

    protected HashMap<String, String> textures;

    protected static HashMap<String, String> names = new HashMap<>();


    public BaseMobHead(EntityType entity, String key, Sound sound, String texture) {
        this.entity = entity;
        this.key = key;
        this.sound = sound;

        this.textures = new HashMap<>();
        if (texture != null) addHeadTexture("default", getName() + " Head", texture);


        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }

    public BaseMobHead(EntityType entity, String key, Sound sound) {
        this(entity, key, sound, null);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() != entity) return;
        if (!dropHead(event)) return;

        event.getDrops().add(getHead(event));
    }

    public boolean dropHead(EntityDeathEvent event) {
        if (Config.isDevMode()) return true;
        return shouldDrop(event.getEntity().getKiller());
    }

    public ItemStack getHead(EntityDeathEvent event) {
        return createHead(getName(event) + " Head", getTexture(event));
    }

    public ItemStack getHead() {
        return createHead(getName() + " Head", getTexture());
    }

    public String getTexture(EntityDeathEvent event) {
        return textures.get("default");
    }

    public String getTexture() {
        return textures.get("default");
    }

    public String getName(EntityDeathEvent event) {
        return WordUtils.capitalizeFully(entity.toString().toLowerCase().replace("_", " "));
    }

    public String getName() {
        return WordUtils.capitalizeFully(entity.toString().toLowerCase().replace("_", " "));
    }

    public void addHeadTexture(String key, String name, String texture) {
        textures.put(key, texture);
        names.put(texture, name);
    }

    public ItemStack createHead(String name, String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), "MobHead");
        SkullMeta meta = (SkullMeta)head.getItemMeta();
        Component c = MiniMessage.miniMessage().deserialize("<yellow>" + name.replace(" Head", "'s Head")).decoration(TextDecoration.ITALIC, false);
        meta.setDisplayName(MM.toString(c));
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(getUrlFromBase64(texture));
        } catch (MalformedURLException var8) {
            var8.printStackTrace();
        }

        meta.setOwnerProfile(profile);
        meta.setNoteBlockSound(this.sound.getKey());
        head.setItemMeta(meta);
        return head;
    }

    public static URL getUrlFromBase64(String base64) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(base64));
        return new URL(decoded.substring("{\"textures\":{\"SKIN\":{\"url\":\"".length(), decoded.length() - "\"}}}".length()));
    }

    protected boolean shouldDrop(Player player) {
        return MobHeads.shouldDrop(player, key);
    }
}
