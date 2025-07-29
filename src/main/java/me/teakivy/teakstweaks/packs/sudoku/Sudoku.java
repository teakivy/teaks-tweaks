package me.teakivy.teakstweaks.packs.sudoku;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Sudoku extends BasePack {

    public Sudoku() {
        super(TTPack.SUDOKU, Material.GOLDEN_SWORD);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (!event.getEntity().getScoreboardTags().contains("sudoku-message")) return;
        event.deathMessage(getText("death_message", insert("player", event.getEntity().getName())));
        event.getEntity().getScoreboardTags().remove("sudoku-message");
    }
}
