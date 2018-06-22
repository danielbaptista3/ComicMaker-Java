package org.comicteam.helpers;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import org.comicteam.controllers.EditorController;
import org.comicteam.layouts.ComicPanel;
import org.comicteam.layouts.Position;
import org.comicteam.layouts.Size;
import org.comicteam.models.ComicModel;
import org.comicteam.models.Text;
import org.comicteam.models.ballons.Balloon;

import java.util.List;

public class CanvasHelper {
    private static Border blackBorder;
    private static Border blueBorder;

    static {
        blackBorder = new Border(
                new BorderStroke(
                        Paint.valueOf("BLACK"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        BorderWidths.DEFAULT
                )
        );

        blueBorder = new Border(
                new BorderStroke(
                        Paint.valueOf("BLUE"),
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        BorderWidths.DEFAULT
                )
        );
    }

    public static Pane getPanel(ComicPanel panel) {
        Pane pane = new Pane();
        pane.setPrefWidth(MM.toPx(panel.getLayout().getSize().getHorizontal()));
        pane.setPrefHeight(MM.toPx(panel.getLayout().getSize().getVertical()));
        pane.setLayoutX(MM.toPx(panel.getLayout().getPosition().getHorizontal()));
        pane.setLayoutY(MM.toPx(panel.getLayout().getPosition().getVertical()));
        pane.setBorder(blackBorder);

        return pane;
    }

    public static void selectPanel(List<Node> nodes, Pane panel) {
        unselectAllPanels(nodes);
        panel.setBorder(blueBorder);
    }

    public static void unselectAllPanels(List<Node> nodes) {
        for (Node p : nodes) {
            //((Pane) p).setBorder(blackBorder);
        }
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

    public static void movePanel(Node node, ComicPanel panel, int x, int y) {
        node.setLayoutX(x);
        node.setLayoutY(y);

        panel.getLayout().setPosition(
                new Position(
                        MM.toMM(x),
                        MM.toMM(y)
                )
        );
    }

/*    public static void moveModel(Node node, ComicModel model, int x, int y) {
        node.setLayoutX(x);
        node.setLayoutY(y);

        model.getLayout().setPosition(
                new Position(
                        MM.toMM(x),
                        MM.toMM(y)
                )
        );
    }*/

    public static void resizePanel(Node node, ComicPanel panel, int x, int y) {
        ((Pane) node).setPrefWidth(x);
        ((Pane) node).setPrefHeight(y);

        panel.getLayout().setSize(
                new Size(
                        MM.toMM(x),
                        MM.toMM(y)
                )
        );
    }

    /*public static void resizeModel(Node node, ComicModel model, int x, int y) {
        ((Pane) node).setPrefWidth(x);
        ((Pane) node).setPrefHeight(y);

        model.getLayout().setSize(
                new Size(
                        MM.toMM(x),
                        MM.toMM(y)
                )
        );
    }*/
}