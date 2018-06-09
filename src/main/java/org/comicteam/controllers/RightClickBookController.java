package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.AddPageForm;

public class RightClickBookController {
    @FXML
    public void addPageButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        AddPageForm form = new AddPageForm();
        form.start(new Stage(StageStyle.DECORATED));
    }
}