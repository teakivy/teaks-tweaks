package me.teakivy.teakstweaks.Utils;

import me.teakivy.teakstweaks.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Credits {

    Main main = Main.getPlugin(Main.class);

    public void createCredits() throws IOException {
        File credits = new File(main.getDataFolder(), "credits.txt");
        if (credits.createNewFile()) {
            FileWriter writer = new FileWriter(credits);
            writer.write("Plugin Creator:\nhttps://youtube.com/teakivy\n\nVanilla Tweaks Credits:\nhttps://vanillatweaks.net/");
            writer.close();
        }
    }
}
