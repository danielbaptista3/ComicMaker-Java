package org.comicteam.helpers;

import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExternalDocumentHelper {
    public static Image getImage(Node node) {
        File file = FXMLHelper.chooseFile(node);

        if (file == null) {
            return null;
        }

        try {
            return new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}