package me.teakivy.teakstweaks.Packs.Utilities.CustomVillagerShops;

import me.teakivy.teakstweaks.Packs.BasePack;
import me.teakivy.teakstweaks.Utils.MessageHandler;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class CustomVillager extends BasePack {

    public CustomVillager() {
        super("Custom Villager Shops", "custom-villager-shops");
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!event.isSneaking()) return;
        if (player.getGameMode() != GameMode.CREATIVE) return;
        if (getConfig().getBoolean("require-op") && !player.isOp()) return;

        Entity nearest = getNearestVillager(player);
        if (nearest == null) return;
        Block blockUnder = nearest.getLocation().add(0, -.6, 0).getBlock();
        if (!(nearest instanceof Villager)) return;
        if (!(blockUnder.getState() instanceof Chest)) return;
        Villager villager = (Villager) nearest;
        Chest chest = (Chest) blockUnder.getState();
        if (villager.getVillagerExperience() > 0 && villager.getScoreboardTags().contains("custom_villager")) return;

        List<MerchantRecipe> recipes = getInventoryRecipes(chest.getBlockInventory());
        if (villager.getRecipes() != recipes) {

            villager.setProfession(Villager.Profession.NITWIT);
            villager.setVillagerType(Villager.Type.PLAINS);
            villager.setVillagerLevel(5);
            villager.setAI(false);
            villager.addScoreboardTag("custom_villager");

            villager.setRecipes(recipes);
            player.sendMessage(MessageHandler.getMessage("pack.custom-villager-shops.trade-init"));
            for (int i = 0; i < 8; i++) {
                playHappyParticle(villager.getLocation());
            }
        }
    }

    private static Entity getNearestVillager(Player player) {
        List<Entity> nearby = player.getNearbyEntities(3, 3, 3);
        for (Entity entity : nearby) {
            if (entity.getType() == EntityType.VILLAGER) return entity;
        }
        return null;
    }

    private static List<MerchantRecipe> getInventoryRecipes(Inventory inv) {
        List<MerchantRecipe> recipes = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            MerchantRecipe recipe = getRecipeFromInventory(inv, i);
            if (recipe == null) continue;
            recipes.add(recipe);
        }
        return recipes;
    }

    private static MerchantRecipe getRecipeFromInventory(Inventory inv, int col) {
        ItemStack item1 = inv.getItem(col - 1);
        ItemStack item2 = inv.getItem(col + 9 - 1);
        ItemStack result = inv.getItem(col + 18 - 1);
        if (item2 != null && item1 == null) {
            item1 = item2;
            item2 = null;
        }
        if (item1 == null || result == null) return null;
        MerchantRecipe recipe = new MerchantRecipe(result, Integer.MAX_VALUE);
        recipe.addIngredient(item1);
        if (item2 != null) recipe.addIngredient(item2);
        return recipe;
    }

    private static void playHappyParticle(Location loc) {
        if (loc.getWorld() == null) return;
        loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc.add(0, 1, 0), 1, .5, .5, .5, .1);
    }

}
