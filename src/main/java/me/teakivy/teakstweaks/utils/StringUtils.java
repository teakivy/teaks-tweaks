package me.teakivy.teakstweaks.utils;

import net.kyori.adventure.text.Component;

import java.util.HashMap;

public final class StringUtils {

    public static String toTitleCase(String input) {
        if (input == null || input.isBlank()) {
            return input;
        }

        StringBuilder result = new StringBuilder(input.length());
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(
                        capitalizeNext
                                ? Character.toUpperCase(c)
                                : Character.toLowerCase(c)
                );
                capitalizeNext = false;
            } else {
                result.append(c);

                // Start a new word after spaces, hyphens, apostrophes, etc.
                capitalizeNext = Character.isWhitespace(c)
                        || c == '-'
                        || c == '\'';
            }
        }

        return result.toString();
    }

    public static Component parseLegacyChatColors(Component legacy) {
        HashMap<Character, String> colorMap = new HashMap<>();
        // colors
        colorMap.put('a', "green");
        colorMap.put('b', "aqua");
        colorMap.put('c', "red");
        colorMap.put('d', "light_purple");
        colorMap.put('e', "yellow");
        colorMap.put('f', "white");
        colorMap.put('0', "black");
        colorMap.put('1', "dark_blue");
        colorMap.put('2', "dark_green");
        colorMap.put('3', "dark_aqua");
        colorMap.put('4', "dark_red");
        colorMap.put('5', "dark_purple");
        colorMap.put('6', "gold");
        colorMap.put('7', "gray");
        colorMap.put('8', "dark_gray");
        colorMap.put('9', "blue");
        // effects
        colorMap.put('k', "obfuscated");
        colorMap.put('l', "bold");
        colorMap.put('m', "strikethrough");
        colorMap.put('n', "underlined");
        colorMap.put('o', "italic");
        colorMap.put('r', "reset");

        for (char key : colorMap.keySet()) {
            String replacement = "<" + colorMap.get(key) + ">";
            legacy = legacy.replaceText(b -> b.matchLiteral("&" + key).replacement(replacement));
        }

        return legacy;
    }
}