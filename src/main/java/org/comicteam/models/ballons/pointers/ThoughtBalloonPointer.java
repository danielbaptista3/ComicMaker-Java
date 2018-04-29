package org.comicteam.models.ballons.pointers;

import org.comicteam.layouts.ComicLayout;

public final class ThoughtBalloonPointer extends BalloonPointer {
    private int bubblesCount;

    public ThoughtBalloonPointer(ComicLayout layout, int bubblesCount) {
        super(layout);
        this.bubblesCount = bubblesCount;
    }

    @Override
    public int getBubblesCount() {
        return bubblesCount;
    }

    public void setBubblesCount(int bubblesCount) {
        this.bubblesCount = bubblesCount;
    }
}
