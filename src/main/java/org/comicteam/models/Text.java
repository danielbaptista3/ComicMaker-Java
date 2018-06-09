package org.comicteam.models;

import javafx.scene.canvas.Canvas;
import javafx.scene.text.Font;
import org.comicteam.layouts.ComicLayout;

public final class Text extends ComicModel {
    private String text;
    private Font font;

    public Text(Canvas canvas, ComicLayout layout, int background, String text, Font font) {
        super(text, canvas, layout, background);
        this.text = text;
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
