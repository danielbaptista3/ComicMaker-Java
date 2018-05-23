package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.NewProjectForm;
import org.comicteam.helpers.FXMLHelper;

public class WorkingController {
    @FXML
    Button openBookButton;

    public void initialize() {

    }

    @FXML
    public void settingsButtonClick() {
        FXMLHelper.openSettingsForm();
    }

    @FXML
    public void saveProjectButtonClick() {
        FXMLHelper.saveProject();
    }

    @FXML
    public void openProjectButtonClick() {
        if (FXMLHelper.openProject(openBookButton)) {
            FXMLHelper.closeAllWindows(openBookButton);
            FXMLHelper.openWorkingForm();
        }
    }

    @FXML
    public void newProjectButtonClick() {
        //Box de confirm

        NewProjectForm np = new NewProjectForm();
        np.start(new Stage(StageStyle.DECORATED));
    }
}