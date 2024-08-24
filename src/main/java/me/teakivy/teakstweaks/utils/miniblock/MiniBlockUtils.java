package me.teakivy.teakstweaks.utils.miniblock;

import com.google.gson.internal.LinkedTreeMap;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.JsonManager;
import org.bukkit.Material;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.StonecuttingRecipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MiniBlockUtils {

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

    public static List<MiniBlockTrade> getMiniBlocks() {
        if (miniBlocks.isEmpty()) load();
        return miniBlocks;
    }

    public static List<MerchantRecipe> getAllMerchantRecipes() {
        if (miniBlocks.isEmpty()) load();
        List<MerchantRecipe> recipes = new ArrayList<>();
        for (MiniBlockTrade miniBlock : miniBlocks) {
            recipes.add(miniBlock.getMerchantTrade());
        }
        return recipes;
    }

    public static List<StonecuttingRecipe> getAllStonecuttingRecipes() {
        if (miniBlocks.isEmpty()) load();
        List<StonecuttingRecipe> recipes = new ArrayList<>();
        for (MiniBlockTrade miniBlock : miniBlocks) {
            recipes.add(miniBlock.getStonecuttingRecipe());
        }
        return recipes;
    }
}
