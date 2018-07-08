package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.CMFile;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.layouts.ComicPage;

public class RightClickBookController {
    public static RightClickBookController controller;

    @Translate
    @FXML
    public Button addPageButton;

    public void initialize() {
        TranslateProcessor.translate(RightClickBookController.class, this);
        controller = this;
    }

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