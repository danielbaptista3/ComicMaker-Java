package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.comicteam.helpers.FXMLHelper;

public class RightClickModelController {
    @FXML
    private TextField modelNameField;

    public void initialize() {
        modelNameField.setText(FXMLHelper.getSelectedModel().getName());
    }

    @FXML
    public void modelNameFieldReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            WorkingController.controller.redrawComponentsTree();
            WorkingController.controller.hideComponentsTreeRightClick();
            return;
        }

        FXMLHelper.getSelectedModel().setName(modelNameField.getText());
        System.out.println(FXMLHelper.getSelectedModel().getName());
    }

    @FXML
    public void deleteModelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        FXMLHelper.getPanelOfSelectedModel().getModels().remove(FXMLHelper.getSelectedModel());

        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }
}
