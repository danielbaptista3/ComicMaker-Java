package org.comicteam.helpers;

import javafx.scene.text.Font;
import org.comicteam.ComicBook;
import org.comicteam.layouts.*;
import org.comicteam.models.ComicModel;
import org.comicteam.models.Text;
import org.comicteam.models.ballons.Balloon;
import org.comicteam.models.ballons.pointers.BalloonPointer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONifyHelper {
    public static JSONObject jsonBook(ComicBook book) {
        JSONObject o = new JSONObject();
        o.put("name", book.getName());
        o.put("serie", book.getSerie());

        JSONArray authors = new JSONArray();
        for (String author : book.getAuthors()) {
            authors.add(author);
        }
        o.put("authors", authors);
        o.put("description", book.getDescription());
        o.put("size", jsonSize(book.getSize()));

        JSONArray pages = new JSONArray();
        for (ComicPage page : book.getPages()) {
            pages.add(jsonPage(page));
        }
        o.put("pages", pages);

        return o;
    }

    private static JSONObject jsonPage(ComicPage page) {
        JSONObject o = new JSONObject();
        o.put("index", page.getIndex());
        JSONArray panels = new JSONArray();

        for (ComicPanel panel : page.getPanels()) {
            panels.add(jsonPanel(panel));
        }
        o.put("panels", panels);

        return o;
    }

    private static JSONObject jsonPanel(ComicPanel panel) {
        JSONObject o = new JSONObject();
        o.put("Layout", jsonLayout(panel.getLayout()));

        JSONArray models = new JSONArray();
        for (ComicModel model : panel.getModels()) {
            JSONObject omodel = new JSONObject();
            if (model.getClass().getSimpleName().contains("Balloon")
                    || model.getClass().getSimpleName().equals("Caption")) {
                omodel.put(model.getClass().getSimpleName(), jsonBalloon(model));
            } else {
                omodel.put(model.getClass().getSimpleName(), jsonModel(model));
            }
            models.add(omodel);
        }
        o.put("models", models);

        return o;
    }

    private static JSONObject jsonModel(ComicModel model) {
        JSONObject o = new JSONObject();

        o.put("name", model.getName());
        if (model.getLayout() != null) {
            o.put("Layout", jsonLayout(model.getLayout()));
        }
        o.put("background", model.getBackground());
        if (model.getCanvas() != null) {
            o.put("Canvas", model.getCanvas().getGraphicsContext2D());
        }

        return o;
    }

    private static JSONObject jsonBalloon(ComicModel model) {
        JSONObject o = jsonModel(model);

        Balloon balloon = (Balloon) model;
        o.put("Text", jsonText(balloon.getText()));

        if (balloon.getPointer() == null) {
            return o;
        }

        switch (balloon.getPointer().getClass().getSimpleName()) {
            case "SpeechBalloonPointer":
                o.put("SpeechBalloonPointer", jsonSpeechBalloonPointer(balloon.getPointer()));
                break;
            case "ThoughtBalloonPointer":
                o.put("ThoughtBalloonPointer", jsonThoughtBalloonPointer(balloon.getPointer()));
                break;
        }

        return o;
    }

    private static JSONObject jsonSpeechBalloonPointer(BalloonPointer pointer) {
        JSONObject o = new JSONObject();
        o.put("Layout", jsonLayout(pointer.getLayout()));

        return o;
    }

    private static JSONObject jsonThoughtBalloonPointer(BalloonPointer pointer) {
        JSONObject o = new JSONObject();
        o.put("bubblesCount", pointer.getBubblesCount());
        o.put("Layout", jsonLayout(pointer.getLayout()));

        return o;
    }

    private static JSONObject jsonLayout(ComicLayout layout) {
        JSONObject o = new JSONObject();
        if (layout.getPosition() != null) {
            o.put("Position", jsonPosition(layout.getPosition()));
        }
        if (layout.getSize() != null) {
            o.put("Size", jsonSize(layout.getSize()));
        }

        return o;
    }

    private static JSONObject jsonPosition(Position position) {
        JSONObject o = new JSONObject();
        o.put("horizontal", position.getHorizontal());
        o.put("vertical", position.getVertical());

        return o;
    }

    private static JSONObject jsonSize(Size size) {
        JSONObject o = new JSONObject();
        o.put("horizontal", size.getHorizontal());
        o.put("vertical", size.getVertical());

        return o;
    }

    private static JSONObject jsonText(Text text) {
        JSONObject o = jsonModel(text);
        o.put("String", text.getText());
        o.put("Font", jsonFont(text.getFont()));

        return o;
    }

    private static JSONObject jsonFont(Font font) {
        JSONObject o = new JSONObject();
        o.put("size", font.getSize());
        o.put("name", font.getName());

        return o;
    }
}
