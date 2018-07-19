package org.comicteam.helpers;

import org.comicteam.annotations.Language;
import org.comicteam.plugins.Plugin;
import org.comicteam.plugins.languages.French;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginHelper {
    private static final String DIRECTORY_NAME = String.format("%s/.comicmaker", System.getProperty("user.home"));
    private static final String PLUGINS_NAME = String.format("%s/plugins", DIRECTORY_NAME);
    public static List<Class<?>> plugins;
    public static List<Class<?>> languages;

    private static boolean directoryExists() {
        return Files.exists(Paths.get(DIRECTORY_NAME));
    }

    private static boolean pluginsDirectoryExists() {
        return Files.exists(Paths.get(PLUGINS_NAME));
    }

    private static void createPluginsDirectory() throws IOException {
        if (!directoryExists()) {
            Files.createDirectory(Paths.get(DIRECTORY_NAME));
            if (!pluginsDirectoryExists()) {
                Files.createDirectory(Paths.get(PLUGINS_NAME));
            }
        }
    }

    private static void loadFrench() {
        try {
            SettingsHelper.set("language", "French");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void hasDefaultLanguage() {
        if (languages.size() < 0) {
            loadFrench();
        } else {
            String language = SettingsHelper.get("language");

            for (Class c : languages) {
                if (c.getSimpleName().equals(language)) {
                    return;
                }
            }

            loadFrench();
        }
    }

    private static List<File> getInstalledJars() {
        try {
            createPluginsDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File plugins = new File(PLUGINS_NAME);

        List<File> jars = new ArrayList<>();

        for (File f : plugins.listFiles()) {
            if (f.getName().endsWith(".jar")) {
                jars.add(f);
            }
        }

        return jars;
    }

    private static void setInstalledPluginsOnHashMap() {
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
                            Class c = Class.forName(name, true, loader);
                            if (c.isAnnotationPresent(Language.class)) {
                                languages.add(c);
                            } else {
                                plugins.add(c);
                            }
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

    public static void loadInstalledPlugins() {
        plugins = new ArrayList<>();
        languages = new ArrayList<>();
        LanguageHelper.clearLanguages();

        setInstalledPluginsOnHashMap();

        LanguageHelper.addLanguage(French.class);
        hasDefaultLanguage();

        for (Class c : languages) {
            LanguageHelper.addLanguage(c);
        }

        for (Class c : plugins) {
            try {
                Plugin p = (Plugin) c.newInstance();
                p.action();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addPlugin(Path path) {
        try {
            Files.copy(path, new FileOutputStream(PLUGINS_NAME + "/" + path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlugin(Plugin p) {
        for (Class c : plugins) {
            if (c.getSimpleName().equals(p.getName())) {
                plugins.remove(c);
                return;
            }
        }

        for (Class c : languages) {
            if (c.getSimpleName().equals(p.getName())) {
                languages.remove(c);
            }
        }

        try {
            Files.delete(Paths.get(PLUGINS_NAME + "/" + p.getName() + ".jar"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadInstalledPlugins();
    }
}