package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.AddPanelForm;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.layouts.*;

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

        ComicBookHelper.saved = false;

        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }

    @FXML
    public void upButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        int index = ((ComicPage) ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue()).getIndex();

        if (index > 1) {
            ComicBookHelper.openedBook.getPages().get(index - 1).setIndex(index - 1);
            ComicBookHelper.openedBook.getPages().get(index - 2).setIndex(index);

            WorkingController.controller.redrawComponentsTree();
        }
    }

    @FXML
    public void downButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        int index = ((ComicPage) ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue()).getIndex();

        if (index < ComicBookHelper.openedBook.getPages().size()) {
            ComicBookHelper.openedBook.getPages().get(index - 1).setIndex(index + 1);
            ComicBookHelper.openedBook.getPages().get(index).setIndex(index);

            WorkingController.controller.redrawComponentsTree();
        }
    }
}
