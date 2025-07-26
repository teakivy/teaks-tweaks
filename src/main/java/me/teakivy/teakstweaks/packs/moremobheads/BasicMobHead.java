package me.teakivy.teakstweaks.packs.moremobheads;

import com.destroystokyo.paper.profile.PlayerProfile;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.types.HeadEntry;
import me.teakivy.teakstweaks.packs.moremobheads.types.TexturedHead;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.util.UUID;

public class BasicMobHead implements Listener {
    private final EntityType type;
    private final String key;
    private final String name;
    private final String texture;
    private final Sound sound;

    public BasicMobHead(String key, EntityType type, Sound sound) {
        this.key = key;
        this.type = type;
        this.sound = sound;

        HeadEntry entry = MoreMobHeads.getHead(key);
        if (entry == null) {
            throw new IllegalArgumentException("Head entry for key '" + key + "' not found.");
        }
        if (!(entry instanceof TexturedHead head)) {
            throw new IllegalArgumentException("Head entry for key '" + key + "' is not a TexturedHead.");
        }
        this.name = head.name();
        this.texture = head.texture();

        register();
    }

    @EventHandler
    public void playerKillEvent(EntityDeathEvent event) {
        if (event.getEntityType() != this.type) return;
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        if (!MoreMobHeads.shouldDrop(killer, this.key)) return;
        ItemStack head = getHead();
        if (head == null) return;
        event.getDrops().add(head);
    }

    protected ItemStack getHead() {
        ItemStack head = ItemStack.of(Material.PLAYER_HEAD);
        PlayerProfile profile = Bukkit.createProfileExact(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), "MobHead");
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        Component name = MiniMessage.miniMessage().deserialize("<yellow>" + this.name.replace(" Head", "'s Head")).decoration(TextDecoration.ITALIC, false);
        meta.displayName(name);
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(MoreMobHeads.getUrlFromBase64(this.texture));
        } catch (MalformedURLException ignored) {
            ignored.printStackTrace();
        }

        profile.setTextures(textures);
        meta.setPlayerProfile(profile);
        meta.setNoteBlockSound(Registry.SOUNDS.getKey(this.sound));
        head.setItemMeta(meta);
        return head;
    }

    protected void register() {
        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }
}
