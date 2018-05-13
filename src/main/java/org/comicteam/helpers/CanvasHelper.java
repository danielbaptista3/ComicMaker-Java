package org.comicteam.helpers;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.models.Text;
import org.comicteam.models.ballons.Balloon;

public class CanvasHelper {
    public static Pane getPanel(ComicPanel panel) {
        Pane pane = new Pane();
        pane.setPrefWidth(MM.toPx(panel.getLayout().getSize().getHorizontal()));
        pane.setPrefHeight(MM.toPx(panel.getLayout().getSize().getVertical()));
        pane.setLayoutX(MM.toPx(panel.getLayout().getPosition().getHorizontal()));
        pane.setLayoutY(MM.toPx(panel.getLayout().getPosition().getVertical()));

        pane.setBorder(new Border(
                new BorderStroke(
                        Paint.valueOf("BLACK"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        BorderWidths.DEFAULT
                )
        ));

        return pane;
    }

    public static Pane getBalloon(Balloon balloon) {
        switch (balloon.getClass().getSimpleName()) {
            case "SpeechBalloon":
                return getSpeechBalloon(balloon);
            /*case "ThoughtBalloon":
                return getThoughtBalloon(balloon);*/
            case "Caption":
                return getCaption(balloon);
            default:
                return null;
        }
    }

    public static Pane getSpeechBalloon(Balloon balloon) {
        Pane pane  = new Pane();
        pane.setLayoutX(balloon.getLayout().getPosition().getHorizontal());
        pane.setLayoutY(balloon.getLayout().getPosition().getVertical());
        pane.setPrefWidth(balloon.getLayout().getSize().getHorizontal());
        pane.setPrefHeight(balloon.getLayout().getSize().getVertical());

        Canvas canvas = new Canvas();

        canvas.getGraphicsContext2D().strokeOval(
                MM.toPx(balloon.getLayout().getPosition().getHorizontal()),
                MM.toPx(balloon.getLayout().getPosition().getVertical()),
                MM.toPx(balloon.getLayout().getSize().getHorizontal()),
                MM.toPx(balloon.getLayout().getSize().getVertical())
        );

        pane.getChildren().add(canvas);
        pane.getChildren().add(getText(balloon.getText()));
        /*canvas.getGraphicsContext2D().strokeText(
                balloon.getText().getText(),
                MM.toPx(balloon.getLayout().getPosition().getHorizontal()),
                MM.toPx(balloon.getLayout().getPosition().getVertical())
        );*/

        return pane;
    }

    public static Canvas getThoughtBalloon(Balloon balloon) {
        Canvas canvas = new Canvas(
                MM.toPx(balloon.getLayout().getSize().getHorizontal()),
                MM.toPx(balloon.getLayout().getSize().getVertical())
        );

        return canvas;
    }

    public static Pane getCaption(Balloon balloon) {
        Pane paneCaption = new Pane();
        paneCaption.setLayoutX(MM.toPx(balloon.getLayout().getPosition().getHorizontal()));
        paneCaption.setLayoutY(MM.toPx(balloon.getLayout().getPosition().getVertical()));
        paneCaption.setPrefWidth(MM.toPx(balloon.getLayout().getSize().getHorizontal() + 5));
        paneCaption.setPrefHeight(MM.toPx(balloon.getLayout().getSize().getVertical() + 5));

        paneCaption.getChildren().add(getText(balloon.getText()));

        /*canvas.getGraphicsContext2D().strokeRect(
                MM.toPx(balloon.getLayout().getPosition().getHorizontal()),
                MM.toPx(balloon.getLayout().getPosition().getVertical()),
                MM.toPx(balloon.getLayout().getSize().getHorizontal()),
                MM.toPx(balloon.getLayout().getSize().getVertical())
        );*/

        /*canvas.getGraphicsContext2D().strokeText(
                balloon.getText().getText(),
                MM.toPx(balloon.getLayout().getPosition().getHorizontal()),
                MM.toPx(balloon.getLayout().getPosition().getVertical() + 4)
        );

        canvas.getGraphicsContext2D().*/

        return paneCaption;
    }

    public static Canvas getText(Text text) {
        Canvas canvas = new Canvas();

        canvas.getGraphicsContext2D().setFont(text.getFont());
        canvas.getGraphicsContext2D().strokeText(
                text.getText(),
                0,
                0
        );

        return canvas;
    }
}