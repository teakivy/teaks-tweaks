package me.teakivy.teakstweaks.utils.update;

import me.teakivy.teakstweaks.Main;
import me.teakivy.teakstweaks.utils.Logger;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class UpdateChecker {
    private final static int resourceId = 94021;

    /**
     * Gets the latest version of the plugin from SpigotMC
     * @return The latest version of the plugin
     */
    public static String getLatestVersion() {
        if (Main.getInstance().getConfig().getBoolean("settings.disable-update-checker")) return null;
        String url = "https://api.spiget.org/v2/resources/" + resourceId + "/versions/latest";

        try {
            @SuppressWarnings("deprecation")
            String nameJson = IOUtils.toString(new URL(url));
            JSONObject latest = (JSONObject) JSONValue.parseWithException(nameJson);
            return latest.get("name").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if there is an update available
     * @return If there is an update available
     */
    public static boolean hasUpdate() {
        String latestVersion = getLatestVersion();
        if (latestVersion == null) return false;
        return !latestVersion.equals(Main.getInstance().getDescription().getVersion());
    }

    /**
     * Sends a message to the console if there is an update available
     */
    public static void sendUpdateMessage() {
        if (hasUpdate()) {
            Logger.info(Translatable.get("startup.update.available").replace("%version%", getLatestVersion()));
            Logger.info(Translatable.get("startup.update.download").replace("%url%", Translatable.get("plugin.url")));
        }
    }
}


