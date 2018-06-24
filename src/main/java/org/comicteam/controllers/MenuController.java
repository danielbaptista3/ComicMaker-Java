package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;

public class MenuController {
    @FXML
    private Button newProjectButton;
    @FXML
    private Button openProjectButton;
    @FXML
    private Button saveProjectButton;
    @FXML
    private Button exportProjectButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button pluginsButton;
    @FXML
    private Button printButton;
    @FXML
    private Button quitButton;

    @FXML
    public void newProjectButtonClick() {
        if (!ComicBookHelper.saved) {
            FXMLHelper.openSavingWarningForm();
        }

        FXMLHelper.openNewProjectForm();
    }

    @FXML
    public void openProjectButtonClick() {
        if (!ComicBookHelper.saved) {
            FXMLHelper.openSavingWarningForm();
        }

        if (FXMLHelper.openProject(openProjectButton)) {
            FXMLHelper.closeAllWindows(openProjectButton);
            FXMLHelper.openWorkingForm();
        }
    }

    @FXML
    public void saveProjectButtonClick() {
        FXMLHelper.saveProject();
    }

    @FXML
    public void exportProjectButtonClick() {

    }

    @FXML
    public void settingsButtonClick() {
        FXMLHelper.openSettingsForm();
    }

    @FXML
    public void pluginsButtonClick() {

    }

    @FXML
    public void printButtonClick() {
        /*PrinterJob job = javafx.print.PrinterJob.createPrinterJob();
        job.showPrintDialog(printButton.getScene().getWindow());*/
    }

    @FXML
    public void quitButtonClick() {
        if (!ComicBookHelper.saved) {
            FXMLHelper.openSavingWarningForm();
        } else {
            FXMLHelper.closeAllWindows(quitButton);
        }
    }
}