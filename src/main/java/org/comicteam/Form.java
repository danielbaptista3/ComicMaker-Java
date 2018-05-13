package org.comicteam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.MM;

import java.awt.*;
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

        ComicBook book = ComicBookHelper.open("Bulletin Météo.cm");

        Scene scene  = new Scene(root, MM.toPx(book.getSize().getHorizontal()), MM.toPx(book.getSize().getVertical()));

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(book.getName());

        primaryStage.show();
    }
}