package me.teakivy.teakstweaks.packs.transferablepets;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.dialog.DialogUtils;
import me.teakivy.teakstweaks.utils.register.TTPack;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.ArrayList;
import java.util.List;

public class TransferablePets extends BasePack implements Listener {

    public TransferablePets() {
        super(TTPack.TRANSFERABLE_PETS, Material.LEAD);
    }

    @EventHandler
    public void test(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player target)) return;
        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        List<LivingEntity> nearby = (List<LivingEntity>) player.getLocation().getNearbyLivingEntities(32, 32, 32);
        List<LivingEntity> pets = new ArrayList<>();
        for (LivingEntity entity : nearby) {
            if (!entity.isLeashed()) continue;
            if (entity.getLeashHolder().getUniqueId().equals(player.getUniqueId())) {
                pets.add(entity);
            }
        }

        DialogUtils.showConfirmation(player, Component.text("You’re about to share the lead and pass ownership of your animal to " + target.getName() + ". Are you sure you want to do this?"), (r, aud) -> {
            for (LivingEntity pet : pets) {
                if (!pet.isLeashed()) continue;
                pet.setLeashHolder(target);

                if (pet instanceof Tameable) {
                    ((Tameable) pet).setOwner(target);
                }

                if (pet instanceof Fox fox) {
                    if (fox.getFirstTrustedPlayer() != null && fox.getFirstTrustedPlayer().getUniqueId().equals(player.getUniqueId())) {
                        fox.setFirstTrustedPlayer(target);
                    }
                    if (fox.getSecondTrustedPlayer() != null && fox.getSecondTrustedPlayer().getUniqueId().equals(player.getUniqueId())) {
                        fox.setSecondTrustedPlayer(target);
                    }
                }
            }
        }, (r, aud) -> {

        });

    }
}
