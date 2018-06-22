package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.EditorForm;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.layouts.Position;
import org.comicteam.layouts.Size;
import org.comicteam.models.ComicModel;

import java.io.IOException;
import java.util.Collections;

public class WorkingController {
    private boolean menuOpened;
    public static WorkingController controller;

    @FXML
    public AnchorPane pane;
    @FXML
    public TreeView componentsTree;
    @FXML
    private Label currentPageLabel;
    @FXML
    private Label pageCountLabel;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private VBox rightClickBox;
    @FXML
    private AnchorPane measurePane;

    @FXML
    private TextField xField;
    @FXML
    private TextField yField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField heightField;
    @FXML
    private Button okButton;

    public void initialize() {
        measurePane.setVisible(false);

        redrawComponentsTree();

        EditorForm editor = new EditorForm();
        editor.start(new Stage(StageStyle.DECORATED));

        if (ComicBookHelper.openedBook.getPages().size() > 1) {
            selectPage(ComicBookHelper.openedBook.getPages().get(0));
        }

        controller = this;
    }

    public void redrawComponentsTree() {
        componentsTree.setRoot(new TreeItem(ComicBookHelper.openedBook.getName()));
        componentsTree.getRoot().setExpanded(true);

        ComicBookHelper.openedBook.sortPages();
        ComicBookHelper.openedBook.sortPanels();

        for (ComicPage page : ComicBookHelper.openedBook.getPages()) {
            TreeItem<Object> treePage = new TreeItem<>(page);
            componentsTree.getRoot().getChildren().add(treePage);
            componentsTree.getRoot().setExpanded(true);

            for (ComicPanel panel : page.getPanels()) {
                TreeItem<Object> treePanel = new TreeItem<>(panel);
                treePage.getChildren().add(treePanel);
                treePage.setExpanded(true);

                for (ComicModel model : panel.getModels()) {
                    treePanel.getChildren().add(new TreeItem<>(model));
                    treePanel.setExpanded(true);
                }
            }
        }
        componentsTree.refresh();

        if (!currentPageLabel.getText().equals("")) {
            if (Integer.valueOf(currentPageLabel.getText()) > ComicBookHelper.openedBook.getPages().size()) {
                currentPageLabel.setText(String.valueOf(ComicBookHelper.openedBook.getPages().size()));
            }
        }

        pageCountLabel.setText(String.format("%s", ComicBookHelper.openedBook.getPages().size()));
    }

    public void alimentateMeasurePane(Node node) {
        xField.setText(String.valueOf(node.getLayoutX()));
        yField.setText(String.valueOf(node.getLayoutY()));
        widthField.setText(String.valueOf(((Pane) node).getWidth()));
        heightField.setText(String.valueOf(((Pane) node).getHeight()));
    }

    @FXML
    public void measureButtonClick() {
        if (FXMLHelper.integerFieldCorrect(xField) && FXMLHelper.integerFieldCorrect(yField)
                && FXMLHelper.integerFieldCorrect(widthField) && FXMLHelper.integerFieldCorrect(heightField)) {
            switch (getClassOfSelectedObject().getSimpleName()) {
                case "ComicPanel":
                    getSelectedComicPanel().getLayout().setPosition(
                            new Position(
                                    Integer.valueOf(xField.getText()),
                                    Integer.valueOf(yField.getText())
                            )
                    );
                    getSelectedComicPanel().getLayout().setSize(
                            new Size(
                                    Integer.valueOf(widthField.getText()),
                                    Integer.valueOf(heightField.getText())
                            )
                    );
                    break;
            }

            EditorController.controller.redrawEditorPane();
        }
    }

    @FXML
    public void menuButtonClick() {
        if (!menuOpened) {
            try {
                menuPane = FXMLLoader.load(getClass().getResource("../fxml/menu.fxml"));
                pane.getChildren().add(menuPane);
                menuOpened = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            pane.getChildren().remove(menuPane);
            menuOpened = false;
        }
    }

    @FXML
    public void previousPageClick() {
        if (ComicBookHelper.currentPage > 0) {
            selectPage(ComicBookHelper.openedBook.getPages().get(ComicBookHelper.currentPage - 1));
        }
    }

    @FXML
    public void nextPageClick() {
        if (ComicBookHelper.currentPage < ComicBookHelper.openedBook.getPages().size() - 1) {
            selectPage(ComicBookHelper.openedBook.getPages().get(ComicBookHelper.currentPage + 1));
        }
    }

    public void selectPage(ComicPage page) {
        EditorController.controller.showPage(page);

        currentPageLabel.setText(String.valueOf(ComicBookHelper.currentPage + 1));
        componentsTree.getSelectionModel().select(
                componentsTree.getRoot().getChildren().get(ComicBookHelper.currentPage)
        );
    }

    public ComicPage getSelectedComicPage() {
        return (ComicPage) ((TreeItem) componentsTree.getSelectionModel().getSelectedItem()).getValue();
    }

    public ComicPanel getSelectedComicPanel() {
        return (ComicPanel) ((TreeItem) componentsTree.getSelectionModel().getSelectedItem()).getValue();
    }

    public ComicPage getComicPageOfSelectedComicPanel() {
        TreeItem item = ((TreeItem) componentsTree.getSelectionModel().getSelectedItem());
        int indexPage = item.getParent().getParent().getChildren().indexOf(item.getParent());

        return (ComicPage) (((TreeItem) componentsTree.getRoot().getChildren().get(indexPage)).getValue());
    }

    public Class getClassOfSelectedObject() {
        return ((TreeItem) componentsTree.getSelectionModel().getSelectedItem()).getValue().getClass();
    }

    @FXML
    public void componentsTreeClick(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            hideComponentsTreeRightClick();

            switch (getClassOfSelectedObject().getSimpleName()) {
                case "ComicPage":
                    selectPage(getSelectedComicPage());
                    measurePane.setVisible(false);
                    break;
                case "ComicPanel":
                    EditorController.controller.showPage(getComicPageOfSelectedComicPanel());

                    measurePane.setVisible(true);

                    CanvasHelper.selectPanel(
                            EditorController.controller.editorPane.getChildren(),
                            ((Pane) EditorController.controller.editorPane.getChildren().get(getComicPageOfSelectedComicPanel().getPanels().indexOf(getSelectedComicPanel())))
                    );
            }
        }

        if (e.getButton() == MouseButton.SECONDARY) {
            switch (getClassOfSelectedObject().getSimpleName()) {
                case "ComicPage":
                    showPageRightClick("rightClickPage", e.getX(), e.getY());
                    break;
                case "ComicPanel":
                    showPageRightClick("rightClickPanel", e.getX(), e.getY());
                    break;
                default:
                    switch (getClassOfSelectedObject().getSuperclass().getSimpleName()) {
                        case "ComicModel":
                        case "Balloon":
                            showPageRightClick("rightClickModel", e.getX(), e.getY());
                            break;
                        default:
                            showPageRightClick("rightClickBook", e.getX(), e.getY());
                            break;
                    }

                    break;
            }
        }
    }

    public void showPageRightClick(String fxml, double x, double y) {
        try {
            hideComponentsTreeRightClick();

            rightClickBox = FXMLLoader.load(getClass().getResource(String.format("../fxml/%s.fxml", fxml)));
            rightClickBox.setLayoutX(x);
            rightClickBox.setLayoutY(y + 50);

            pane.getChildren().add(rightClickBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hideComponentsTreeRightClick() {
        if (rightClickBox != null) {
            pane.getChildren().remove(rightClickBox);
            rightClickBox = null;
        }
    }
}