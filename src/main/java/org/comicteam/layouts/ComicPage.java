package org.comicteam.layouts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComicPage implements Serializable {
    private int index;
    private List<ComicPanel> panels;

    public ComicPage(int index) {
        this.index = index;
        panels = new ArrayList<>();
    }

    public ComicPage(int index, List<ComicPanel> panels) {
        this(index);
        this.panels = panels;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<ComicPanel> getPanels() {
        return panels;
    }

    public void setPanels(List<ComicPanel> panels) {
        this.panels = panels;
    }

    @Override
    public String toString() {
        return String.format("Page %s", index);
    }
}
