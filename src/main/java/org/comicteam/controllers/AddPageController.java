package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.layouts.ComicPage;

public class AddPageController {
    @FXML
    private TextField pageNumberField;
    @FXML
    private Button addPageButton;

    @FXML
    public void addPageButtonClick() {
        if (FXMLHelper.integerFieldCorrect(pageNumberField)) {
            ComicBookHelper.openedBook.getPages().add(
                    new ComicPage(
                            Integer.valueOf(pageNumberField.getText())
                    )
            );
            ComicBookHelper.saved = false;

            WorkingController.controller.redrawComponentsTree();
            FXMLHelper.closeWindow(addPageButton);
        }
    }
}