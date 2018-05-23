package org.comicteam.helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class SettingsHelper {
    private static final String DIRECTORY_NAME = String.format("%s/.comicmaker", System.getProperty("user.home"));
    private static final String FILE_NAME = String.format("%s/comicmaker.conf", DIRECTORY_NAME);

    private static File settingsFile;

    private static HashMap<String, String> parameters;

    static {
        settingsFile = new File(FILE_NAME);

        if (!comicMakerDirectoryExists()) {
            try {
                createComicMakerDirectory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!comicMakerSettingsFileExists()) {
            try {
                createComicMakerSettingsFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (get("language") == null) {
            try {
                set("language", "Français");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (get("savePath") == null) {
            try {
                set("savePath", "./");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createComicMakerDirectory() throws IOException {
        Files.createDirectory(Paths.get(DIRECTORY_NAME));
    }

    private static boolean comicMakerDirectoryExists() {
        return Files.exists(Paths.get(DIRECTORY_NAME));
    }

    private static void createComicMakerSettingsFile() throws IOException {
        Files.createFile(Paths.get(FILE_NAME));
    }

    private static boolean comicMakerSettingsFileExists() {
        return Files.exists(Paths.get(FILE_NAME));
    }

    private static void initParameters() {
        parameters = new HashMap<>();
        String[] line;

        try (Scanner scanner = new Scanner(settingsFile)) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().split(" = ");

                parameters.put(line[0], line[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeParameters() throws IOException {
        try (FileWriter writer = new FileWriter(settingsFile)) {
            for (String param : parameters.keySet()) {
                writer.write(String.format("%s = %s\n", param, parameters.get(param)));
            }
        }
    }

    public static void set(String parameter, String value) throws IOException {
        initParameters();

        parameters.put(parameter, value);

        writeParameters();
    }

    public static String get(String parameter) {
        initParameters();

        return parameters.get(parameter);
    }
}