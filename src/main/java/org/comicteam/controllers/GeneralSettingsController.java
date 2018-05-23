package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.comicteam.helpers.LanguageHelper;
import org.comicteam.helpers.SettingsHelper;

import java.io.File;
import java.io.IOException;

public class GeneralSettingsController {
    @FXML
    private ChoiceBox<String> languagesCombo;
    @FXML
    private Button directoryChooserButton;
    @FXML
    private TextField savePathField;
    @FXML
    private TextField modelEditorField;

    public void initialize() {
        savePathField.setText(SettingsHelper.get("savePath"));
        modelEditorField.setText(SettingsHelper.get("modelEditor"));

        languagesCombo.getItems().setAll(LanguageHelper.getLanguagesAvailables());
        languagesCombo.setValue(SettingsHelper.get("language"));

        languagesCombo.setOnAction((e) -> languagesComboAction());
    }

    @FXML
    public void directoryChooserButtonClick() {
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(directoryChooserButton.getScene().getWindow());

        try {
            SettingsHelper.set("savePath", file.getPath());
            savePathField.setText(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modelEditorButtonClick() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(directoryChooserButton.getScene().getWindow());

        try {
            SettingsHelper.set("modelEditor", file.getPath());
            modelEditorField.setText(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void languagesComboAction() {
        if (languagesCombo.getValue() != null) {
            try {
                SettingsHelper.set("language", languagesCombo.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}