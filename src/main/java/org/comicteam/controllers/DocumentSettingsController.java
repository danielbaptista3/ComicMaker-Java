package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.comicteam.CMFile;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;

import java.util.Arrays;

public class DocumentSettingsController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField serieField;
    @FXML
    private TextArea authorsArea;
    @FXML
    private TextArea descriptionArea;

    public void initialize() {
        nameField.setText(CMFile.cmfile.book.getName());
        serieField.setText(CMFile.cmfile.book.getSerie());

        StringBuilder builder = new StringBuilder();
        for (String author : CMFile.cmfile.book.getAuthors()) {
            builder.append(author).append("\n");
        }
        authorsArea.setText(builder.toString());

        descriptionArea.setText(CMFile.cmfile.book.getDescription());
    }

    @FXML
    public void nameFieldKeyReleased() {
        if (!nameField.getText().isEmpty()) {
            CMFile.cmfile.book.setName(nameField.getText());
            CMFile.cmfile.saved = false;
        }

        FXMLHelper.setNameFieldBorder(nameField);
    }

    @FXML
    public void serieFieldKeyReleased() {
        CMFile.cmfile.book.setSerie(serieField.getText());
        CMFile.cmfile.saved = false;
    }

    @FXML
    public void authorsAreaKeyReleased() {
        CMFile.cmfile.book.setAuthors(Arrays.asList(authorsArea.getText().split("\n")));
        CMFile.cmfile.saved = false;
    }

    @FXML
    public void descriptionAreaKeyReleased() {
        CMFile.cmfile.book.setDescription(descriptionArea.getText());
        CMFile.cmfile.saved = false;
    }
}
