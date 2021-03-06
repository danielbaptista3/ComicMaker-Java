package org.comicteam.models;

import javafx.scene.canvas.Canvas;
import org.comicteam.layouts.ComicLayout;

public class ComicModel {
    protected String name;
    protected Canvas canvas;
    protected ComicLayout layout;
    protected int background;

    public ComicModel(String name, Canvas canvas, ComicLayout layout, int background) {
        this.name = name;
        this.canvas = canvas;
        this.layout = layout;
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public ComicLayout getLayout() {
        return layout;
    }

    public void setLayout(ComicLayout layout) {
        this.layout = layout;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    @Override
    public String toString() {
        return name;
    }
}
