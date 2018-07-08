package org.comicteam.plugins.languages;

import org.comicteam.plugins.Pluginable;

public interface Languable extends Pluginable {
    String getTranslation(String name) throws NoSuchFieldException, IllegalAccessException;
}
