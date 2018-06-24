package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;

public class SavingWarningController {
    public static boolean mustCancel;
    public static SavingWarningController controller;

    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private Button cancelButton;

    public void initialize() {
        mustCancel = false;
        controller = this;
    }

    @FXML
    public void yesButtonClick() {
        FXMLHelper.saveProject();
        FXMLHelper.closeWindow(yesButton);
        FXMLHelper.closeAllWindows(WorkingController.controller.pane);
    }

    @FXML
    public void noButtonClick() {
        FXMLHelper.closeWindow(noButton);
        FXMLHelper.closeWindow(WorkingController.controller.pane);
    }

    @FXML
    public void cancelButtonClick(MouseEvent e) {
        FXMLHelper.closeWindow(cancelButton);
        mustCancel = true;
    }
}
