package me.teakivy.teakstweaks.utils.update;

import me.teakivy.teakstweaks.TeaksTweaks;
import me.teakivy.teakstweaks.utils.log.Logger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.translation.Argument;

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
            Logger.info(Component.translatable("startup.update.version_available", Argument.string("version", latestVersion.getVersion())));
            Logger.info(Component.translatable("startup.update.download", Argument.string("url", latestVersion.getUrl())));
        }
    }
}


