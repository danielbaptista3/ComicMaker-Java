package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.comicteam.helpers.ClipartHelper;

public class ClipartController {
    @FXML
    private TextField keywordSearchField;
    @FXML
    private ScrollPane resultsScrollPane;
    @FXML
    private GridPane resultsGridPane;

    @FXML
    public void searchButtonClick() {
        ClipartHelper.getCanvas(keywordSearchField.getText());
    }
}