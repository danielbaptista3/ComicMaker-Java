package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.comicteam.CMFile;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.helpers.MM;
import org.comicteam.layouts.ComicLayout;
import org.comicteam.layouts.Position;
import org.comicteam.layouts.Size;
import org.comicteam.models.ComicModel;

public class ModelEditorController {
    public static ModelEditorController controller;

    @FXML
    public Pane drawingPane;

    @Translate
    @FXML
    public ToggleButton eraserButton;
    @Translate
    @FXML
    public ToggleButton penButton;
    @Translate
    @FXML
    public ToggleButton lineButton;
    @Translate
    @FXML
    public ToggleButton balloonButton;
    @Translate
    @FXML
    public Label modelNameLabel;

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

    @FXML
    private TextField modelNameField;

    private boolean existing;

    public void initialize() {
        TranslateProcessor.translate(ModelEditorController.class, this);

        toggles = new ToggleGroup();
        penButton.setSelected(true);
        toggles.getToggles().add(eraserButton);
        toggles.getToggles().add(penButton);
        toggles.getToggles().add(lineButton);
        toggles.getToggles().add(balloonButton);

        prepareCanvas(null);
        controller = this;
    }

    public void prepareCanvas(Canvas ext) {
        drawingPane.setBorder(FXMLHelper.defaultBorder);

        if (ext == null) {
            existing = false;
            c = new Canvas(610, 540);
        } else {
            existing = true;
            c = ext;
        }
        colorPicker.setValue(Color.BLACK);

        colorPicker.setOnAction(e -> {
            c.getGraphicsContext2D().setStroke(colorPicker.getValue());
            c.getGraphicsContext2D().setFill(colorPicker.getValue());
        });

        drawingPane.setOnMouseDragged(e -> {
            double x1 = e.getSceneX() - drawingPane.getLayoutX();
            double y1 = e.getSceneY() - drawingPane.getLayoutY();

            if (toggles.getSelectedToggle() == eraserButton) {
                c.getGraphicsContext2D().clearRect(x1, y1, eraserSlider.getValue(), eraserSlider.getValue());
            } else if (toggles.getSelectedToggle() == penButton) {
                c.getGraphicsContext2D().fillOval(x1, y1, penSlider.getValue(), penSlider.getValue());
            } else if (toggles.getSelectedToggle() == lineButton) {
                drawingPane.setOnMouseReleased(r -> {
                    double xr = r.getSceneX() - drawingPane.getLayoutX();
                    double yr = r.getSceneY() - drawingPane.getLayoutY();

                    double xr2 = x1 + r.getX();
                    double yr2 = y1 + r.getY();

                    c.getGraphicsContext2D().setLineWidth(lineSlider.getValue());
                    c.getGraphicsContext2D().strokeLine(x1, y1, xr, yr);
                });
            } else if (toggles.getSelectedToggle() == balloonButton) {
                drawingPane.setOnMouseDragged(r -> {
                    double x2 = r.getSceneX() - drawingPane.getLayoutX();
                    double y2 = r.getSceneY() - drawingPane.getLayoutY();

                    c.getGraphicsContext2D().strokeOval(x1, y1, 150, 100);
                });
            }
        });

        c.setLayoutX(0);
        c.setLayoutY(0);

        drawingPane.getChildren().add(c);
    }

    @FXML
    public void saveButtonClick() {
        String modelName = "Model";

        if (!modelNameField.getText().equals("")) {
            modelName = modelNameField.getText();
        }

        ComicModel model = new ComicModel(
                modelName,
                c,
                new ComicLayout(
                        new Position(0, 0),
                        new Size(
                                MM.toMM((int) c.getWidth()),
                                MM.toMM((int) c.getHeight())
                        )
                ),
                0);

        FXMLHelper.closeWindow(modelNameField);
        EditorController.controller.redrawEditorPane();
        CMFile.cmfile.saved = false;

        if (existing) {
            return;
        }

        FXMLHelper.getSelectedComicPanel().getModels().add(model);
        WorkingController.controller.redrawComponentsTree();
    }
}