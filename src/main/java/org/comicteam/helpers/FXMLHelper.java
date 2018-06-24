package org.comicteam.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.comicteam.*;
import org.comicteam.controllers.WorkingController;
import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.models.ComicModel;

import java.io.File;

public class FXMLHelper {
    public static Border redBorder;
    public static Border defaultBorder;

    static {
        redBorder = new Border(
                new BorderStroke(
                        Paint.valueOf("red"),
                        BorderStrokeStyle.SOLID,
                        null,
                        null
                )
        );

        defaultBorder = new Border(
                new BorderStroke(null, null, null, null)
        );
    }

    public static boolean nameFieldCorrect(TextField nameField) {
        return !nameField.getText().isEmpty();
    }

    public static boolean integerFieldCorrect(TextField sizeField) {
        return sizeField.getText().matches("[0-9]+");
    }

    public static void setNameFieldBorder(TextField nameField) {
        if (!nameFieldCorrect(nameField)) {
            nameField.setBorder(redBorder);
        } else {
            nameField.setBorder(defaultBorder);
        }
    }

    public static void setSizeFieldBorder(TextField sizeField) {
        if (!integerFieldCorrect(sizeField)) {
            sizeField.setBorder(redBorder);
        } else {
            sizeField.setBorder(defaultBorder);
        }
    }

    public static void openNewProjectForm() {
        NewProjectForm np = new NewProjectForm();
        np.start(new Stage(StageStyle.DECORATED));
    }

    public static File chooseFile(Node node) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(SettingsHelper.get("savePath")));
        return chooser.showOpenDialog(node.getScene().getWindow());
    }

    public static boolean openProject(Node node) {
        File file = chooseFile(node);

        if (file != null) {
            if (ComicBookHelper.isACorrectDescriptor(file.getPath())) {
                ComicBookHelper.openedBook = ComicBookHelper.open(file.getPath());
            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static void saveProject() {
        ComicBookHelper.saveProject();
    }

    public static void openSettingsForm() {
        SettingsForm s = new SettingsForm();
        s.start(new Stage(StageStyle.DECORATED));
    }

    public static void openWorkingForm() {
        WorkingForm wf = new WorkingForm();
        wf.start(new Stage(StageStyle.DECORATED));
    }

    public static void openSavingWarningForm() {
        SavingWarningForm swf = new SavingWarningForm();
        swf.start(new Stage(StageStyle.DECORATED));
    }

    public static void closeAllWindows(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();

        if (Window.getWindows().size() > 1) {
            for (Window window : Window.getWindows()) {
                ((Stage) window).close();
            }
        }

        stage.close();
    }

    public static void closeWindow(Node node) {
        ((Stage) node.getScene().getWindow()).close();
    }

    public static ComicPage getSelectedComicPage() {
        return (ComicPage) ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue();
    }

    public static ComicPanel getSelectedComicPanel() {
        return (ComicPanel) ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue();
    }

    public static ComicPage getComicPageOfSelectedComicPanel() {
        TreeItem item = ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem());
        int indexPage = item.getParent().getParent().getChildren().indexOf(item.getParent());

        return (ComicPage) (((TreeItem) WorkingController.controller.componentsTree.getRoot().getChildren().get(indexPage)).getValue());
    }

    public static Class getClassOfSelectedObject() {
        return ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue().getClass();
    }

    public static ComicModel getSelectedModel() {
        return (ComicModel) ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getValue();
    }

    public static ComicPanel getPanelOfSelectedModel() {
        return (ComicPanel) ((TreeItem) WorkingController.controller.componentsTree.getSelectionModel().getSelectedItem()).getParent().getValue();
    }
}
