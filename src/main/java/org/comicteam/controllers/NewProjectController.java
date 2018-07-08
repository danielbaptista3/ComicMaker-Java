package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.comicteam.CMFile;
import org.comicteam.ComicBook;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.layouts.Size;

import java.util.Arrays;

public class NewProjectController {
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
    @FXML
    private TextField hSizeField;
    @FXML
    private TextField vSizeField;
    @Translate
    @FXML
    private Button createProjectButton;

    public void initialize() {
        TranslateProcessor.translate(NewProjectController.class, this);
    }

    @FXML
    public void createProjectButtonClick() {
        if (!FXMLHelper.nameFieldCorrect(nameField) || !FXMLHelper.integerFieldCorrect(hSizeField) || !FXMLHelper.integerFieldCorrect(vSizeField)) {
            nameFieldKeyReleased();
            hSizeFieldKeyReleased();
            vSizeFieldKeyReleased();

            return;
        }

        CMFile.cmfile.book = new ComicBook(
                nameField.getText(),
                serieField.getText(),
                Arrays.asList(authorsArea.getText().split("\n")),
                descriptionArea.getText(),
                new Size(
                        Integer.valueOf(hSizeField.getText()),
                        Integer.valueOf(vSizeField.getText())
                )
        );
        CMFile.cmfile.saved = false;

        FXMLHelper.closeAllWindows(createProjectButton);

        FXMLHelper.openWorkingForm();
    }

    @FXML
    public void nameFieldKeyReleased() {
        FXMLHelper.setNameFieldBorder(nameField);
    }

    @FXML
    public void hSizeFieldKeyReleased() {
        FXMLHelper.setSizeFieldBorder(hSizeField);
    }

    @FXML
    public void vSizeFieldKeyReleased() {
        FXMLHelper.setSizeFieldBorder(vSizeField);
    }
}