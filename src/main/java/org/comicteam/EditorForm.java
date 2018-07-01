package org.comicteam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.comicteam.controllers.EditorController;
import org.comicteam.controllers.SavingWarningController;
import org.comicteam.controllers.WorkingController;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.helpers.FXMLHelper;
import org.comicteam.helpers.MM;

import java.io.IOException;

public class EditorForm extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("fxml/editor.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene  = new Scene(
                root,
                MM.toPx(CMFile.cmfile.book.getSize().getHorizontal()),
                MM.toPx(CMFile.cmfile.book.getSize().getVertical())
        );

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(CMFile.cmfile.book.getName());
        primaryStage.setX(400);

        primaryStage.setOnCloseRequest((e) -> {
            if (!CMFile.cmfile.saved) {
                FXMLHelper.openSavingWarningForm();

                if (SavingWarningController.mustCancel) {
                    e.consume();
                }
            } else {
                FXMLHelper.closeWindow(WorkingController.controller.pane);
            }
        });

        primaryStage.show();
    }
}