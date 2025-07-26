package me.teakivy.teakstweaks.packs.moremobheads.types;

import com.google.gson.*;

import java.lang.reflect.Type;

public class HeadEntryDeserializer implements JsonDeserializer<HeadEntry> {

    @Override
    public HeadEntry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        String key = obj.get("key").getAsString();
        double chance = obj.get("chance").getAsDouble();
        double lootingBonus = obj.get("looting_bonus").getAsDouble();

        if (obj.has("texture")) {
            String name = obj.get("name").getAsString();
            String texture = obj.get("texture").getAsString();
            return new TexturedHead(key, name, texture, chance, lootingBonus);
        } else if (obj.has("material")) {
            String material = obj.get("material").getAsString();
            return new MaterialHead(key, material, chance, lootingBonus);
        }

        throw new JsonParseException("Unknown HeadEntry format: " + obj);
    }
}
