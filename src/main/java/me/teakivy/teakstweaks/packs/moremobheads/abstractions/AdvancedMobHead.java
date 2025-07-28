package me.teakivy.teakstweaks.packs.moremobheads.abstractions;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

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
        if (event.getEntityType() != this.type) return;
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        String key = getKey(event);
        if (!MoreMobHeads.shouldDrop(killer, key)) return;
        ItemStack head = getHead(key);
        if (head == null) return;
        event.getDrops().add(head);
        MoreMobHeads.grant(killer, key);
    }

    protected ItemStack getHead(String key) {
        return MoreMobHeads.getHeadItem(key, getSound(key));
    }

    protected Sound getSound(String key) {
        return this.sound;
    }

    protected void register() {
        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }

    protected abstract String getKey(EntityDeathEvent event);
}
