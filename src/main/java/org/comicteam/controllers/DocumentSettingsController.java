package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.comicteam.CMFile;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.FXMLHelper;

import java.util.Arrays;

public class DocumentSettingsController {
    @Translate
    @FXML
    public Label nameLabel;
    @Translate
    @FXML
    public Label serieLabel;
    @Translate
    @FXML
    public Label authorsLabel;
    @Translate
    @FXML
    public Label descriptionLabel;

    @FXML
    private TextField nameField;
    @FXML
    private TextField serieField;
    @FXML
    private TextArea authorsArea;
    @FXML
    private TextArea descriptionArea;

    public void initialize() {
        TranslateProcessor.translate(DocumentSettingsController.class, this);

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
