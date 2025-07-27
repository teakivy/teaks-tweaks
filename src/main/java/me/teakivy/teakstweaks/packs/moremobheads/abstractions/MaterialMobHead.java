package me.teakivy.teakstweaks.packs.moremobheads.abstractions;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MaterialMobHead implements Listener {
    private final EntityType type;
    private final String key;
    Material material;

    public MaterialMobHead(String key, EntityType type, Material material) {
        this.key = key;
        this.type = type;
        this.material = material;

        register();
    }

    @EventHandler
    public void playerKillEvent(EntityDeathEvent event) {
        System.out.println("MaterialMobHead: " + event.getEntityType() + " " + this.type);
        if (event.getEntityType() != this.type) return;
        System.out.println("MaterialMobHead: " + event.getEntityType() + " " + this.type + " - matched");
        Player killer = event.getEntity().getKiller();
        if (killer == null) return;
        System.out.println("MaterialMobHead: " + event.getEntityType() + " " + this.type + " - killer not null");
        if (!MoreMobHeads.shouldDrop(killer, this.key)) return;
        System.out.println("MaterialMobHead: " + event.getEntityType() + " " + this.type + " - should drop");
        event.getDrops().add(ItemStack.of(this.material));
    }

    protected void register() {
        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }
}
