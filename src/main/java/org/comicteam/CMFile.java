package org.comicteam;

import javafx.scene.image.Image;
import org.comicteam.exceptions.DescriptorNotFoundException;
import org.comicteam.exceptions.InvalidDescriptorException;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.JSONifyHelper;
import org.comicteam.helpers.JSONtoJava;
import org.comicteam.helpers.SettingsHelper;
import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.layouts.Size;
import org.comicteam.models.ComicModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CMFile {
    public static CMFile cmfile;
    private String descriptorContent;
    private HashMap<String, Image> images;
    public ComicBook book;
    public boolean saved = true;
    public int currentPage = 0;

    public CMFile(String name, String serie, List<String> authors, String description, Size size) {
        book = new ComicBook(name, serie, authors, description, size);
        saved = true;
    }

    public CMFile(String fileName) throws InvalidDescriptorException, DescriptorNotFoundException, IOException {
        cmfile = this;

        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        images = new HashMap<>();
        boolean descriptorFound = false;

        while (entries.hasMoreElements()) {
            ZipEntry current = entries.nextElement();

            if (current.getName().equals("descriptor.xml")) {
                setDescriptorContent(zipFile.getInputStream(current));
                descriptorFound = true;
            } else {
                addImage(current.getName(), zipFile.getInputStream(current));
            }
        }

        if (descriptorFound) {
            try {
                openComicBook();
            } catch (Exception e) {
                throw new InvalidDescriptorException();
            }
        } else {
            throw new DescriptorNotFoundException();
        }
    }

    private void openComicBook() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject o;

        try {
            o = (JSONObject) parser.parse(descriptorContent);
            saved = true;
            book = JSONtoJava.javaBook(o);
        } catch (ParseException e) {
            throw new Exception("Descriptor is invalid");
        }
    }

    private void setDescriptorContent(InputStream input) throws IOException {
        descriptorContent = new String(input.readAllBytes());
    }

    private String getDescriptorContent() {
        return descriptorContent;
    }

    private void addImage(String name, InputStream input) {
        Image image = new Image(input);

        images.put(name, image);
    }

    public Image getImage(String name) {
        return images.get(name);
    }

    public void save() {
        File descriptor = saveDescriptor();

        if (descriptor != null) {
            HashMap<String, ByteArrayOutputStream> canvass = saveAllCanvas();

            if (canvass != null) {
                if (putAllFilesOnZip(descriptor, canvass)) {
                    saved = true;
                }
            }
        }
    }

    private File saveDescriptor() {
        File descriptor = null;

        try {
            descriptor = File.createTempFile("descriptor", "xml");

            try (FileWriter fw = new FileWriter(descriptor)) {
                descriptorContent = JSONifyHelper.jsonBook(book).toJSONString();
                fw.write(descriptorContent);
                saved = true;
                return descriptor;
            } catch (IOException e) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return descriptor;
    }

    private HashMap<String, ByteArrayOutputStream> saveAllCanvas() {
        HashMap<String, ByteArrayOutputStream> canvass = new HashMap<>();

        for (ComicPage page : book.getPages()) {
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
    }

    private boolean putAllFilesOnZip(File descriptor, HashMap<String, ByteArrayOutputStream> canvass) {
        try {
            File file = new File(String.format("%s/%s.cm", SettingsHelper.get("savePath"), book.getName()));
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
    }
}
