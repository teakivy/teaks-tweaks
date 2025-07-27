package me.teakivy.teakstweaks.packs.moremobheads.abstractions;

import me.teakivy.teakstweaks.TeaksTweaks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public abstract class SpecialMobHead implements Listener {

    public SpecialMobHead() {
        register();
    }

    @EventHandler
    protected abstract void playerKillEvent(EntityDeathEvent event);

    protected void register() {
        TeaksTweaks.getInstance().getServer().getPluginManager().registerEvents(this, TeaksTweaks.getInstance());
    }
}
