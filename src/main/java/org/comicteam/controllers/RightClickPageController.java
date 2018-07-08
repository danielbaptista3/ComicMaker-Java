package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.AddPanelForm;
import org.comicteam.CMFile;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.layouts.*;

public class RightClickPageController {
    public static RightClickPageController controller;

    @Translate
    @FXML
    public Button deletePageButton;
    @Translate
    @FXML
    public Button addPanelButton;
    @Translate
    @FXML
    public Button upButton;
    @Translate
    @FXML
    public Button downButton;

    public void initialize() {
        TranslateProcessor.translate(RightClickPageController.class, this);
        controller = this;
    }

    @FXML
    public void deletePageButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        CMFile.cmfile.book.getPages().remove(
                ((TreeItem)WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue()
        );

        CMFile.cmfile.saved = false;

        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }

    @FXML
    public void addPanelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        ComicLayout layout = new ComicLayout(
                new Position(
                        Integer.valueOf(0),
                        Integer.valueOf(0)
                ),
                new Size(10, 10)
        );

        ComicPanel panel = new ComicPanel(layout);

        ((ComicPage)((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue()).getPanels().add(
                panel
        );

        CMFile.cmfile.saved = false;

        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }

    @FXML
    public void upButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        int index = FXMLHelper.getSelectedComicPage().getIndex();

        if (index > 0) {
            CMFile.cmfile.book.getPages().get(index).setIndex(index - 1);
            CMFile.cmfile.book.getPages().get(index - 1).setIndex(index);

            WorkingController.controller.redrawComponentsTree();
            CMFile.cmfile.saved = false;
        }
    }

    @FXML
    public void downButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        int index = FXMLHelper.getSelectedComicPage().getIndex();

        if (index < CMFile.cmfile.book.getPages().size()) {
            CMFile.cmfile.book.getPages().get(index).setIndex(index + 1);
            CMFile.cmfile.book.getPages().get(index + 1).setIndex(index);

            CMFile.cmfile.saved = false;
            WorkingController.controller.redrawComponentsTree();
        }
    }
}
