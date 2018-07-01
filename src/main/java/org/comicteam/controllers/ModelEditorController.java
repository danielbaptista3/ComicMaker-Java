package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.helpers.MM;
import org.comicteam.layouts.ComicLayout;
import org.comicteam.layouts.Position;
import org.comicteam.layouts.Size;
import org.comicteam.models.ComicModel;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class ModelEditorController {
    @FXML
    private Pane drawingPane;

    @FXML
    private ToggleButton eraserButton;
    @FXML
    private ToggleButton penButton;
    @FXML
    private ToggleButton lineButton;

    @FXML
    private Slider eraserSlider;
    @FXML
    private Slider penSlider;
    @FXML
    private Slider lineSlider;

    private ToggleGroup toggles;

    @FXML
    private ColorPicker colorPicker;

    private Canvas c;

    double x, y;

    @FXML
    private Button saveButton;
    @FXML
    private TextField modelNameField;

    public void initialize() {
        toggles = new ToggleGroup();
        penButton.setSelected(true);
        toggles.getToggles().add(eraserButton);
        toggles.getToggles().add(penButton);
        toggles.getToggles().add(lineButton);

        prepareCanvas();
    }

    private void prepareCanvas() {
        //drawingPane.setBorder(FXMLHelper.defaultBorder);

        c = new Canvas(610, 540);
        colorPicker.setValue(Color.BLACK);

        colorPicker.setOnAction(e -> {
            c.getGraphicsContext2D().setStroke(colorPicker.getValue());
            c.getGraphicsContext2D().setFill(colorPicker.getValue());
        });

        drawingPane.setOnMouseDragged(e -> {
            double x1 = e.getSceneX() - drawingPane.getLayoutX();
            double y1 = e.getSceneY() - drawingPane.getLayoutY();

            double x = x1 + e.getX();
            double y = y1 + e.getY();

            if (toggles.getSelectedToggle() == eraserButton) {
                c.getGraphicsContext2D().clearRect(x1, y1, eraserSlider.getValue(), eraserSlider.getValue());
            } else if (toggles.getSelectedToggle() == penButton) {
                c.getGraphicsContext2D().fillOval(x1, y1, penSlider.getValue(), penSlider.getValue());
            } else if (toggles.getSelectedToggle() == lineButton) {
                drawingPane.setOnMouseReleased(r -> {
                    double xr = r.getSceneX() - drawingPane.getLayoutX();
                    double yr = r.getSceneY() - drawingPane.getLayoutY();

                    double xr2 = xr + r.getX();
                    double yr2 = yr + r.getY();

                    c.getGraphicsContext2D().setLineWidth(lineSlider.getValue());
                    c.getGraphicsContext2D().strokeLine(xr, yr, xr2, yr2);
                });
            }
        });

        c.setLayoutX(0);
        c.setLayoutY(0);

        drawingPane.getChildren().add(c);
    }

    @FXML
    public void saveButtonClick() {
        //trim canvas
        /*WritableImage im = new WritableImage((int) c.getWidth(), (int) c.getHeight());

        c.snapshot(null, im);
        ImageIO.read(new ByteArrayInputStream(im.))

        int bx = 0;


        for (; bx < im.getWidth(); bx++) {
            for (int y = 0; y < im.getHeight(); y++) {
                if (im.getPixelReader().getColor(bx, y).equals(Color.WHITE)) {

                }
            }
        }*/

        ComicModel model = new ComicModel(
                modelNameField.getText(),
                c,
                new ComicLayout(
                        new Position(0, 0),
                        new Size(
                                MM.toMM((int) c.getWidth()),
                                MM.toMM((int) c.getHeight())
                        )
                ),
                0);

        FXMLHelper.getSelectedComicPanel().getModels().add(model);

        FXMLHelper.closeWindow(modelNameField);
        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }
}