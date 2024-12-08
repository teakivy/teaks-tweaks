package me.teakivy.teakstweaks.utils.update;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class Version {
    private final String version;
    private final List<String> supportedMCVersions;
    private final String id;

    public Version(String version, List<String> supportedMCVersions, String id) {
        this.version = version;
        this.supportedMCVersions = supportedMCVersions;
        this.id = id;
    }

    public Version(String version, String id) {
        this.version = version;
        this.supportedMCVersions = new ArrayList<>();
        this.id = id;
    }

    public Version(String version) {
        this.version = version;
        this.supportedMCVersions = new ArrayList<>();
        this.id = "";
    }

    public String getVersion() {
        return version;
    }

    public List<String> getSupportedMCVersions() {
        return supportedMCVersions;
    }

    public String getUrl() {
        return "https://modrinth.com/plugin/teaks-tweaks/version/" + id;
    }

    public boolean isSupported() {
        String mcVersion = Bukkit.getBukkitVersion().split("-")[0];
        return supportedMCVersions.contains(mcVersion);
    }

    public void addSupportedMCVersion(String version) {
        supportedMCVersions.add(version);
    }

    private int getMajorVersion() {
        return Integer.parseInt(version.split("\\.")[0]);
    }

    private int getMinorVersion() {
        return Integer.parseInt(version.split("\\.")[1]);
    }

    private int getPatchVersion() {
        return Integer.parseInt(version.split("\\.")[2].split("-")[0]);
    }

    public boolean isNewerThan(Version v) {
        if (v.getVersion().equals(getVersion())) return false;

        if (getMajorVersion() != v.getMajorVersion()) {
            return getMajorVersion() > v.getMajorVersion();
        }
        if (getMinorVersion() != v.getMinorVersion()) {
            return getMinorVersion() > v.getMinorVersion();
        }
        if (getPatchVersion() != v.getPatchVersion()) {
            return getPatchVersion() > v.getPatchVersion();
        }
        return (getSupportedMCVersions().size() < v.getSupportedMCVersions().size());
    }

}
