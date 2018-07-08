package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PluginsController {
    @FXML
    ToggleGroup group;
    @FXML
    private RadioButton installedRadio;
    @FXML
    private RadioButton availableRadio;
    @FXML
    private ListView pluginsList;

    public void initialize() {
        group = new ToggleGroup();
        group.getToggles().add(installedRadio);
        group.getToggles().add(availableRadio);
    }

    @FXML
    public void installedRadioAction() {
        pluginsList.getItems().clear();

        //pluginsList.getItems().
    }

    @FXML
    public void availableRadioAction() {

    }
}
