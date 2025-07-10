package me.teakivy.teakstweaks.packs.wanderingtrades;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.UUIDUtils;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Trades extends BasePack {

    public Trades() {
        super("wandering-trades", Material.LEAD);
    }

    @EventHandler
    public void traderSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.WANDERING_TRADER) {
            Location location = event.getLocation().clone();
            if (Config.isPackEnabled("wandering-trader-announcements")) {
                runAnnouncement(location);
            }

            if (location.getWorld() == null) return;
            List<MerchantRecipe> recipes = new ArrayList<>();

            if (getConfig().getBoolean("player-heads.has-player-heads")) {
                // Head Trade CompletableFuture
                getHeadTrades().thenAccept((recipes1) -> {
                    // Required to run on main thread
                    Bukkit.getScheduler().runTask(TeaksTweaks.getInstance(), () -> {
                        WanderingTrader trader = (WanderingTrader) event.getEntity();
                        recipes.addAll(recipes1);
                        recipes.addAll(MiniBlocks.getBlockTrades());
                        recipes.addAll(trader.getRecipes());
                        trader.setRecipes(recipes);
                    });
                });
                return;
            }
            WanderingTrader trader = (WanderingTrader) event.getEntity();
            recipes.addAll(MiniBlocks.getBlockTrades());

            recipes.addAll(trader.getRecipes());
            trader.setRecipes(recipes);
        }
    }

    private void runAnnouncement(Location location) {
        ConfigurationSection config = Config.getPackConfig("wandering-trader-announcements");
        int radius = config.getInt("radius");
        if (radius < 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(Translatable.get("wandering_trader_announcements.announcement_all"));
            }
            return;
        }

        location.getWorld().getNearbyEntities(location, radius, radius, radius).forEach(entity -> {
            if (entity.getType() == EntityType.PLAYER) {
                entity.sendMessage(Translatable.get("wandering_trader_announcements.announcement"));
            }
        });
    }

    private CompletableFuture<List<MerchantRecipe>> getHeadTrades() {
        return CompletableFuture.supplyAsync(() -> {
            List<String> players = new ArrayList<>(getConfig().getStringList("player-heads.players"));

            if (getConfig().getBoolean("player-heads.read-from-whitelist")) {
                players.clear();
                for (OfflinePlayer pl : Bukkit.getWhitelistedPlayers()) {
                    players.add(pl.getName());
                }
            }

            return players;
        }).thenCompose(players -> {
            List<CompletableFuture<MerchantRecipe>> futures = new ArrayList<>();
            List<String> headNames = new ArrayList<>();
            Random rand = new Random();

            if (Config.isDevMode()) {
                // In dev mode, create a recipe for each player
                for (String player : players) {
                    futures.add(newHeadRecipe(player)
                            .exceptionally(ex -> {
                                return null;  // Return null in case of failure
                            })
                    );
                }
            } else {
                // Limit the number of trades based on config
                int amount = getConfig().getInt("player-heads.amount-of-trades");
                int attempts = amount + 25;

                while (amount > 0 && attempts > 0) {
                    attempts--;
                    String name = players.get(rand.nextInt(players.size()));

                    if (!headNames.contains(name)) {
                        headNames.add(name);
                        amount--;
                        futures.add(newHeadRecipe(name)
                                .exceptionally(ex -> {
                                    return null;  // Return null in case of failure
                                })
                        );
                    }
                }
            }

            // Wait for all futures to complete
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .completeOnTimeout(null, 30, TimeUnit.SECONDS) // Set timeout to prevent hanging
                    .thenApply(v -> {
                        // Gather results into a list of MerchantRecipe
                        List<MerchantRecipe> trades = new ArrayList<>();
                        for (CompletableFuture<MerchantRecipe> future : futures) {
                            try {
                                MerchantRecipe recipe = future.join(); // Safe because all futures have completed
                                if (recipe != null) {
                                    trades.add(recipe);
                                }
                            } catch (Exception ignored) {
                            }
                        }
                        return trades;
                    });
        }).exceptionally(ex -> {
            return new ArrayList<>(); // Return an empty list on failure
        });
    }

    private CompletableFuture<MerchantRecipe> newHeadRecipe(String playerName) {
        return UUIDUtils.getPlayerHead(playerName).thenApply(playerHead -> {
            try {
                MerchantRecipe recipe = new MerchantRecipe(playerHead, getConfig().getInt("player-heads.per-trade"));
                recipe.addIngredient(new ItemStack(Material.EMERALD, 1));
                return recipe;
            } catch (Exception e) {
                return null; // Handle errors by returning null
            }
        }).completeOnTimeout(null, 10, TimeUnit.SECONDS); // Timeout for individual recipe generation
    }



}
