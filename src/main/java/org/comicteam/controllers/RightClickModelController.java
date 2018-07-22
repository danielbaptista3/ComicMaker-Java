package org.comicteam.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.comicteam.annotations.Translate;
import org.comicteam.annotations.TranslateProcessor;
import org.comicteam.forms.ModelEditorForm;
import org.comicteam.helpers.FXMLHelper;

public class RightClickModelController {
    public static RightClickModelController controller;

    @Translate
    @FXML
    public Button modifyModelButton;
    @Translate
    @FXML
    public Button deleteModelButton;

    @FXML
    private TextField modelNameField;

    public void initialize() {
        TranslateProcessor.translate(RightClickModelController.class, this);
        modelNameField.setText(FXMLHelper.getSelectedModel().getName());
        controller = this;
    }

    @FXML
    public void modelNameFieldReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            WorkingController.controller.redrawComponentsTree();
            WorkingController.controller.hideComponentsTreeRightClick();
            return;
        }

        FXMLHelper.getSelectedModel().setName(modelNameField.getText());
    }

    @FXML
    public void deleteModelButtonClick() {
        WorkingController.controller.hideComponentsTreeRightClick();

        FXMLHelper.getPanelOfSelectedModel().getModels().remove(FXMLHelper.getSelectedModel());

        WorkingController.controller.redrawComponentsTree();
        EditorController.controller.redrawEditorPane();
    }

    @FXML
    public void modifyModelButtonClick() {
        ModelEditorForm form = new ModelEditorForm();
        form.start(new Stage(StageStyle.DECORATED));

        ModelEditorController.controller.drawingPane.getChildren().clear();

        Canvas canvas = FXMLHelper.getSelectedModel().getCanvas();
        canvas.setOnMouseDragged(e -> {});
        ModelEditorController.controller.prepareCanvas(canvas);
    }
}
