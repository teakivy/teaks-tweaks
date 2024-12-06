package me.teakivy.teakstweaks.utils.update;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VersionManager {
    private static final List<Version> versions = new ArrayList<>();

    public static void init() {
        versions.clear();
        String url = "https://api.modrinth.com/v2/project/Xdn5t532/version";
        try {
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray versions = (JSONArray) JSONValue.parseWithException(nameJson);
            for (Object version : versions) {
                JSONObject obj = (JSONObject) version;
                Version v = parseVersion(obj);
                VersionManager.versions.add(v);
            }
        } catch (IOException | ParseException ignored) {}
    }

    private static Version parseVersion(JSONObject obj) {
        String version = (String) obj.get("version_number");
        String id = (String) obj.get("id");
        List<String> supportedMCVersions = new ArrayList<>();
        JSONArray supportedVersions = (JSONArray) obj.get("game_versions");
        for (Object supportedVersion : supportedVersions) {
            supportedMCVersions.add((String) supportedVersion);
        }
        return new Version(version, supportedMCVersions, id);
    }

    public static List<Version> getVersions() {
        return versions;
    }

    public static Version getBestVersion() {
        List<Version> supportedVersions = new ArrayList<>();
        for (Version version : versions) {
            if (version.isSupported()) supportedVersions.add(version);
        }

        if (supportedVersions.isEmpty()) return null;

        Version bestVersion = supportedVersions.getFirst();
        for (Version version : supportedVersions) {
            if (!bestVersion.isNewerThan(version)) bestVersion = version;
        }

        return bestVersion;
    }
}
