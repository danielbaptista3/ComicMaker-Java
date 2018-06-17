package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.MM;
import org.comicteam.layouts.*;
import org.comicteam.models.ComicModel;

public class EditorController {
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

    public boolean canMove(Node node, MouseEvent e) {
        int leftX = (int) e.getSceneX();
        int rightX = (int) (e.getSceneX() + ((Pane) node).getWidth());

        int topY = (int) e.getSceneY();
        int downY = (int) (e.getSceneY() + ((Pane) node).getHeight());

        return leftX >= 0 && rightX <= editorPane.getWidth() && topY >= 0 && downY <= editorPane.getHeight();
    }

    public boolean canResize(MouseEvent e) {
        return e.getSceneX() <= editorPane.getWidth() && e.getSceneY() <= editorPane.getHeight();
    }

    public void movePanel(Node node, ComicPanel panel, MouseEvent e) {
        if (canMove(node, e)) {
            CanvasHelper.movePanel(node, panel, (int) e.getSceneX(), (int) e.getSceneY());
            WorkingController.controller.redrawComponentsTree();
        }
    }

    public void moveModel(Node node, ComicModel model, MouseEvent e) {
        if (canMove(node, e)) {
            //CanvasHelper.moveModel(node, model, (int) e.getSceneX(), (int) e.getSceneY());
        }
    }

    public void resizePanel(Node node, ComicPanel panel, MouseEvent e) {
        if (canResize(e)) {
            double newWidth = e.getSceneX() - node.getLayoutX();
            double newHeight = e.getSceneY() - node.getLayoutY();

            CanvasHelper.resizePanel(node, panel, (int) newWidth, (int) newHeight);
        }
    }

    public void resizeModel(Node node, ComicModel model, MouseEvent e) {
        if (canResize(e)) {
            double newWidth = e.getSceneX() - node.getLayoutX();
            double newHeight = e.getSceneY() - node.getLayoutY();

            //CanvasHelper.resizeModel(node, model, (int) newWidth, (int) newHeight);
        }
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