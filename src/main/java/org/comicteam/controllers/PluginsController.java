package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.PluginHelper;
import org.comicteam.plugins.Plugin;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginsController {
    @FXML
    public Label pluginNameLabel;
    @FXML
    public Label pluginVersionLabel;
    @FXML
    public Label pluginDescriptionLabel;
    @FXML
    private ListView pluginsList;
    @Translate
    @FXML
    public Button addPluginButton;
    @Translate
    @FXML
    public Button deletePluginButton;

    public void initialize() {
        MenuController.controller.pluginsBox.getChildren().clear();
        TranslateProcessor.translate(PluginsController.class, this);

        pluginsList.getItems().clear();

        for (Class<?> c : PluginHelper.languages) {
            try {
                pluginsList.getItems().add(c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for (Class<?> c : PluginHelper.plugins) {
            try {
                pluginsList.getItems().add(c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (pluginsList.getItems().size() > 0) {
            pluginsList.getSelectionModel().select(0);
            Plugin p = (Plugin) pluginsList.getSelectionModel().getSelectedItem();

            pluginNameLabel.setText(p.getName());
            pluginVersionLabel.setText(p.getVersion());
            pluginDescriptionLabel.setText(p.getDescription());
        } else {
            pluginNameLabel.setText("");
            pluginVersionLabel.setText("");
            pluginDescriptionLabel.setText("");
        }

        pluginsList.setOnMouseClicked(e -> {
            selectPlugin();
        });

        pluginsList.setOnKeyPressed(e -> {
            selectPlugin();
        });
    }

    public void selectPlugin() {
        Plugin p = (Plugin) pluginsList.getSelectionModel().getSelectedItem();

        pluginNameLabel.setText(p.getName());
        pluginVersionLabel.setText(p.getVersion());
        pluginDescriptionLabel.setText(p.getDescription());
    }

    @FXML
    public void addPluginButtonClick() {
        FileChooser chooser = new FileChooser();
        String filename = chooser.showOpenDialog(addPluginButton.getScene().getWindow()).getAbsolutePath();

        Path path = Paths.get(filename);

        if (path.getNameCount() == 0) {
            return;
        }

        PluginHelper.addPlugin(path);
        PluginHelper.loadInstalledPlugins();

        initialize();
    }

    @FXML
    public void deletePluginButtonClick() {
        Plugin p = (Plugin) pluginsList.getSelectionModel().getSelectedItem();

        PluginHelper.deletePlugin(p);

        initialize();
    }
}
