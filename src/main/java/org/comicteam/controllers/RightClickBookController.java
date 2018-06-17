package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.layouts.ComicPage;

public class RightClickBookController {
    @FXML
    public void addPageButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        ComicBookHelper.openedBook.getPages().add(
                new ComicPage(
                        ComicBookHelper.openedBook.getPages().size() + 1
                )
        );
        ComicBookHelper.saved = false;

        WorkingController.controller.redrawComponentsTree();
    }
}