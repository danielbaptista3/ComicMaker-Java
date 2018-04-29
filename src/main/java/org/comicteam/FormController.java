package org.comicteam;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;

public class FormController  {
    private static final int xSize = 200;
    private static final int ySize = 100;
    private Canvas canvas;

    @FXML
    AnchorPane pane;

    public void initialize() {
        canvas = new Canvas(500, 500);
        try {
            URL url = getClass().getResource("../../../tintin.jpg");
            Image image = new Image(url.toExternalForm());
            //canvas.getGraphicsContext2D().drawImage(image, 100, 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /**/
    }

    /*@FXML
    public void getEllipse(MouseEvent mouse) {
        Canvas c = new Canvas(500, 500);

        c.getGraphicsContext2D().strokeOval(mouse.getX() - xSize / 2, mouse.getY() - ySize / 2, xSize, ySize);

        if (pane.getChildren().size() > 0) {
            pane.getChildren().remove(0);
        }

        pane.getChildren().add(c);
    }*/
}
