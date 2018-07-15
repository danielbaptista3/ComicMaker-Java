package org.comicteam.plugins;

public interface Pluginable {
    String getName();
    String getVersion();
    String getDescription();

    void action();
}