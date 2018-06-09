package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.AddPanelForm;
import org.comicteam.helpers.ComicBookHelper;

public class RightClickPageController {
    @FXML
    public void deletePageButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        ComicBookHelper.openedBook.getPages().remove(
                ((TreeItem)WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue()
        );

        ComicBookHelper.saved = false;

        WorkingController.controller.redrawComponentsTree();
    }

    @FXML
    public void addPanelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        AddPanelForm form = new AddPanelForm();
        form.start(new Stage(StageStyle.DECORATED));
    }
}
