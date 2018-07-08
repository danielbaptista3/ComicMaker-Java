package org.comicteam.helpers;

import org.apache.commons.codec.language.bm.Lang;
import org.comicteam.languages.French;
import org.comicteam.languages.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguageHelper {
    private static List<Class<?>> languages;

    static {
        languages = new ArrayList<>();
        languages.add(French.class);
    }

    public static List<Class<?>> getLanguagesAvailables() {
        return languages;
    }

    public static void addLanguage(Class c) {
        languages.add(c);
    }

    public static Class<?> getCurrentLanguage() {
        String sLanguage = SettingsHelper.get("language");

        for (Class c : languages) {
            if (c.getSimpleName().equals(sLanguage)) {
                return c;
            }
        }

        return null;
    }

    public static String getTranslation(String name) throws NoSuchFieldException, IllegalAccessException {
        return (String) getCurrentLanguage().getField(name).get(null);
    }
}