package org.comicteam.models.ballons.pointers;

import org.comicteam.layouts.ComicLayout;

import java.io.Serializable;

public abstract class BalloonPointer implements Serializable {
    protected ComicLayout layout;

    public BalloonPointer(ComicLayout layout) {
        this.layout = layout;
    }

    public ComicLayout getLayout() {
        return layout;
    }

    public void setLayout(ComicLayout layout) {
        this.layout = layout;
    }

    public int getBubblesCount() {
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BalloonPointer{");
        sb.append("layout=").append(layout);
        sb.append('}');
        return sb.toString();
    }
}
