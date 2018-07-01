package org.comicteam.helpers;

import org.comicteam.CMFile;
import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.models.ComicModel;

import java.io.*;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ComicBookHelper {
    //public static ComicBook openedBook;
    //public static boolean saved = true;
    //public static int currentPage = 0;

    /*public static void open(String fileName) {
        try {
            CMFile cm = new CMFile(fileName);
        } catch (InvalidDescriptorException e) {
            e.printStackTrace();
        } catch (DescriptorNotFoundException e) {
            e.printStackTrace();
        }
    }*/

/*    public static ComicBook open(String fileName) {
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
            saved = true;
            return JSONtoJava.javaBook(o);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    /*public static void saveProject() {
        File descriptor = saveDescriptor();

        if (descriptor != null) {
            HashMap<String, ByteArrayOutputStream> canvass = saveAllCanvas();

            if (canvass != null) {
                if (putAllFilesOnZip(descriptor, canvass)) {
                    saved = true;
                }
            }
        }
    }*/

    /*public static File saveDescriptor() {
        File descriptor = null;

        try {
            descriptor = File.createTempFile("descriptor", "xml");

            try (FileWriter fw = new FileWriter(descriptor)) {
                fw.write(JSONifyHelper.jsonBook(openedBook).toJSONString());
                saved = true;
                return descriptor;
            } catch (IOException e) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return descriptor;
    }*/

    /*public static HashMap<String, ByteArrayOutputStream> saveAllCanvas() {
        HashMap<String, ByteArrayOutputStream> canvass = new HashMap<>();

        for (ComicPage page : openedBook.getPages()) {
            for (ComicPanel panel : page.getPanels()) {
                for (ComicModel model : panel.getModels()) {
                    ByteArrayOutputStream canvas = CanvasHelper.writeCanvasFile(model.getCanvas());

                    if (canvas == null) {
                        return null;
                    }

                    canvass.put(model.getCanvas().toString(), canvas);
                }
            }
        }

        return canvass;
    }*/

    /*public static boolean putAllFilesOnZip(File descriptor, HashMap<String, ByteArrayOutputStream> canvass) {
        try {
            File file = new File(String.format("%s/%s.cm", SettingsHelper.get("savePath"), openedBook.getName()));
            ZipOutputStream input = new ZipOutputStream(new FileOutputStream(file));

            ZipEntry entry = new ZipEntry("descriptor.xml");
            input.putNextEntry(entry);

            input.write(new FileInputStream(descriptor).readAllBytes());

            for (String canvasName : canvass.keySet()) {
                input.putNextEntry(new ZipEntry(canvasName));
                input.write(canvass.get(canvasName).toByteArray());
            }

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }*/
}