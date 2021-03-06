package org.comicteam.forms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene  = new Scene(root, 600, 500);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Paramètres");
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/settings.png"))));

        primaryStage.showAndWait();
    }
}
