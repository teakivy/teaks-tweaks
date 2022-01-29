package me.teakivy.teakstweaks.Packs.Hermitcraft.WanderingTrades;

import me.teakivy.teakstweaks.Packs.BasePack;
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
        super("Wandering Trades", "wandering-trades");
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
            List<Integer> numbers = new ArrayList<>();

            for (int i = 0; i < amount; i++) {
                Random rand = new Random();
                int num = rand.nextInt(players.size());
                if (!numbers.contains(num)) {
                    trades.add(newHeadRecipe(players.get(num)));
                    numbers.add(num);
                } else {
                    int num2 = rand.nextInt(players.size());
                    if (!numbers.contains(num2)) {
                        trades.add(newHeadRecipe(players.get(num)));
                        numbers.add(num);
                    }
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
