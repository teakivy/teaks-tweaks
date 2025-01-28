package me.teakivy.teakstweaks.utils;

import me.teakivy.teakstweaks.TeaksTweaks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Credits {
    private final List<String> contributors;
    private final List<String> supporters;
    private final List<String> notes;

    public Credits() {
        this.contributors = new ArrayList<>();
        this.supporters = new ArrayList<>();
        this.notes = new ArrayList<>();

        contributors.add("evm0");
        contributors.add("RamrKorda");
        contributors.add("MCCasper");
        contributors.add("Loudbooks");
        contributors.add("ri1_");
        contributors.add("mythiccal");
        contributors.add("Vinellon");
        contributors.add("sanmc19");
        contributors.add("Capirichu");
        contributors.add("Artugr18");
        contributors.add("Gudlin");

        supporters.add("loudambiance");

        notes.add("Thanks to Sachin Gorkar for some of the tweak's ideas");
        notes.add("Fast Leaf Decay By: @StarTux on Github");
        notes.add("Vanilla Tweaks Credits: https://vanillatweaks.net/");
        notes.add("Teak's Tweaks Paste powered by PasteBook: https://paste.teakstweaks.com/");
    }

    private String generateList(String title, List<String> list) {
        StringBuilder builder = new StringBuilder();
        builder.append(title).append(":\n");
        for (String s : list) {
            builder.append(" - ").append(s).append("\n");
        }
        return builder.toString();
    }

    private String generateCredits() {
        StringBuilder builder = new StringBuilder();
        builder.append("Plugin Creator: TeakIvy\n")
                .append(" - https://twitter.com/TeakIvyYT\n")
                .append(" - https://youtube.com/teakivy\n")
                .append("\n")
                .append(generateList("Contributors", contributors))
                .append("\n")
                .append(generateList("Supporters", supporters))
                .append("\n");
        for (String note : notes) {
            builder.append(note).append("\n\n");
        }
        return builder.toString();
    }

    /**
     * Creates the credits.txt file
     * @throws IOException If the file cannot be created
     */
    public void createCredits() throws IOException {
        File credits = new File(TeaksTweaks.getInstance().getDataFolder(), "credits.txt");
        FileWriter writer = new FileWriter(credits);

        writer.write(generateCredits());
        writer.close();
    }
}
