package me.teakivy.teakstweaks.packs.easiermending;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class EasierMending extends BasePack {

    public EasierMending() {
        super(TTPack.EASIER_MENDING, Material.ENCHANTED_BOOK);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        if (!isAnvil(event.getClickedBlock().getType())) return;
        ItemStack item = event.getItem();
        if (item == null) return;
        ItemMeta baseMeta = item.getItemMeta();
        if (baseMeta == null) return;
        if (!(baseMeta instanceof Damageable meta)) return;
        if (!item.getEnchantments().containsKey(Enchantment.MENDING)) return;

        int durability = meta.getDamage();
        if (durability <= 0) return;
        int xp = player.calculateTotalExperiencePoints();
        if (xp > durability / 2) {
            meta.setDamage(0);
            item.setItemMeta(meta);
            player.setExperienceLevelAndProgress(xp - durability / 2);

            event.setCancelled(true);
            return;
        }

        meta.setDamage(durability - xp * 2);
        item.setItemMeta(meta);
        player.setExperienceLevelAndProgress(0);
        event.setCancelled(true);
    }

    public boolean isAnvil(Material material) {
        List<Material> anvils = List.of(Material.ANVIL, Material.CHIPPED_ANVIL, Material.DAMAGED_ANVIL);
        return anvils.contains(material);
    }
}
