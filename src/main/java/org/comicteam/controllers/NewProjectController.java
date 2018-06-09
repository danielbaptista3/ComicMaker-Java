package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.comicteam.ComicBook;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.layouts.Size;

import java.util.Arrays;

public class NewProjectController {
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
    @FXML
    private Button createProjectButton;

    @FXML
    public void createProjectButtonClick() {
        if (!FXMLHelper.nameFieldCorrect(nameField) || !FXMLHelper.integerFieldCorrect(hSizeField) || !FXMLHelper.integerFieldCorrect(vSizeField)) {
            nameFieldKeyReleased();
            hSizeFieldKeyReleased();
            vSizeFieldKeyReleased();

            return;
        }

        ComicBookHelper.openedBook = new ComicBook(
                nameField.getText(),
                serieField.getText(),
                Arrays.asList(authorsArea.getText().split("\n")),
                descriptionArea.getText(),
                new Size(
                        Integer.valueOf(hSizeField.getText()),
                        Integer.valueOf(vSizeField.getText())
                )
        );
        ComicBookHelper.saved = false;

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