package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.layouts.*;

public class AddPanelController {
    @FXML
    private TextField horizontalPositionField;
    @FXML
    private TextField verticalPositionField;

    @FXML
    public void addPanelButtonClick() {
        if (!FXMLHelper.integerFieldCorrect(horizontalPositionField)
            || !FXMLHelper.integerFieldCorrect(verticalPositionField)) {

            return;
        }


        FXMLHelper.closeWindow(horizontalPositionField);
    }
}