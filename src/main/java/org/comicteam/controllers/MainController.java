package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.FXMLHelper;

public class MainController {
    public static MainController controller;

    @Translate
    @FXML
    public Button newProjectButton;
    @Translate
    @FXML
    public Button openProjectButton;
    @Translate
    @FXML
    public Button settingsButton;

    public void initialize() {
        TranslateProcessor.translate(MainController.class, this);
        controller = this;
    }

    @FXML
    public void newProjectButtonMouseClick() {
        FXMLHelper.openNewProjectForm();
    }

    @FXML
    public void openProjectButtonMouseClick() {
        if (FXMLHelper.openProject(openProjectButton)) {
            FXMLHelper.closeAllWindows(openProjectButton);
            FXMLHelper.openWorkingForm();
        }
    }

    @FXML
    public void settingsButtonMouseClick() {
        FXMLHelper.openSettingsForm();
    }
}
