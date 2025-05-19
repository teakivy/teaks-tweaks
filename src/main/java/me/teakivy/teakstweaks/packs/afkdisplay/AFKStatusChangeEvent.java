package me.teakivy.teakstweaks.packs.afkdisplay;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class AFKStatusChangeEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final boolean isAfk;

    public AFKStatusChangeEvent(@NotNull Player who, boolean isAfk) {
        super(who);
        this.isAfk = isAfk;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isAfk() {
        return isAfk;
    }
}
