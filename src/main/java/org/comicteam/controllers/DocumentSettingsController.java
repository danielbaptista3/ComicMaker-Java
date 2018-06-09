package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        nameField.setText(ComicBookHelper.openedBook.getName());
        serieField.setText(ComicBookHelper.openedBook.getSerie());

        StringBuilder builder = new StringBuilder();
        for (String author : ComicBookHelper.openedBook.getAuthors()) {
            builder.append(author).append("\n");
        }
        authorsArea.setText(builder.toString());

        descriptionArea.setText(ComicBookHelper.openedBook.getDescription());
    }

    @FXML
    public void nameFieldKeyReleased() {
        if (!nameField.getText().isEmpty()) {
            ComicBookHelper.openedBook.setName(nameField.getText());
            ComicBookHelper.saved = false;
        }

        FXMLHelper.setNameFieldBorder(nameField);
    }

    @FXML
    public void serieFieldKeyReleased() {
        ComicBookHelper.openedBook.setSerie(serieField.getText());
        ComicBookHelper.saved = false;
    }

    @FXML
    public void authorsAreaKeyReleased() {
        ComicBookHelper.openedBook.setAuthors(Arrays.asList(authorsArea.getText().split("\n")));
        ComicBookHelper.saved = false;
    }

    @FXML
    public void descriptionAreaKeyReleased() {
        ComicBookHelper.openedBook.setDescription(descriptionArea.getText());
        ComicBookHelper.saved = false;
    }
}
