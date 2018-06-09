package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.comicteam.helpers.FXMLHelper;

public class MainController {
    @FXML
    Button openProjectButton;

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
