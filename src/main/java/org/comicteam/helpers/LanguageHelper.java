package org.comicteam.helpers;

import org.comicteam.plugins.languages.Languable;

import java.util.ArrayList;
import java.util.List;

public class LanguageHelper {
    private static List<Languable> languages;

    static {
        languages = new ArrayList<>();
    }

    public static List<Languable> getLanguagesAvailables() {
        return languages;
    }

    public static void addLanguage(Class c) {
        try {
            Languable l = (Languable) c.newInstance();
            languages.add(l);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Languable getCurrentLanguage() {
        String sLanguage = SettingsHelper.get("language");

        for (Languable l : languages) {
            if (l.getName().equals(sLanguage)) {
                return l;
            }
        }

        return null;
    }

    public static void clearLanguages() {
        languages.clear();
    }

    public static String getTranslation(String name) throws NoSuchFieldException, IllegalAccessException {
        return getCurrentLanguage().getTranslation(name);
    }
}