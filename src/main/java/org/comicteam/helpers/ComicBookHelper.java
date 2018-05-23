package org.comicteam.helpers;

import org.comicteam.ComicBook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ComicBookHelper {
    public static ComicBook openedBook;
    public static boolean saved;

    public static ComicBook open(String fileName) {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder();

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNext()) {
                builder.append(reader.nextLine());
            }

            try (FileWriter fw = new FileWriter(file)) {
                fw.write(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        JSONParser parser = new JSONParser();
        JSONObject o;

        try {
            o = (JSONObject) parser.parse(builder.toString());
            return JSONtoJava.javaBook(o);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean save(ComicBook book) {
        return save(book, SettingsHelper.get("savePath"));
    }

    public static boolean save(ComicBook book, String folder) {
        File file = new File(String.format("%s/%s.cm", folder, book.getName()));

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(JSONifyHelper.jsonBook(book).toJSONString());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isAComicBook(String fileName) {
        try {
            ComicBook book = open(fileName);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}