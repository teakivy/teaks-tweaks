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

public abstract class AdvancedMobHead implements Listener {
    private final EntityType type;
    private final Sound sound;

    public AdvancedMobHead(EntityType type, Sound sound) {
        this.type = type;
        this.sound = sound;

        register();
    }

    @EventHandler
    public void playerKillEvent(EntityDeathEvent event) {
        System.out.println("Entity Death Event: " + event.getEntityType());
        if (event.getEntityType() != this.type) return;
        System.out.println("Mob Head: " + event.getEntityType() + " killed by " + event.getEntity().getKiller());
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        System.out.println("Killer: " + killer.getName());
        String key = getKey(event);
        if (!MoreMobHeads.shouldDrop(killer, key)) return;
        System.out.println("Dropping head for key: " + key);
        ItemStack head = getHead(key);
        if (head == null) return;
        System.out.println("Head created: " + head.getType());
        event.getDrops().add(head);
    }

    protected ItemStack getHead(String key) {
        HeadEntry entry = MoreMobHeads.getHead(key);
        if (entry == null) return null;
        if (!(entry instanceof TexturedHead texturedHead)) return null;
        String texture = texturedHead.texture();
        String n = texturedHead.name();

        ItemStack head = ItemStack.of(Material.PLAYER_HEAD);
        PlayerProfile profile = Bukkit.createProfileExact(UUID.fromString("fdb5599c-1b14-440e-82df-d69719703d21"), "MobHead");
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        Component name = MiniMessage.miniMessage().deserialize("<yellow>" + n).decoration(TextDecoration.ITALIC, false);
        meta.displayName(name);
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(MoreMobHeads.getUrlFromBase64(texture));
        } catch (MalformedURLException ignored) {
            ignored.printStackTrace();
        }

        profile.setTextures(textures);
        meta.setPlayerProfile(profile);
        Sound sound = getSound(key);
        if (sound != null) meta.setNoteBlockSound(Registry.SOUNDS.getKey(sound));
        head.setItemMeta(meta);
        return head;
    }

    private Sound getSound(String key) {
        return this.sound;
    }

    protected void register() {
        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }

    protected abstract String getKey(EntityDeathEvent event);
}
