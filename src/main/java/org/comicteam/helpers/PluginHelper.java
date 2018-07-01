package org.comicteam.helpers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PluginHelper {
    public List<File> getInstalledPlugins() {
        Path path = Paths.get("~/.comicmaker/plugins");

        path.getFileSystem().
    }
}