package org.comicteam.helpers;

import java.util.ArrayList;
import java.util.List;

public class LanguageHelper {
    private static List<String> languages;

    static {
        languages = new ArrayList<>();

        languages.add("Français");
        languages.add("Anglais");
        languages.add("Russe");
        languages.add("Japonais");
    }

    public static List<String> getLanguagesAvailables() {
        return languages;
    }
}