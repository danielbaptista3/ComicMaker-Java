package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import org.comicteam.CMFile;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.helpers.MM;
import org.comicteam.layouts.*;
import org.comicteam.models.ComicModel;

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
        editorPane.setBackground(Background.EMPTY);

        CMFile.cmfile.book.sortPanels();

        if (CMFile.cmfile.book.getPages().size() == 0) {
            return;
        }

        for (ComicPanel panel : CMFile.cmfile.book.getPages().get(CMFile.cmfile.currentPage).getPanels()) {
            Pane panelPane = CanvasHelper.getPanel(panel);

            if (panel.getModels().size() == 0) {
                setMouseActions(panelPane, panel, null, null);
            }

            for (ComicModel model : panel.getModels()) {
                System.out.println(model.getLayout().getPosition());
                panelPane.getChildren().add(model.getCanvas());

                setMouseActions(panelPane, panel, model.getCanvas(), model);
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

    boolean can;

    public void setMouseActions(Pane panelPane, ComicPanel panel, Canvas canvas, ComicModel model) {
        can = true;

        if (canvas != null && model != null) {
            canvas.setOnMouseDragged(c -> {
                moveModel(panelPane, canvas, model, c);
                CMFile.cmfile.saved = false;
                can = false;
            });
        }

        if (panelPane != null && panel != null) {
            panelPane.setOnMouseDragged(e -> {
                if (can) {
                    CanvasHelper.selectPanel(panelPane);

                    switch (e.getButton()) {
                        case PRIMARY:
                            movePanel(panelPane, panel, e);
                            break;
                        case SECONDARY:
                            resizePanel(panelPane, panel, e);
                            break;
                    }
                }

                can = true;

                CMFile.cmfile.saved = false;
            });
        }
    }

    //ok
    public boolean canMovePanel(Node node, MouseEvent e) {
        int leftX = (int) (e.getSceneX() - ((Pane) node).getWidth() / 2);
        int rightX = (int) (e.getSceneX() + ((Pane) node).getWidth() / 2);

        int topY = (int) (e.getSceneY() - ((Pane) node).getHeight() / 2);
        int downY = (int) (e.getSceneY() + ((Pane) node).getHeight() / 2);

        return leftX >= 0 && rightX <= editorPane.getWidth() && topY >= 0 && downY <= editorPane.getHeight();
    }

    //ok
    public boolean canResizePanel(MouseEvent e) {
        return e.getSceneX() <= editorPane.getWidth() && e.getSceneY() <= editorPane.getHeight();
    }

    //ok
    public void movePanel(Node node, ComicPanel panel, MouseEvent e) {
        if (canMovePanel(node, e)) {
            int x = (int) (e.getSceneX() - ((Pane) node).getWidth() / 2);
            int y = (int) (e.getSceneY() - ((Pane) node).getHeight() / 2);

            CanvasHelper.movePanel(node, panel, x, y);
            WorkingController.controller.alimentateMeasurePane(panel);
            WorkingController.controller.redrawComponentsTree();
        }
    }

    //ok
    public void resizePanel(Node node, ComicPanel panel, MouseEvent e) {
        if (canResizePanel(e)) {
            double newWidth = e.getSceneX() - node.getLayoutX();
            double newHeight = e.getSceneY() - node.getLayoutY();

            CanvasHelper.resizePanel(node, panel, (int) newWidth, (int) newHeight);
            WorkingController.controller.alimentateMeasurePane(panel);
        }
    }

    public boolean canMoveModel(Pane panelPane, Canvas canvas, MouseEvent e) {
        int x = (int) (e.getSceneX() - canvas.getLayoutBounds().getWidth() / 2 - panelPane.getLayoutX());
        int y = (int) (e.getSceneY() - canvas.getLayoutBounds().getHeight() / 2 - panelPane.getLayoutY());

        return x >= 0 && y >= 0 && x + canvas.getWidth() <= panelPane.getWidth() &&
                y + canvas.getHeight() <= panelPane.getHeight();
    }

    public void moveModel(Pane panelPane, Canvas canvas, ComicModel model, MouseEvent e) {
        if (canMoveModel(panelPane, canvas, e)) {
            int x = (int) (e.getSceneX() - canvas.getLayoutBounds().getWidth() / 2 - panelPane.getLayoutX());
            int y = (int) (e.getSceneY() - canvas.getLayoutBounds().getHeight() / 2 - panelPane.getLayoutY());

            CanvasHelper.moveModel(canvas, model, x, y);
            WorkingController.controller.alimentateMeasureModel(model);
        }
    }

    public void resizeModel(Canvas canvas, ComicModel model, MouseEvent e) {
        double newWidth = e.getSceneX() - canvas.getLayoutX();
        double newHeight = e.getSceneY() - canvas.getLayoutY();

        CanvasHelper.resizeModel(canvas, model, (int) newWidth, (int) newHeight);
        WorkingController.controller.alimentateMeasureModel(model);
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

        System.out.println("add actions");
        setMouseActions(((Pane) getSelectedPanel()), FXMLHelper.getSelectedComicPanel(), canvas, model);

        FXMLHelper.getSelectedComicPanel().getModels().add(model);

        ((Pane) getSelectedPanel()).getChildren().add(canvas);
    }
}