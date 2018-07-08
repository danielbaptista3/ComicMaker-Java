package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.comicteam.plugins.languages.Languable;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.LanguageHelper;
import org.comicteam.helpers.SettingsHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GeneralSettingsController {
    @Translate
    @FXML
    public Label languageLabel;
    @Translate
    @FXML
    public Label defaultSavePathLabel;

    @FXML
    private ChoiceBox<String> languagesCombo;
    @FXML
    private Button directoryChooserButton;
    @FXML
    private TextField savePathField;

    public void initialize() {
        TranslateProcessor.translate(GeneralSettingsController.class, this);

        savePathField.setText(SettingsHelper.get("savePath"));

        List<Languable> languages = LanguageHelper.getLanguagesAvailables();

        for (Languable l : languages) {
            languagesCombo.getItems().add(l.getName());
        }

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

    public void languagesComboAction() {
        if (languagesCombo.getValue() != null) {
            try {
                SettingsHelper.set("language", languagesCombo.getValue());
                TranslateProcessor.translate(MainController.class, MainController.controller);
                TranslateProcessor.translate(GeneralSettingsController.class, this);

                if (MenuController.controller != null) {
                    TranslateProcessor.translate(MenuController.class, MenuController.controller);
                }

                if (WorkingController.controller != null) {
                    TranslateProcessor.translate(WorkingController.class, WorkingController.controller);
                }

                if (RightClickBookController.controller != null) {
                    TranslateProcessor.translate(RightClickBookController.class, RightClickBookController.controller);
                }

                if (RightClickPageController.controller != null) {
                    TranslateProcessor.translate(RightClickPageController.class, RightClickPageController.controller);
                }

                if (RightClickPanelController.controller != null) {
                    TranslateProcessor.translate(RightClickPanelController.class, RightClickPanelController.controller);
                }

                if (RightClickModelController.controller != null) {
                    TranslateProcessor.translate(RightClickModelController.class, RightClickModelController.controller);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}