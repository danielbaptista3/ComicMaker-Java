package org.comicteam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;

import java.io.IOException;

public class WorkingForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("fxml/working.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene  = new Scene(
                root,
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight()
        );

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(ComicBookHelper.openedBook.getName());
        primaryStage.setX(0);

        primaryStage.setOnCloseRequest((e) -> {
            if (!ComicBookHelper.saved) {
                FXMLHelper.openSavingWarningForm();
            }
        });

        primaryStage.show();
    }
}
