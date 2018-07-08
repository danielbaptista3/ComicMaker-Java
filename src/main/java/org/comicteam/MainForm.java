package org.comicteam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.comicteam.helpers.PluginHelper;

import java.io.IOException;

public class MainForm extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PluginHelper.loadInstalledPlugins();

        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene  = new Scene(root, 700, 500);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("ComicMaker");

        primaryStage.show();
    }
}