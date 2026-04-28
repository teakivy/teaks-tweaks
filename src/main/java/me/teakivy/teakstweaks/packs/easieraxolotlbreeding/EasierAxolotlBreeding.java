package me.teakivy.teakstweaks.packs.easieraxolotlbreeding;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class EasierAxolotlBreeding extends BasePack {

    public EasierAxolotlBreeding() {
        super(TTPack.EASIER_AXOLOTL_BREEDING, Material.TROPICAL_FISH);
    }

    @EventHandler
    public void onBreed(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (!(entity instanceof Axolotl axolotl)) return;
        if (!axolotl.isAdult()) return;
        if (!axolotl.canBreed()) return;
        if (axolotl.isLoveMode()) return;

        Player player = event.getPlayer();
        Material materialInHand = player.getInventory().getItemInMainHand().getType();

        if (materialInHand == Material.TROPICAL_FISH_BUCKET && !getConfig().getBoolean("allow-buckets")) {
            event.setCancelled(true);
            return;
        }
        if (materialInHand != Material.TROPICAL_FISH) return;

        axolotl.setLoveModeTicks(600);
        axolotl.getWorld().spawnParticle(Particle.HEART, axolotl.getLocation().add(0, 1, 0), 7, 0.3, 0.1, 0.3);


    }
}
