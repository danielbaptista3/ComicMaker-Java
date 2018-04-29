package org.comicteam.layouts;

import java.util.ArrayList;
import java.util.List;

public class ComicPage {
    private List<ComicTier> tiers;
    private int index;

    public ComicPage(int index) {
        this.index = index;
        tiers = new ArrayList<>();
    }

    public ComicPage(List<ComicTier> tiers, int index) {
        this(index);
        this.tiers = tiers;
    }

    public List<ComicTier> getTiers() {
        return this.tiers;
    }

    public void setTiers(List<ComicTier> tiers) {
        this.tiers = tiers;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicPage{");
        sb.append("tiers=").append(tiers);
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }
}
