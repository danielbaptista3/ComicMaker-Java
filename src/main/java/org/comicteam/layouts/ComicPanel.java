package org.comicteam.layouts;

import org.comicteam.models.ComicModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComicPanel implements Serializable {
    protected List<ComicModel> models;
    protected ComicLayout layout;

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
        final StringBuilder sb = new StringBuilder("ComicPanel{");
        sb.append("models=").append(models);
        sb.append(", layout=").append(layout);
        sb.append('}');
        return sb.toString();
    }
}
