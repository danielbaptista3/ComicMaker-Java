package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.models.ComicModel;

import java.io.IOException;

public class WorkingController {
    private boolean menuOpened;
    public static WorkingController controller;

    @FXML
    private AnchorPane pane;
    @FXML
    public TreeView componentsTree;
    @FXML
    private TextField currentPageField;
    @FXML
    private Label pageCountLabel;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private VBox rightClickBox;

    public void initialize() {
        redrawComponentsTree();

        controller = this;
    }

    public void redrawComponentsTree() {
        componentsTree.setRoot(new TreeItem(ComicBookHelper.openedBook.getName()));
        componentsTree.getRoot().setExpanded(true);

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

        currentPageField.setText("1");
        pageCountLabel.setText(String.format("/%s", ComicBookHelper.openedBook.getPages().size()));
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
        if (ComicBookHelper.currentPage > 1) {
            ComicBookHelper.currentPage--;
            currentPageField.setText(String.valueOf(ComicBookHelper.currentPage));
        }
    }

    @FXML
    public void nextPageClick() {
        if (ComicBookHelper.currentPage < ComicBookHelper.openedBook.getPages().size()) {
            ComicBookHelper.currentPage++;
            currentPageField.setText(String.valueOf(ComicBookHelper.currentPage));
        }
    }

    @FXML
    public void componentsTreeClick(MouseEvent e) {
        Class elementClass = ((TreeItem) componentsTree.getSelectionModel().getSelectedItem()).getValue().getClass();

        if (e.getButton() == MouseButton.SECONDARY) {
            switch (elementClass.getSimpleName()) {
                case "ComicPage":
                    showPageRightClick("rightClickPage", e.getX(), e.getY());
                    break;
                case "ComicPanel":
                    showPageRightClick("rightClickPanel", e.getX(), e.getY());
                    break;
                default:
                    switch (elementClass.getSuperclass().getSimpleName()) {
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

        if (e.getButton() == MouseButton.PRIMARY) {
            hideComponentsTreeRightClick();
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