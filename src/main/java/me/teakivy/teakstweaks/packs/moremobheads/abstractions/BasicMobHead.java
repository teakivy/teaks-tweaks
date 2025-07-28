package me.teakivy.teakstweaks.packs.moremobheads.abstractions;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.moremobheads.types.HeadEntry;
import me.teakivy.teakstweaks.packs.moremobheads.types.TexturedHead;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

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
        MoreMobHeads.grant(killer, this.key);
    }

    protected ItemStack getHead() {
        return MoreMobHeads.getHeadItem(this.key, this.sound);
    }

    protected void register() {
        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }
}
