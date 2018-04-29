package org.comicteam;

import org.comicteam.layouts.ComicPage;

import java.util.ArrayList;
import java.util.List;

public class ComicBook {
    private String name;
    private String serie;
    private List<String> authors;
    private String description;
    private List<ComicPage> pages;

    public ComicBook(String name, String serie, List<String> authors, String description, List<ComicPage> pages) {
        this(name, serie, authors, description);
        this.pages = pages;
    }

    public ComicBook(String name, String serie, List<String> authors, String description) {
        this.name = name;
        this.serie = serie;
        this.authors = authors;
        this.description = description;
        pages = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerie() {
        return this.serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ComicPage> getPages() {
        return this.pages;
    }

    public void setPages(List<ComicPage> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicBook{");
        sb.append("name='").append(name).append('\'');
        sb.append(", serie='").append(serie).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", description='").append(description).append('\'');
        sb.append(", pages=").append(pages);
        sb.append('}');
        return sb.toString();
    }
}
