package me.teakivy.teakstweaks.packs.wanderingtrades;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.UUIDUtils;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trades extends BasePack {

    public Trades() {
        super("wandering-trades", PackType.HERMITCRAFT, Material.LEAD);
    }

    @EventHandler
    public void traderSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.WANDERING_TRADER) {
            WanderingTrader trader = (WanderingTrader) event.getEntity();
            List<MerchantRecipe> recipes = new ArrayList<>();

            if (getConfig().getBoolean("player-heads.has-player-heads")) {
                recipes.addAll(getHeadTrades());
            }
            recipes.addAll(MiniBlocks.getBlockTrades());

            recipes.addAll(trader.getRecipes());
            trader.setRecipes(recipes);
        }
    }

    private List<MerchantRecipe> getHeadTrades() {
        List<MerchantRecipe> trades = new ArrayList<>();
        List<String> players = new ArrayList<>(getConfig().getStringList("player-heads.players"));

        if (getConfig().getBoolean("player-heads.read-from-whitelist")) {
            players.clear();
            for (OfflinePlayer pl : Bukkit.getWhitelistedPlayers()) {
                players.add(pl.getName());
            }
        }

        if (Config.isDevMode()) {
            for (String player : players) {
                MerchantRecipe recipe = newHeadRecipe(player);
                if (recipe != null) trades.add(recipe);
            }
        } else {
            int amount = getConfig().getInt("player-heads.amount-of-trades");
            List<String> headNames = new ArrayList<>();

            Random rand = new Random();
            int attempts = amount + 25;
            while (amount > 0) {
                attempts--;
                if (attempts <= 0) break;

                String name = players.get(rand.nextInt(players.size()));

                if (!headNames.contains(name)) {
                    headNames.add(name);
                    amount--;

                    MerchantRecipe recipe = newHeadRecipe(name);
                    if (recipe != null) trades.add(recipe);
                }
            }
        }
        return trades;

    }

    private MerchantRecipe newHeadRecipe(String playerName) {
        try {
            MerchantRecipe recipe = new MerchantRecipe(UUIDUtils.getPlayerHead(playerName), getConfig().getInt("player-heads.per-trade"));

            recipe.addIngredient(new ItemStack(Material.EMERALD, 1));

            return recipe;
        } catch (Exception e) {
            return null;
        }
    }

}
