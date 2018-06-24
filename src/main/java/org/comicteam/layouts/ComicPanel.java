package org.comicteam.layouts;

import org.comicteam.models.ComicModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComicPanel implements Serializable, Comparable {
    private List<ComicModel> models;
    private ComicLayout layout;

    public ComicPanel(List<ComicModel> models, ComicLayout layout) {
        this(layout);
        this.models = models;
    }

    public ComicPanel(ComicLayout layout) {
        this.layout = layout;
        models = new ArrayList<>();
    }

    public List<ComicModel> getModels() {
        return models;
    }

    public void setModels(List<ComicModel> models) {
        this.models = models;
    }

    public ComicLayout getLayout() {
        return layout;
    }

    public void setLayout(ComicLayout layout) {
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "Panel";
    }

    @Override
    public int compareTo(Object o) {
        if (layout.getPosition().getVertical() < ((ComicPanel) o).layout.getPosition().getVertical()) {
            //if (layout.getPosition().getHorizontal() < ((ComicPanel) o).layout.getPosition().getHorizontal()) {
                return -1;
            /*} else {
                return 1;
            }*/
        }

        return 1;
    }
}
