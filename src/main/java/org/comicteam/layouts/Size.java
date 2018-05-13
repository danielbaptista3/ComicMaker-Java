package org.comicteam.layouts;

import java.io.Serializable;

public class Size implements Serializable {
    private int horizontal;
    private int vertical;

    public Size(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Size{");
        sb.append("horizontal=").append(horizontal);
        sb.append(", vertical=").append(vertical);
        sb.append('}');
        return sb.toString();
    }
}
