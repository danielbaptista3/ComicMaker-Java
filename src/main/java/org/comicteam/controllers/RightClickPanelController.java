package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.ClipartForm;
import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.ComicPanel;

public class RightClickPanelController {
    @FXML
    public void addClipartModelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        ClipartForm form = new ClipartForm();
        form.start(new Stage(StageStyle.DECORATED));
    }

    @FXML
    public void addModelWithEditorButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();


    }

    @FXML
    public void addModelWithExternalDocumentButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();


    }

    @FXML
    public void deletePanelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        TreeItem selectedItem = (TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem();

        ComicPage parentPage = (ComicPage) selectedItem.getParent().getValue();
        ComicPanel panelToDelete = (ComicPanel) selectedItem.getValue();

        parentPage.getPanels().remove(panelToDelete);

        WorkingController.controller.redrawComponentsTree();
    }
}
