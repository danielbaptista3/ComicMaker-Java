package org.comicteam.annotations;

import org.comicteam.helpers.LanguageHelper;

public class LanguageProcessor {
    public static void language(Class c) {
        if (c.isAnnotationPresent(Language.class)) {
            LanguageHelper.addLanguage(c);
        }
    }
}