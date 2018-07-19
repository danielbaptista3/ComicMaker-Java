package org.comicteam.controllers;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import org.comicteam.CMFile;

import java.io.IOException;

public class SettingsController {
    @FXML
    private TreeView settingsCategoriesList;
    @FXML
    private AnchorPane rightPane;

    public void initialize() {
        settingsCategoriesList.setRoot(new TreeItem<>("Root"));

        TreeItem<String> general = new TreeItem<>("General");
        settingsCategoriesList.getRoot().getChildren().add(general);

        if (CMFile.cmfile/*.book*/ != null) {
            TreeItem<String> document = new TreeItem<>("Document");
            settingsCategoriesList.getRoot().getChildren().add(document);
        }

        settingsCategoriesList.getRoot().setExpanded(true);

        settingsCategoriesList.getSelectionModel().selectedItemProperty()
                .addListener((ChangeListener<TreeItem<String>>) (observable, oldValue, newValue) -> {
                    String fxmlFilename;

                    switch (newValue.getValue()) {
                        default:
                        case "General":
                            fxmlFilename = "/fxml/generalsettings.fxml";
                            break;
                        case "Document":
                            fxmlFilename = "/fxml/documentsettings.fxml";
                            break;
                    }

                    try {
                        Node node = FXMLLoader.load(getClass().getResource(fxmlFilename));
                        rightPane.getChildren().setAll(node);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        settingsCategoriesList.getSelectionModel().select(0);
    }
}
