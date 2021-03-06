package org.comicteam.plugins;

public abstract class Plugin implements Pluginable {
    private String name;
    private String version;
    private String description;

    public Plugin(String name, String version, String description) {
        this.name = name;
        this.version = version;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public abstract void action();

    @Override
    public String toString() {
        return name;
    }
}