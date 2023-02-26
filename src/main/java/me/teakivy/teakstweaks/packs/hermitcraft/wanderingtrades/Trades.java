package me.teakivy.teakstweaks.packs.hermitcraft.wanderingtrades;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trades extends BasePack {

    public Trades() {
        super("Wandering Trades", "wandering-trades", PackType.HERMITCRAFT, Material.LEAD);
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

        if (main.getConfig().getBoolean("config.dev-mode")) {
            for (String player : players) {
                trades.add(newHeadRecipe(player));
            }
        } else {
            int amount = getData().getConfig().getInt("wandering-trades.heads.amount-of-trades");
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

                    trades.add(newHeadRecipe(name));
                }
            }
        }
        return trades;

    }


    private MerchantRecipe newHeadRecipe(String playerName) {
        MerchantRecipe recipe = new MerchantRecipe(getHead(playerName), getData().getConfig().getInt("wandering-trades.heads.max-per-trade"));

        recipe.addIngredient(new ItemStack(Material.valueOf(getData().getConfig().getString("wandering-trades.heads.trade-item")), getData().getConfig().getInt("wandering-trades.heads.trade-amount")));

        return recipe;
    }


    public static ItemStack getHead(String playerName) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setOwner(playerName);
        item.setItemMeta(skull);
        return item;
    }

}
