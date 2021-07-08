package me.teakivy.vanillatweaks.Utils.UpdateChecker;

import me.teakivy.vanillatweaks.Main;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private Main main = Main.getPlugin(Main.class);
    private int resourceId;

    public UpdateChecker(Main plugin, int resourceId) {
        this.main = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.main, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                this.main.getLogger().info("Cannot look for updates: " + exception.getMessage());
            }
        });
    }

}
