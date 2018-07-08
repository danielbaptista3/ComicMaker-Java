package org.comicteam.helpers;

import org.comicteam.annotations.LanguageProcessor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginHelper {
    private static final String DIRECTORY_NAME = String.format("%s/.comicmaker/plugins", System.getProperty("user.home"));

    private static boolean pluginsDirectoryExists() {
        return Files.exists(Paths.get(DIRECTORY_NAME));
    }

    private static void createPluginsDirectory() throws IOException {
        if (!pluginsDirectoryExists()) {
            Files.createDirectory(Paths.get(DIRECTORY_NAME));
        }
    }

    private static List<File> getInstalledJars() {
        try {
            createPluginsDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File plugins = new File(DIRECTORY_NAME);

        List<File> jars = new ArrayList<>();

        for (File f : plugins.listFiles()) {
            if (f.getName().endsWith(".jar")) {
                jars.add(f);
            }
        }

        return jars;
    }

    public static void loadInstalledPlugins() {
        for (File jar : getInstalledJars()) {
            URLClassLoader loader = null;

            try {
                URL url = jar.toURI().toURL();
                loader = new URLClassLoader(new URL[] {url});
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                JarFile plugin = new JarFile(jar);

                Enumeration<JarEntry> es = plugin.entries();

                while (es.hasMoreElements()) {
                    String name = es.nextElement().getName();

                    if (name.endsWith(".class")) {
                        name = name.replace("/", ".");
                        name = name.substring(0, name.length() - 6);
                        try {
                            LanguageProcessor.language(Class.forName(name, true, loader));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}