package org.comicteam;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.comicteam.helpers.CanvasHelper;
import org.comicteam.helpers.ComicBookHelper;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.models.ComicModel;
import org.comicteam.models.ballons.Balloon;

public class FormController {
    //Balloon balloon;
    //BalloonPointer pointer;

    ComicBook book;

    @FXML
    AnchorPane pane;

    public void initialize() {
       // canvas, layout, back, text, pointer
        /*pointer = new SpeechBalloonPointer(new ComicLayout(new Position(400, 400), null));
        balloon = new SpeechBalloon(
                null,
                new ComicLayout(
                        new Position(50, 50),
                        new Size(400, 200)
                ),
                1,
                new Text(null, new ComicLayout(new Position(50, 50), null), 0, "Bonjour",new Font(20)),
                pointer
        );

        showBalloon(balloon);*/
        //canvas = new Canvas(500, 500);
        /*try {
            URL url = getClass().getResource("../../../tintin.jpg");
            Image image = new Image(url.toExternalForm());
            //canvas.getGraphicsContext2D().drawImage(image, 100, 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        ComicBook bulletin = DodoStop.getBook();
        //ComicBookHelper.saveDescriptor(bulletin);

        book = ComicBookHelper.open("Bulletin Météo.cm");

        for (ComicPanel panel : book.getPages().get(0).getPanels()) {
            Pane panePanel = CanvasHelper.getPanel(panel);
            for (ComicModel model : panel.getModels()) {
                Pane paneModel = CanvasHelper.getBalloon((Balloon) model);
                //Canvas canvasText = CanvasHelper.getText(((Balloon) model).getText());
                panePanel.getChildren().add(paneModel);
                //panePanel.getChildren().add(canvasText);
            }

            pane.getChildren().add(panePanel);
        }
    }

    /*public void showBalloon(Balloon balloon) {
        Canvas c = new Canvas(500, 500);

        c.getGraphicsContext2D().strokeOval(
                balloon.getLayout().getPosition().getHorizontal(),
                balloon.getLayout().getPosition().getVertical(),
                balloon.getLayout().getSize().getHorizontal(),
                balloon.getLayout().getSize().getVertical()
        );

        int gap = 10;

        int lcenterx = balloon.getLayout().getPosition().getHorizontal() + (balloon.getLayout().getSize().getHorizontal() / 2) - gap;
        int rcenterx = balloon.getLayout().getPosition().getHorizontal() + (balloon.getLayout().getSize().getHorizontal() / 2) + gap;
        int centery = balloon.getLayout().getPosition().getVertical() + (balloon.getLayout().getSize().getVertical() / 2) + balloon.getLayout().getSize().getVertical() / 2;

        c.getGraphicsContext2D().strokeLine(
                balloon.getPointer().getLayout().getPosition().getHorizontal(),
                balloon.getPointer().getLayout().getPosition().getVertical(),
                lcenterx,
                centery
        );

        c.getGraphicsContext2D().strokeLine(
                balloon.getPointer().getLayout().getPosition().getHorizontal(),
                balloon.getPointer().getLayout().getPosition().getVertical(),
                rcenterx,
                centery
        );

        pane.getChildren().add(c);
    }*/
}
