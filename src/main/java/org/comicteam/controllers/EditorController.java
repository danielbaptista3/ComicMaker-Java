package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.MM;
import org.comicteam.layouts.*;
import org.comicteam.models.ComicModel;

public class EditorController {
    private double mouseX;
    private double mouseY;

    public static EditorController controller;

    @FXML
    public AnchorPane editorPane;

    public void initialize() {
        if (ComicBookHelper.openedBook.getPages().size() > 0) {
            redrawEditorPane();
        }

        controller = this;
    }

    public void redrawEditorPane() {
        editorPane.getChildren().clear();

        ComicBookHelper.openedBook.sortPanels();

        for (ComicPanel panel : ComicBookHelper.openedBook.getPages().get(ComicBookHelper.currentPage).getPanels()) {
            Pane panelPane = CanvasHelper.getPanel(panel);

            panelPane.setOnMouseClicked(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            });

            panelPane.setOnMouseDragged((e) -> {
                switch (e.getButton()) {
                    case PRIMARY:
                        movePanel(panelPane, panel, e);
                        break;
                    case SECONDARY:
                        resizePanel(panelPane, panel, e);
                        break;
                }

                ComicBookHelper.saved = false;
            });

            editorPane.getChildren().add(panelPane);
        }
    }

    public void showPage(ComicPage page) {
        ComicBookHelper.currentPage = page.getIndex();
        redrawEditorPane();
    }

    public void movePanel(Node node, ComicPanel panel, MouseEvent e) {
        double x = node.getLayoutX() + e.getX() - mouseX;
        double y = node.getLayoutY() + e.getY() - mouseY;

        CanvasHelper.movePanel(node, panel, x, y);
        WorkingController.controller.alimentateMeasurePane(node);
    }

    public void moveModel(Node node, ComicModel model, MouseEvent e) {
        //CanvasHelper.moveModel(node, model, (int) e.getSceneX(), (int) e.getSceneY());
    }

    public void resizePanel(Node node, ComicPanel panel, MouseEvent e) {
        double x = node.getLayoutX() + e.getX();
        double y = node.getLayoutY() + e.getY();

        /*System.out.println(x);
        System.out.println(y);*/

        CanvasHelper.resizePanel(node, panel, x, y);
        WorkingController.controller.alimentateMeasurePane(node);
    }

    public void resizeModel(Node node, ComicModel model, MouseEvent e) {
        //double newWidth = e.getSceneX() - node.getLayoutX();
        //double newHeight = e.getSceneY() - node.getLayoutY();

        //CanvasHelper.resizeModel(node, model, (int) newWidth, (int) newHeight);
    }

    public void addImage(Image image) {
        ImageView imageView = new ImageView(image);

        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        canvas.getGraphicsContext2D().drawImage(image, 0, 0);

        ComicModel model = new ComicModel(
                "Model",
                canvas,
                new ComicLayout(
                        new Position(0, 0),
                        new Size(
                                MM.toMM((int) image.getWidth()),
                                MM.toMM((int) image.getHeight())
                        )
                ),
                0
        );

        ((ComicPanel) ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue()).getModels().add(
                model
        );

        imageView.setOnMouseDragged(e -> {
            switch (e.getButton()) {
                case PRIMARY:
                    moveModel(imageView, model, e);
                    break;
                case SECONDARY:
                    break;
            }
        });

        editorPane.getChildren().add(canvas);
    }
}