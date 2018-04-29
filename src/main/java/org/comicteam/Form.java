package org.comicteam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Form extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("test.fxml"));
        } catch (IOException e) {
            return;
        }

        Scene scene  = new Scene(root, 500, 500);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}