package me.teakivy.teakstweaks.packs.wanderingtrades;

import com.google.gson.internal.LinkedTreeMap;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.JsonManager;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.Material;
import org.bukkit.inventory.MerchantRecipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MiniBlocks {

    static List<MiniBlockTrade> miniBlocks = new ArrayList<>();

    public static void load() {
        JsonManager.saveToFile(
                JsonManager.updateJson(
                        JsonManager.getFromFile("data/mini_blocks.json"),
                        JsonManager.getFromResource("data/mob_heads.json"),
                        true),
                "data/mob_heads.json");

        File file = new File(TeaksTweaks.getInstance().getDataFolder() + "/data/mini_blocks.json");
        if (!file.exists()) TeaksTweaks.getInstance().saveResource("data/mini_blocks.json", false);

        file = new File(TeaksTweaks.getInstance().getDataFolder() + "/data/mini_blocks.json");

        if (!file.exists()) miniBlocks = new ArrayList<>();

        try {
            LinkedHashMap<String, Object> map =  TeaksTweaks.getGson().fromJson(new FileReader(file), LinkedHashMap.class);
            for (Object mini_block : ((List<Object>) map.get("mini_blocks"))) {
                LinkedTreeMap<String, Object> mini_block_map = (LinkedTreeMap<String, Object>) mini_block;

                String name = (String) mini_block_map.get("name");
                String texture = (String) mini_block_map.get("texture");
                Material material = Material.valueOf(mini_block_map.get("id").toString().toUpperCase());

                miniBlocks.add(new MiniBlockTrade(name, texture, material));
            }
            return;
        } catch (FileNotFoundException ignored) {}

        miniBlocks = new ArrayList<>();
    }

    public static List<MerchantRecipe> getBlockTrades() {
        List<MerchantRecipe> recipes = new ArrayList<>();
        int amount = Config.getInt("packs.wandering-trades.mini-blocks.amount-of-trades");
        List<Integer> numbers = new ArrayList<>();

        if (!Config.getBoolean("packs.wandering-trades.mini-blocks.has-mini-blocks")) return recipes;

        if (Config.isDevMode()) {
            for (MiniBlockTrade miniBlock : miniBlocks) {
                recipes.add(miniBlock.getTrade());
            }

            return recipes;
        }

        for (int i = 0; i < amount; i++) {
            Random rand = new Random();
            int num = rand.nextInt(miniBlocks.size());
            if (!numbers.contains(num)) {
                recipes.add(miniBlocks.get(num).getTrade());
                numbers.add(num);
            } else {
                int num2 = rand.nextInt(miniBlocks.size());
                if (!numbers.contains(num2)) {
                    recipes.add(miniBlocks.get(num).getTrade());
                    numbers.add(num);
                }
            }
        }

        return recipes;
    }
}
