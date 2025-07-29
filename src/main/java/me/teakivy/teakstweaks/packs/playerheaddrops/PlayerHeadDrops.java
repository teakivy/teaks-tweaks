package me.teakivy.teakstweaks.packs.playerheaddrops;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerHeadDrops extends BasePack {

    public PlayerHeadDrops() {
        super(TTPack.PLAYER_HEAD_DROPS, Material.PLAYER_HEAD);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        if (!player.isDead()) return;
        if (player.getKiller() == null) return;

        Player killer = player.getKiller();
        event.getDrops().add(getHead(player, killer.getName()));
    }

    public ItemStack getHead(Player player, String killer) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setNoteBlockSound(Registry.SOUNDS.getKey(Sound.ENTITY_PLAYER_HURT));

        List<Component> lore = new ArrayList<>();
        if (getConfig().getBoolean("display-killer"))
            lore.add(getText("lore", insert("player", killer)));
        skull.lore(lore);
        skull.setOwningPlayer(player);
        item.setItemMeta(skull);
        return item;
    }
}
