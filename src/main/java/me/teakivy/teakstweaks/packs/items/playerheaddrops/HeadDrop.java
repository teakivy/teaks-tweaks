package me.teakivy.teakstweaks.packs.items.playerheaddrops;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class HeadDrop extends BasePack {

    public HeadDrop() {
        super("Player Head Drops", "player-head-drops", PackType.ITEMS, Material.PLAYER_HEAD, "A player will drop their head when killed by another player. The item displays who the killer is.");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!hasPermission(event.getPlayer())) return;

        Player player = event.getEntity();
        if (player.isDead()) {
            if (player.getKiller() != null) {
                if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getPlayerChance())) return;
                Player killer = player.getKiller();
                event.getDrops().add(getHead(player, killer.getName()));
            }
        }
    }

    public ItemStack getHead(Player player, String killer) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setNoteBlockSound(Sound.ENTITY_PLAYER_HURT.getKey());

        ArrayList<String> lore = new ArrayList<>();
        if (getConfig().getBoolean("display-killer"))
            lore.add("Killed by " + killer);
        skull.setLore(lore);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);
        return item;
    }
}
