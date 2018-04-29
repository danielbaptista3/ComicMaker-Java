package org.comicteam.layouts;

import org.comicteam.models.ComicModel;

import java.util.List;

public class ComicSplash extends ComicPanel {

    public ComicSplash(List<ComicModel> models, ComicLayout layout) {
        super(models, layout);
    }

    public ComicSplash(ComicLayout layout) {
        super(layout);
    }
}
