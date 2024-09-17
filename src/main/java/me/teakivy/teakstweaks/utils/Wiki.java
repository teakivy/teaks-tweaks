package me.teakivy.teakstweaks.utils;

public class Wiki {
    private static final String wikiUrl = "https://github.com/teakivy/teaks-tweaks/wiki";
    public static String getWiki() {
        return wikiUrl;
    }

    public static String getPackWiki(String pack) {
        return wikiUrl + "/Pack:-" + pack.replaceAll(" ", "-");
    }

    public static String getCraftingTweakWiki(String tweak) {
        return wikiUrl + "/Crafting-Tweak:-" + tweak.replaceAll(" ", "-");
    }

    public static String getCommandWiki(String command) {
        return wikiUrl + "/Command:-" + command.replaceAll(" ", "-");
    }

    public static String getWikiPage(String page) {
        return wikiUrl + "/" + page.replaceAll(" ", "-");
    }
}
