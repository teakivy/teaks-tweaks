package me.teakivy.teakstweaks.Utils;

import me.teakivy.teakstweaks.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Credits {

    Main main = Main.getPlugin(Main.class);

    public void createCredits() throws IOException {
        File credits = new File(main.getDataFolder(), "credits.txt");
        FileWriter writer = new FileWriter(credits);
        writer.write("Plugin Creator: TeakIvy\n" +
                        " - https://twitter.com/TeakIvyYT\n" +
                        " - https://youtube.com/teakivy\n" +
                        "\n" +
                        "Contributors:\n" +
                        " - evm0\n" +
                        "\n" +
                        "Thanks to Sachin Gorkar for some of the tweak's ideas\n" +
                        "\n" +
                        "Fast Leaf Decay  By: @StarTux on Github\n" +
                        "\n" +
                        "Vanilla Tweaks Credits:\n" +
                        "https://vanillatweaks.net/\n"
                        );
        writer.close();
    }
}
