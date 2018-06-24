package org.comicteam;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

public class CMFile {
    private String json;
    private List<Canvas> canvas;

    public CMFile() {
        canvas = new ArrayList<>();
    }

    public void setJSON(String json) {
        this.json = json;
    }

    public void addCanvas(Canvas canvas) {
        this.canvas.add(canvas);
    }

    public boolean save(String folder) {
        if (!json.equals("")) {

        }

        return false;
    }
}
