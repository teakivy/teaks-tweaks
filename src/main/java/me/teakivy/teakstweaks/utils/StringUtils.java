package me.teakivy.teakstweaks.utils;

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
}