package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.CMFile;
import org.comicteam.forms.PluginsForm;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.helpers.PluginHelper;
import org.comicteam.helpers.UpdateHelper;

public class MenuController {
    public static MenuController controller;

    @Translate
    @FXML
    public Button newProjectButton;
    @Translate
    @FXML
    public Button openProjectButton;
    @Translate
    @FXML
    public Button saveProjectButton;
    @Translate
    @FXML
    public Button settingsButton;
    @Translate
    @FXML
    public Button pluginsButton;
    @Translate
    @FXML
    public Button updateButton;
    @Translate
    @FXML
    public Button quitButton;
    @FXML
    public VBox pluginsBox;

    public void initialize() {
        if (!UpdateHelper.newVersionIsAvailable()) {
            updateButton.setVisible(false);
        }

        TranslateProcessor.translate(MenuController.class, this);
        controller = this;

        PluginHelper.loadInstalledPlugins();
    }

    @FXML
    public void newProjectButtonClick() {
        if (!CMFile.cmfile.saved) {
            FXMLHelper.openSavingWarningForm();
        }

        FXMLHelper.openNewProjectForm();
    }

    @FXML
    public void openProjectButtonClick() {
        if (!CMFile.cmfile.saved) {
            FXMLHelper.openSavingWarningForm();
        }

        if (FXMLHelper.openProject(openProjectButton)) {
            FXMLHelper.closeAllWindows(openProjectButton);
            FXMLHelper.openWorkingForm();
        }
    }

    @FXML
    public void saveProjectButtonClick() {
        FXMLHelper.saveProject();
    }

    @FXML
    public void settingsButtonClick() {
        FXMLHelper.openSettingsForm();
    }

    @FXML
    public void pluginsButtonClick() {
        PluginsForm pf = new PluginsForm();
        pf.start(new Stage(StageStyle.DECORATED));
    }

    @FXML
    public void quitButtonClick() {
        if (!CMFile.cmfile.saved) {
            FXMLHelper.openSavingWarningForm();
        } else {
            FXMLHelper.closeAllWindows(quitButton);
        }
    }

    @FXML
    public void updateButtonClick() {
        UpdateHelper.writeLatestVersionFile();
    }
}