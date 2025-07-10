package me.teakivy.teakstweaks.packs.sudoku;

import me.teakivy.teakstweaks.packs.BasePack;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Sudoku extends BasePack {

    public Sudoku() {
        super("sudoku", Material.GOLDEN_SWORD);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getEntity().getScoreboardTags().contains("sudoku-message")) return;
        event.deathMessage(getText("death_message", insert("player", event.getEntity().getName())));
        event.getEntity().getScoreboardTags().remove("sudoku-message");
    }
}
