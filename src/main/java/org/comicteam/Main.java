package org.comicteam;

import org.comicteam.helpers.ComicBookHelper;

@interface VeryGood {

}

public class Main {
    public static void main(String[] args) {
        ComicBook bulletin = DodoStop.getBook();
        ComicBookHelper.save(bulletin);
    }
}