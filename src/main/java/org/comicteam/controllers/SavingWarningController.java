package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;

public class SavingWarningController {
    @FXML
    Button yesButton;
    @FXML
    Button noButton;

    @FXML
    public void yesButtonClick() {
        ComicBookHelper.save(ComicBookHelper.openedBook);
        FXMLHelper.closeWindow(yesButton);
    }

    @FXML
    public void noButtonClick() {
        FXMLHelper.closeWindow(noButton);
    }
}
