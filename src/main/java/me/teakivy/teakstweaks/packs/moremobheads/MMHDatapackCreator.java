package me.teakivy.teakstweaks.packs.moremobheads;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.log.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

public class MMHDatapackCreator {
    private static final LinkedHashMap<String, String> advancements = new LinkedHashMap<>();

    public static void createDataPack() {
        File dataPackFolder = getDataPackFolder();
        // Check if the data pack already exists, if it does, delete it
        File dataPack = new File(dataPackFolder, "MoreMobHeads");
        if (dataPack.exists()) {
            try {
                FileUtils.deleteDirectory(dataPack);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!Config.getPackConfig("more-mob-heads").getBoolean("advancements.enabled")) return;
        dataPack.mkdir();

        // Create the data pack files
        try {
            Files.copy(TeaksTweaks.getInstance().getResource("datapacks/MoreMobHeads/pack.mcmeta"), new File(dataPack, "pack.mcmeta").toPath());
            new File(dataPack, "data/teakstweaks/advancement/moremobheads").mkdirs();
            Files.copy(TeaksTweaks.getInstance().getResource("datapacks/MoreMobHeads/data/teakstweaks/advancement/moremobheads/root.json"), new File(dataPack, "data/teakstweaks/advancement/moremobheads/root.json").toPath());

            for (Map.Entry<String, String> entry : advancements.entrySet()) {
                File advancement = new File(dataPack, "data/teakstweaks/advancement/moremobheads/" + entry.getKey() + ".json");
                advancement.createNewFile();
                FileWriter writer = new FileWriter(advancement);
                writer.write(entry.getValue());
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static File getDataPackFolder() {
        File dataPackFolder = new File(Bukkit.getWorlds().getFirst().getWorldFolder(), "datapacks");
        if (!dataPackFolder.exists()) {
            dataPackFolder.mkdir();
        }
        return dataPackFolder;
    }

    public static void addBaseAdvancement(String key, String name, String texture) {
        InputStream file = TeaksTweaks.getInstance().getResource("datapacks/MoreMobHeads/data/teakstweaks/advancement/moremobheads/baseHead.json");
        if (file == null) return;

        String result;
        try {
            result = IOUtils.toString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return;
        }
        if (result == null) return;

        result = result.replace("<name>", name.replace(" Head", ""));
        result = result.replace("<texture>", texture);
        result = result.replace("<description>", "Collect a " + name);
        result = result.replace("<announce_in_chat>", Config.getBoolean("packs.more-mob-heads.advancements.announce-in-chat") ? "true" : "false");

        if (key.equalsIgnoreCase("illusioner_head")) return;
        if (key.equalsIgnoreCase("killer_rabbit_head")) return;
        advancements.put(key, result);
    }

    public static void addNormalAdvancement(String key, String name, Material item) {
        InputStream file = TeaksTweaks.getInstance().getResource("datapacks/MoreMobHeads/data/teakstweaks/advancement/moremobheads/normalHead.json");
        if (file == null) return;

        String result;
        try {
            result = IOUtils.toString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return;
        }
        if (result == null) return;

        result = result.replace("<name>", name.replace(" Head", ""));
        result = result.replace("<item>", item.name().toLowerCase());
        result = result.replace("<description>", "Collect a " + name);
        result = result.replace("<announce_in_chat>", Config.getBoolean("packs.more-mob-heads.advancements.announce-in-chat") ? "true" : "false");

        advancements.put(key, result);
    }
}
