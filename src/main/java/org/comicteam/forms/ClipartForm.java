package org.comicteam.forms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ClipartForm extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/clipart.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene  = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ajout d'un modèle Clipart");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/clipart.png"))));

        primaryStage.showAndWait();
    }
}