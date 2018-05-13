package org.comicteam;

import javafx.scene.text.Font;
import org.comicteam.layouts.*;
import org.comicteam.models.Caption;
import org.comicteam.models.ComicModel;
import org.comicteam.models.Text;
import org.comicteam.models.ballons.Balloon;
import org.comicteam.models.ballons.SpeechBalloon;
import org.comicteam.models.ballons.pointers.SpeechBalloonPointer;

import java.util.ArrayList;
import java.util.List;

public class DodoStop {
    private static final String name = "Bulletin Météo";
    private static final String serie = "Les Profs";
    private static final String strAuthors = "Pica;Mauricet;Erroc";
    private static final String description = "La vie des profs haut en couleur";
    private static final Size size = new Size(150, 150);
    private static List<String> authors;

    static {
        authors = new ArrayList<>();
        for (String author : strAuthors.split(";")) {
            authors.add(author);
        }
    }

    public static ComicBook getBook() {
        List<ComicPage> pages = new ArrayList<>();
        pages.add(getPage());

        return new ComicBook(
                name,
                serie,
                authors,
                description,
                size,
                pages
        );
    }

    public static ComicPage getPage() {
        return new ComicPage(  1, getPanels());
    }

    public static List<ComicPanel> getPanels() {
        List<ComicPanel> panels = new ArrayList<>();
        panels.add(getPanel1());
        panels.add(getPanel2());
        panels.add(getPanel3());

        return panels;
    }

    public static ComicPanel getPanel1() {
        ComicPanel panel = new ComicPanel(
                new ComicLayout(
                        new Position(5, 5),
                        new Size(40, 40)
                )
        );

        return panel;
    }

    private static ComicPanel getPanel2() {
        return new ComicPanel(
                new ComicLayout(
                        new Position(50, 5),
                        new Size(40, 40)
                )
        );
    }

    public static ComicPanel getPanel3() {
        List<ComicModel> models = new ArrayList<>();
        models.add(getCaption());
        models.add(getSpeech());

        return new ComicPanel(
                models,
                new ComicLayout(
                        new Position(5, 50),
                        new Size(100,40)
                )
        );
    }

    public static Balloon getCaption() {
        return new Caption(
                null,
                new ComicLayout(
                        new Position(0, 0),
                        new Size(85, 5)
                ),
                0,
                new Text(
                        null,
                        null,
                        0,
                        "Comme beaucoup de profs, vous êtes insomniaque...",
                        new Font("Arial", 10)
                ),
                null
        );
    }

    public static Balloon getSpeech() {
        return new SpeechBalloon(
                null,
                new ComicLayout(
                        new Position(50, 30),
                        new Size(30, 30)
                ),
                0,
                new Text(
                        null,
                        null,
                        0,
                        "Ron\nZZZ...",
                        new Font( "Arial", 10)
                ),
                new SpeechBalloonPointer(
                        new ComicLayout(
                                new Position(30, 70),
                                null
                        )
                )
        );
    }
}