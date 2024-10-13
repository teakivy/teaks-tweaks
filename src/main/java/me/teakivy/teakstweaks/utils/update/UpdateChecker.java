package me.teakivy.teakstweaks.utils.update;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.log.Logger;
import me.teakivy.teakstweaks.utils.config.Config;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class UpdateChecker {
    private final static String resourceId = "Xdn5t532";

    /**
     * Gets the latest version of the plugin from SpigotMC
     * @return The latest version of the plugin
     */
    public static String getLatestVersion() {
        if (Config.getBoolean("settings.disable-update-checker")) return null;
        String url = "https://api.modrinth.com/v2/project/" + resourceId + "/version";

        try {
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray latest = (JSONArray) JSONValue.parseWithException(nameJson);
            return ((JSONObject) latest.get(0)).get("version_number").toString();
        } catch (IOException | ParseException ignored) {}
        return null;
    }

    /**
     * Checks if there is an update available
     * @return If there is an update available
     */
    public static boolean hasUpdate() {
        String latestVersion = getLatestVersion();
        if (latestVersion == null) return false;
        return !latestVersion.equals(TeaksTweaks.getInstance().getDescription().getVersion());
    }

    /**
     * Sends a message to the console if there is an update available
     */
    public static void sendUpdateMessage() {
        if (hasUpdate()) {
            Logger.info(Translatable.get("startup.update.version_available", Placeholder.parsed("version", getLatestVersion())));
            Logger.info(Translatable.get("startup.update.download", Placeholder.parsed("url", Translatable.getString("plugin.url"))));
        }
    }
}


