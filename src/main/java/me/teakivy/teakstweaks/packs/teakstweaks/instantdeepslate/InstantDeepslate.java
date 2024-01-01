package me.teakivy.teakstweaks.packs.teakstweaks.instantdeepslate;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
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

public class InstantDeepslate extends BasePack {
    private final ArrayList<Material> instantMaterials = new ArrayList<>();

    public InstantDeepslate() {
        super("instant-deepslate", PackType.TEAKSTWEAKS, Material.DEEPSLATE);
    }

    @Override
    public void init() {
        super.init();
        instantMaterials.add(Material.DEEPSLATE);
        instantMaterials.add(Material.DEEPSLATE_BRICKS);
        instantMaterials.add(Material.CRACKED_DEEPSLATE_BRICKS);
        instantMaterials.add(Material.DEEPSLATE_TILES);
        instantMaterials.add(Material.CRACKED_DEEPSLATE_TILES);
        instantMaterials.add(Material.POLISHED_DEEPSLATE);
        instantMaterials.add(Material.CHISELED_DEEPSLATE);
    }

    @EventHandler
    public void onBeginBreak(BlockDamageEvent e) {
        if (!checkPermission(e.getPlayer())) return;
        Player player = e.getPlayer();
        ItemStack item = e.getItemInHand();
        Block block = e.getBlock();
        if (!instantMaterials.contains(block.getType())) return;

        if (item.getType().equals(Material.NETHERITE_PICKAXE) && hasHasteTwo(player) && isEfficiencyFive(item)) {
            e.setInstaBreak(true);
            player.playSound(block.getLocation(), block.getBlockData().getSoundGroup().getBreakSound(), 1f, 1f);
        }
    }

    private boolean hasHasteTwo(Player player) {
        PotionEffect potion = player.getPotionEffect(PotionEffectType.FAST_DIGGING);
        if (potion == null) return false;
        return potion.getAmplifier() >= 1;
    }

    private boolean isEfficiencyFive(ItemStack item) {
        return item.getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED) >= 5;
    }
}
