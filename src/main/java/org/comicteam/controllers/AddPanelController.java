package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
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

        ComicLayout layout = new ComicLayout(
                new Position(
                        Integer.valueOf(horizontalPositionField.getText()),
                        Integer.valueOf(verticalPositionField.getText())
                ),
                new Size(10, 10)
        );

        ((ComicPage)((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue()).getPanels().add(
                new ComicPanel(layout)
        );

        WorkingController.controller.redrawComponentsTree();

        FXMLHelper.closeWindow(horizontalPositionField);
    }
}