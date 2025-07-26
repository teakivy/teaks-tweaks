package me.teakivy.teakstweaks.packs.moremobheads.types;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class HeadDataLoader {

    public static List<HeadEntry> loadHeadData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(HeadEntry.class, new HeadEntryDeserializer())
                .create();

        // Try to load the file
        InputStream stream = HeadDataLoader.class.getResourceAsStream("/data/more_mob_heads.json");

        if (stream == null) {
            throw new IllegalStateException("Could not find more_mob_heads.json in /data/");
        }

        try (InputStreamReader reader = new InputStreamReader(stream)) {
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            JsonArray headsArray = root.getAsJsonArray("heads");

            Type listType = new TypeToken<List<HeadEntry>>() {}.getType();
            return gson.fromJson(headsArray, listType);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read or parse more_mob_heads.json", e);
        }
    }
}
