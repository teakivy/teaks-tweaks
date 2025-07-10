package me.teakivy.teakstweaks.packs.instamine;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class InstaMine extends BasePack {

    public InstaMine() {
        super("insta-mine", Material.DEEPSLATE);
    }

    @EventHandler
    public void onBeginBreak(BlockDamageEvent e) {
        if (!Permission.INSTA_MINE.check(e.getPlayer())) return;
        Player player = e.getPlayer();
        ItemStack item = e.getItemInHand();
        Block block = e.getBlock();
        boolean isInstant = false;
        for (String blocks : getConfig().getStringList("blocks")) {
            if (blocks.equalsIgnoreCase(block.getType().name())) {
                isInstant = true;
                break;
            }
        }
        if (!isInstant) return;

        if (item.getType().equals(Material.NETHERITE_PICKAXE) && hasHasteTwo(player) && isEfficiencyFive(item)) {
            e.setInstaBreak(true);
            player.playSound(block.getLocation(), block.getBlockData().getSoundGroup().getBreakSound(), 1f, 1f);
        }
    }

    private boolean hasHasteTwo(Player player) {
        PotionEffect potion = player.getPotionEffect(PotionEffectType.HASTE);
        if (potion == null) return false;
        return potion.getAmplifier() >= 1;
    }

    private boolean isEfficiencyFive(ItemStack item) {
        return item.getItemMeta().getEnchantLevel(Enchantment.EFFICIENCY) >= 5;
    }
}
