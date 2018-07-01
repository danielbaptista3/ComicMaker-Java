package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.CMFile;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.layouts.ComicPage;

public class RightClickBookController {
    @FXML
    public void addPageButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        CMFile.cmfile.book.getPages().add(
                new ComicPage(
                        CMFile.cmfile.book.getPages().size() + 1
                )
        );
        CMFile.cmfile.saved = false;

        WorkingController.controller.redrawComponentsTree();
    }
}