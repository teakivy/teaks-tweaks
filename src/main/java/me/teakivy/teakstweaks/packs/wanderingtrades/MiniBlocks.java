package me.teakivy.teakstweaks.packs.wanderingtrades;

import com.google.gson.internal.LinkedTreeMap;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.JsonManager;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.miniblock.MiniBlockUtils;
import org.bukkit.Material;
import org.bukkit.inventory.MerchantRecipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MiniBlocks {

    public static List<MerchantRecipe> getBlockTrades() {
        List<MerchantRecipe> recipes = new ArrayList<>();
        int amount = Config.getInt("packs.wandering-trades.mini-blocks.amount-of-trades");
        List<Integer> numbers = new ArrayList<>();

        if (!Config.getBoolean("packs.wandering-trades.mini-blocks.has-mini-blocks")) return recipes;

        if (Config.isDevMode()) {
            recipes.addAll(MiniBlockUtils.getAllMerchantRecipes());

            return recipes;
        }

        for (int i = 0; i < amount; i++) {
            List<MerchantRecipe> miniBlocks = MiniBlockUtils.getAllMerchantRecipes();
            Random rand = new Random();
            int num = rand.nextInt(miniBlocks.size());
            if (!numbers.contains(num)) {
                recipes.add(miniBlocks.get(num));
                numbers.add(num);
            } else {
                int num2 = rand.nextInt(miniBlocks.size());
                if (!numbers.contains(num2)) {
                    recipes.add(miniBlocks.get(num));
                    numbers.add(num);
                }
            }
        }

        return recipes;
    }
}
