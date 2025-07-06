package me.teakivy.teakstweaks.packs.tpa;

import me.teakivy.teakstweaks.packs.back.Back;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class TPARequest {
    private final Player sender;
    private final Player target;
    private final long time;
    private final TPAType type;
    private boolean accepted = false;
    public TPARequest(Player sender, Player target, TPAType type) {
        this.sender = sender;
        this.target = target;
        this.time = System.currentTimeMillis();
        this.type = type;
    }

    public Player getFrom() {
        return switch (type) {
            case TPA -> sender;
            case TPAHERE -> target;
        };
    }

    public Player getTo() {
        return switch (type) {
            case TPA -> target;
            case TPAHERE -> sender;
        };
    }

    public Player getSender() {
        return sender;
    }

    public Player getTarget() {
        return target;
    }

    public TPAType getType() {
        return type;
    }

    public long getTime() {
        return time;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - time > 60 * 1000L;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void accept() {
        accepted = true;
        Back.backLoc.put(getFrom().getUniqueId(), getTo().getLocation());
        getFrom().teleportAsync(getTo().getLocation());

        Component toMessage = MiniMessage.miniMessage().deserialize(Translatable.getString("tpa.teleporting_to_you"), Placeholder.parsed("player", getFrom().getName()));
        getTo().sendMessage(toMessage);

        Component fromMessage = MiniMessage.miniMessage().deserialize(Translatable.getString("tpa.teleporting"), Placeholder.parsed("player", getTo().getName()));
        getFrom().sendMessage(fromMessage);
    }

    public enum TPAType {
        TPA("tpa"),
        TPAHERE("tpahere");

        private final String key;

        TPAType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}

