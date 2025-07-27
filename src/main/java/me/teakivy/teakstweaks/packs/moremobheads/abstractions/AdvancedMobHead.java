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
