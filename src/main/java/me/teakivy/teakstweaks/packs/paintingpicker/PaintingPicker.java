package me.teakivy.teakstweaks.packs.paintingpicker;

import com.google.gson.internal.LinkedTreeMap;
import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.ItemSerializer;
import me.teakivy.teakstweaks.utils.JsonManager;
import me.teakivy.teakstweaks.utils.Key;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PaintingPicker extends BasePack {

    static HashMap<String, ItemStack> paintings = new HashMap<>();

    public PaintingPicker() {
        super("painting-picker", PackType.TEAKSTWEAKS, Material.PAINTING);
    }

    public static void load() {
        JsonManager.saveToFile(
                JsonManager.updateJson(
                        JsonManager.getFromFile("data/paintings.json"),
                        JsonManager.getFromResource("data/paintings.json"),
                        true),
                "data/paintings.json");

        File file = new File(TeaksTweaks.getInstance().getDataFolder() + "/data/paintings.json");
        if (!file.exists()) TeaksTweaks.getInstance().saveResource("data/paintings.json", false);

        file = new File(TeaksTweaks.getInstance().getDataFolder() + "/data/paintings.json");

        if (!file.exists()) paintings = new HashMap<>();

        try {
            LinkedHashMap<String, Object> map =  TeaksTweaks.getGson().fromJson(new FileReader(file), LinkedHashMap.class);
            for (Object painting : ((List<Object>) map.get("paintings"))) {
                LinkedTreeMap<String, Object> paintings_map = (LinkedTreeMap<String, Object>) painting;

                String name = (String) paintings_map.get("name");
                String base64 = (String) paintings_map.get("base64");

                paintings.put(name, ItemSerializer.fromByteArray(Base64.getDecoder().decode(base64)));
            }
            return;
        } catch (FileNotFoundException ignored) {}

        paintings = new HashMap<>();
    }

    @Override
    public void init() {
        super.init();
        load();

        StonecuttingRecipe recipe = new StonecuttingRecipe(Key.get("painting_any"), new ItemStack(Material.PAINTING), Material.PAINTING);
        addRecipe(recipe);

        for (Map.Entry<String, ItemStack> entry : paintings.entrySet()) {
            String name = entry.getKey();
            ItemStack item = entry.getValue();

            recipe = new StonecuttingRecipe(Key.get("painting_" + name.toLowerCase().replaceAll(" ", "_")), item, Material.PAINTING);
            addRecipe(recipe);
        }
    }
}
