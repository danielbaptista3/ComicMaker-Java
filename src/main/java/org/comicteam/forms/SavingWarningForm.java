package org.comicteam.forms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.comicteam.controllers.SavingWarningController;

import java.io.IOException;

public class SavingWarningForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/savingwarning.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene  = new Scene(root, 600, 150);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Warning enregistrement");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        primaryStage.setOnCloseRequest(e -> {
            SavingWarningController.controller.cancelButtonClick();
        });

        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/logo.png"))));

        primaryStage.showAndWait();
    }
}
