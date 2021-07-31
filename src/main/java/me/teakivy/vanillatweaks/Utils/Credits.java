package me.teakivy.vanillatweaks.Utils;

import me.teakivy.vanillatweaks.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Credits {

    Main main = Main.getPlugin(Main.class);

    public void createCredits() throws IOException {
        File credits = new File(main.getDataFolder(), "credits.txt");
        if (credits.createNewFile()) {
            FileWriter writer = new FileWriter(credits);
            writer.write("Credits:\nVanilla Tweaks: https://vanillatweaks.net/");
            writer.close();
        }
    }

}
