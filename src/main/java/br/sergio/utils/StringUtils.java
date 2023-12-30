package br.sergio.utils;

import java.util.List;
import java.util.function.Function;

public final class StringUtils {
    
    private StringUtils() {}

    public static String toTextLines(List<String> lines) {
        return String.join("\n", lines);
    }

    public static String capitalize(String text) {
        return capitalization(text, Character::toUpperCase);
    }

    public static String decapitalize(String text) {
        return capitalization(text, Character::toLowerCase);
    }
    
    private static String capitalization(String text, Function<Character, Character> charFunction) {
        if(text == null) return null;
        if(text.isBlank()) return text;
        char upper = charFunction.apply(text.charAt(0));
        return upper + text.substring(1);
    }

}
