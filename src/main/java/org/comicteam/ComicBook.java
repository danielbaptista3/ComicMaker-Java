package org.comicteam;

import org.comicteam.layouts.ComicPage;
import org.comicteam.layouts.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComicBook implements Serializable {
    private String name;
    private String serie;
    private List<String> authors;
    private String description;
    private Size size;
    private List<ComicPage> pages;

    public ComicBook(String name, String serie, List<String> authors, String description, Size size, List<ComicPage> pages) {
        this(name, serie, authors, description, size);
        this.pages = pages;
    }

    public ComicBook(String name, String serie, List<String> authors, String description, Size size) {
        this.name = name;
        this.serie = serie;
        this.authors = authors;
        this.description = description;
        this.size = size;
        pages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ComicPage> getPages() {
        return pages;
    }

    public void setPages(List<ComicPage> pages) {
        this.pages = pages;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicBook{");
        sb.append("name='").append(name).append('\'');
        sb.append(", serie='").append(serie).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", description='").append(description).append('\'');
        sb.append(", size=").append(size);
        sb.append(", pages=").append(pages);
        sb.append('}');
        return sb.toString();
    }
}
