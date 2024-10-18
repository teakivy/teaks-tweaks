package me.teakivy.teakstweaks.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

import static me.teakivy.teakstweaks.packs.moremobheads.BaseMobHead.getUrlFromBase64;

public class UUIDUtils {

    public static UUID getUUID(String name) {
        String uuid = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream()));
            uuid = (((JsonObject)new JsonParser().parse(in)).get("id")).toString().replaceAll("\"", "");
            uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
            in.close();
        } catch (Exception e) {
            uuid = "er";
        }
        return UUID.fromString(uuid);
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
            JSONObject firstProperty = (JSONObject) propertiesArray.getFirst();

            // Extract the value
            return (String) firstProperty.get("value");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ItemStack getPlayerHead(String name) {
        return getPlayerHead(getUUID(name), name);
    }

    public static ItemStack getPlayerHead(UUID uuid, String name) {
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
        meta.setNoteBlockSound(Sound.ENTITY_PLAYER_HURT.getKey());
        head.setItemMeta(meta);
        return head;
    }

    private static URL getURLFromTexture(String texture) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(texture));
        String skinString = decoded.split("\"SKIN\" : ")[1];
        return new URL(skinString.split("\"url\" : \"")[1].split("\"")[0]);
    }

}
