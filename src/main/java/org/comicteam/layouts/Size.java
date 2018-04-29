package org.comicteam.layouts;

public class Size {
    private int horizontal;
    private int vertical;

    public Size(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return this.horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return this.vertical;
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
