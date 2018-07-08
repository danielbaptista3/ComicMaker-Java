package org.comicteam.plugins.languages;

import org.comicteam.plugins.Plugin;

public abstract class Language extends Plugin implements Languable {
    public Language(String name, String version, String description) {
        super(name, version, description);
    }

    public abstract String getTranslation(String name) throws NoSuchFieldException, IllegalAccessException;
}