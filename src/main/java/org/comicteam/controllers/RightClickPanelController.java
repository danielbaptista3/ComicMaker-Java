package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.CMFile;
import org.comicteam.forms.ClipartForm;
import org.comicteam.forms.ModelEditorForm;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.ExternalDocumentHelper;
import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.ComicPanel;

public class RightClickPanelController {
    public static RightClickPanelController controller;

    @Translate
    @FXML
    public Button addClipartModelButton;
    @Translate
    @FXML
    public Button addModelWithEditorButton;
    @Translate
    @FXML
    public Button addModelWithExternalDocumentButton;
    @Translate
    @FXML
    public Button deletePanelButton;

    public void initialize() {
        TranslateProcessor.translate(RightClickPanelController.class, this);
        controller = this;
    }

    @FXML
    public void addClipartModelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        ClipartForm form = new ClipartForm();
        form.start(new Stage(StageStyle.DECORATED));
    }

    @FXML
    public void addModelWithEditorButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        ModelEditorForm mef = new ModelEditorForm();
        mef.start(new Stage(StageStyle.DECORATED));
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

        CMFile.cmfile.saved = false;

        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }
}
