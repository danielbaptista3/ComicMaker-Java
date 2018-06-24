package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.ClipartForm;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.ExternalDocumentHelper;
import org.comicteam.helpers.FXMLHelper;
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

        Image image = ExternalDocumentHelper.getImage(WorkingController.controller.componentsTree);

        if (image != null) {
            EditorController.controller.addImage(image);
            WorkingController.controller.redrawComponentsTree();
        }
    }

    @FXML
    public void deletePanelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        TreeItem selectedItem = (TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem();

        ComicPage parentPage = (ComicPage) selectedItem.getParent().getValue();
        ComicPanel panelToDelete = (ComicPanel) selectedItem.getValue();

        parentPage.getPanels().remove(panelToDelete);

        ComicBookHelper.saved = false;

        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }
}
