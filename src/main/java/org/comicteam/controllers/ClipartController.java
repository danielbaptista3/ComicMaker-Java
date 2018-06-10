package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.SVGPath;
import org.comicteam.helpers.ClipartHelper;

public class ClipartController {
    @FXML
    private TextField keywordSearchField;
    @FXML
    private ScrollBar resultsScrollBar;
    @FXML
    private GridPane resultsGridPane;
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView imageView00;
    @FXML
    private ImageView imageView01;
    @FXML
    private ImageView imageView10;
    @FXML
    private ImageView imageView11;

    //@FXML
    //private SVGPath svgPath;

    @FXML
    public void searchButtonClick() {
        Image topLeft = ClipartHelper.getImage(keywordSearchField.getText(), 1, 0);
        Image topRight = ClipartHelper.getImage(keywordSearchField.getText(), 1, 1);
        Image bottomLeft = ClipartHelper.getImage(keywordSearchField.getText(), 1, 2);
        Image bottomRight = ClipartHelper.getImage(keywordSearchField.getText(), 1, 3);

        imageView00.setImage(topLeft);
        imageView01.setImage(topRight);
        imageView10.setImage(bottomLeft);
        imageView11.setImage(bottomRight);
    }
}