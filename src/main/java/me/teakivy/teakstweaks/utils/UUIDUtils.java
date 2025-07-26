package me.teakivy.teakstweaks.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UUIDUtils {
    private static final HashMap<String, UUID> uuidCache = new HashMap<>();

    public static CompletableFuture<UUID> getUUIDAsync(String name) {
        // Check cache first
        if (uuidCache.containsKey(name)) {
            return CompletableFuture.completedFuture(uuidCache.get(name));
        }

        // Run async task
        return CompletableFuture.supplyAsync(() -> {
            String uuid = "";
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream()));
                uuid = (((JsonObject)new JsonParser().parse(in)).get("id")).toString().replaceAll("\"", "");
                uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
                in.close();
            } catch (Exception e) {
                uuid = "er";
            }
            UUID Uuid = UUID.fromString(uuid);
            uuidCache.put(name, Uuid);
            return Uuid;
        });
    }

    public static String getPlayerTexture(UUID uuid) {
        String urlString = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString();
        StringBuilder result = new StringBuilder();

        try {
            // Create a URL object
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            // Parse the JSON response using json-simple
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(result.toString());
            JSONArray propertiesArray = (JSONArray) jsonResponse.get("properties");
            JSONObject firstProperty = (JSONObject) propertiesArray.get(0); // fix for first element

            // Extract the value
            return (String) firstProperty.get("value");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static CompletableFuture<ItemStack> getPlayerHead(String name) {
        return getUUIDAsync(name).thenCompose(uuid -> getPlayerHead(uuid, name));
    }

    public static CompletableFuture<ItemStack> getPlayerHead(UUID uuid, String name) {
        return CompletableFuture.supplyAsync(() -> {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            PlayerProfile profile = Bukkit.createPlayerProfile(uuid, name);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            PlayerTextures textures = profile.getTextures();

            try {
                textures.setSkin(getURLFromTexture(getPlayerTexture(uuid)));
            } catch (MalformedURLException var8) {
                var8.printStackTrace();
            }

            meta.setOwnerProfile(profile);
            meta.setNoteBlockSound(Registry.SOUNDS.getKey(Sound.ENTITY_PLAYER_HURT));
            head.setItemMeta(meta);
            return head;
        });
    }

    private static URL getURLFromTexture(String texture) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(texture));
        String skinString = decoded.split("\"SKIN\" : ")[1];
        return new URL(skinString.split("\"url\" : \"")[1].split("\"")[0]);
    }

}
