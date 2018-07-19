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
import org.comicteam.CMFile;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.helpers.MM;
import org.comicteam.layouts.*;
import org.comicteam.models.ComicModel;

import java.io.ObjectOutputStream;

public class EditorController {
    public static EditorController controller;

    @FXML
    public AnchorPane editorPane;

    public void initialize() {
        if (CMFile.cmfile.book.getPages().size() > 0) {
            redrawEditorPane();
        }

        controller = this;
    }

    public void redrawEditorPane() {
        editorPane.getChildren().clear();

        CMFile.cmfile.book.sortPanels();

        if (CMFile.cmfile.book.getPages().size() == 0) {
            return;
        }

        for (ComicPanel panel : CMFile.cmfile.book.getPages().get(CMFile.cmfile.currentPage).getPanels()) {
            Pane panelPane = CanvasHelper.getPanel(panel);

            setPaneMouseActions(panelPane, panel);

            for (ComicModel model : panel.getModels()) {
                panelPane.getChildren().add(model.getCanvas());

                setCanvasMouseActions(model.getCanvas(), model);
            }

            editorPane.getChildren().add(panelPane);
        }
    }

    public Node getSelectedPanel() {
        return editorPane.getChildren().get(FXMLHelper.getComicPageOfSelectedComicPanel().getPanels().indexOf(FXMLHelper.getSelectedComicPanel()));
    }

    public void showPage(ComicPage page) {
        CMFile.cmfile.currentPage = page.getIndex();
        redrawEditorPane();
    }

    public void setPaneMouseActions(Pane panelPane, ComicPanel panel) {
        panelPane.setOnMouseEntered(e -> {
            CanvasHelper.selectPanel(panelPane);
            WorkingController.controller.alimentateMeasurePane(panel);
        });

        panelPane.setOnMouseExited(e -> {
            CanvasHelper.unselectAllPanels();
        });

        panelPane.setOnMouseDragged(e -> {
            CanvasHelper.selectPanel(panelPane);

            switch (e.getButton()) {
                case PRIMARY:
                    movePanel(panelPane, panel, e);
                    break;
                case SECONDARY:
                    resizePanel(panelPane, panel, e);
                    break;
            }

            CMFile.cmfile.saved = false;
        });
    }

    public void setCanvasMouseActions(Canvas canvas, ComicModel model) {
        canvas.setOnMouseEntered(e -> {
            WorkingController.controller.alimentateMeasureModel(model);
        });

        canvas.setOnMouseDragged(e -> {
            switch (e.getButton()) {
                case PRIMARY:
                    moveModel(canvas, model, e);
                    break;
                case SECONDARY:
                    resizeModel(canvas, model, e);
                    break;
            }
        });
    }

    public boolean canMovePanel(Node node, MouseEvent e) {
        int leftX = (int) (e.getSceneX() - ((Pane) node).getWidth() / 2);
        int rightX = (int) (e.getSceneX() + ((Pane) node).getWidth() / 2);

        int topY = (int) (e.getSceneY() - ((Pane) node).getHeight() / 2);
        int downY = (int) (e.getSceneY() + ((Pane) node).getHeight() / 2);

        return leftX >= 0 && rightX <= editorPane.getWidth() && topY >= 0 && downY <= editorPane.getHeight();
    }

    /*public boolean canMoveModel(Node node, MouseEvent e) {
        int leftX = (int) (e.getSceneX() - ((Pane) node).getWidth() / 2);
        int rightX = (int) (e.getSceneX() + ((Pane) node).getWidth() / 2);

        int topY = (int) (e.getSceneY() - ((Pane) node).getHeight() / 2);
        int downY = (int) (e.getSceneY() + ((Pane) node).getHeight() / 2);

        return leftX >= 0 && rightX <= editorPane.getWidth() && topY >= 0 && downY <= editorPane.getHeight();
    }*/

    public boolean canResize(MouseEvent e) {
        return e.getSceneX() <= editorPane.getWidth() && e.getSceneY() <= editorPane.getHeight();
    }

    public void movePanel(Node node, ComicPanel panel, MouseEvent e) {
        if (canMovePanel(node, e)) {
            int x = (int) (e.getSceneX() - ((Pane) node).getWidth() / 2);
            int y = (int) (e.getSceneY() - ((Pane) node).getHeight() / 2);

            CanvasHelper.movePanel(node, panel, x, y);
            WorkingController.controller.alimentateMeasurePane(panel);
            WorkingController.controller.redrawComponentsTree();
        }
    }

    public void moveModel(Canvas canvas, ComicModel model, MouseEvent e) {
        //if (canMove(upper, node, e)) {
            int x = (int) (e.getSceneX() - canvas.getBoundsInLocal().getWidth() / 2);
            int y = (int) (e.getSceneY() - canvas.getBoundsInLocal().getHeight() / 2);

            CanvasHelper.moveModel(canvas, model, x, y);
            WorkingController.controller.alimentateMeasureModel(model);
        //}
    }

    public void resizePanel(Node node, ComicPanel panel, MouseEvent e) {
        if (canResize(e)) {
            double newWidth = e.getSceneX() - node.getLayoutX();
            double newHeight = e.getSceneY() - node.getLayoutY();

            CanvasHelper.resizePanel(node, panel, (int) newWidth, (int) newHeight);
            WorkingController.controller.alimentateMeasurePane(panel);
        }
    }

    public void resizeModel(Canvas canvas, ComicModel model, MouseEvent e) {
        //if (canResize(e)) {
            double newWidth = e.getSceneX() - canvas.getLayoutX();
            double newHeight = e.getSceneY() - canvas.getLayoutY();

            CanvasHelper.resizeModel(canvas, model, (int) newWidth, (int) newHeight);
            WorkingController.controller.alimentateMeasureModel(model);
        //}
    }

    public void addImage(Image image) {
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

        setCanvasMouseActions(canvas, model);

        FXMLHelper.getSelectedComicPanel().getModels().add(model);

        ((Pane) getSelectedPanel()).getChildren().add(canvas);
    }
}