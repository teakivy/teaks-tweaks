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

    /**
     * Checks if there is an update available
     * @return If there is an update available
     */
    public static boolean hasUpdate() {
        Version latestVersion = VersionManager.getBestVersion();
        if (latestVersion == null) return false;
        Version currentVersion = new Version(TeaksTweaks.getInstance().getDescription().getVersion());
        return latestVersion.isNewerThan(currentVersion);
    }

    /**
     * Sends a message to the console if there is an update available
     */
    public static void sendUpdateMessage() {
        if (hasUpdate()) {
            Version latestVersion = VersionManager.getBestVersion();
            if (latestVersion == null) return;
            Logger.info(Translatable.get("startup.update.version_available", Placeholder.parsed("version", latestVersion.getVersion())));
            Logger.info(Translatable.get("startup.update.download", Placeholder.parsed("url", latestVersion.getUrl())));
        }
    }
}


