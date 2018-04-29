package org.comicteam.layouts;

import java.util.ArrayList;
import java.util.List;

public class ComicTier {
    private List<ComicPanel> panels;

    public ComicTier(List<ComicPanel> panels) {
        this.panels = panels;
    }

    public ComicTier() {
        panels = new ArrayList<>();
    }

    public List<ComicPanel> getPanels() {
        return this.panels;
    }

    public void setPanels(List<ComicPanel> panels) {
        this.panels = panels;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicTier{");
        sb.append("panels=").append(panels);
        sb.append('}');
        return sb.toString();
    }
}
