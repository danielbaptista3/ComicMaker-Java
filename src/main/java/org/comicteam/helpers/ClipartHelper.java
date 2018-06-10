package org.comicteam.helpers;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ClipartHelper {
    private static final String CLIPART_URL = "http://openclipart.org/search/json";
    private static JSONObject requestObject;

    private static void request(String keyword, int page) {
        try {
            String url = String.format("%s/?query=%s&amount=4&page=%s", CLIPART_URL, keyword, page);

            requestObject = Unirest.get(url).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static int getPagesCount() {
        return requestObject.getJSONObject("info").getInt();
    }

    public static Image getImage(String keyword, int page, int index) {
        JSONArray images = request(keyword, page);
        JSONObject jsonImage = images.getJSONObject(index);

        //int width = jsonImage.getJSONObject("dimensions").getJSONObject("png_full_lossy").getInt("width");
        //int height = jsonImage.getJSONObject("dimensions").getJSONObject("png_full_lossy").getInt("height");

        String url = jsonImage.getJSONObject("svg").getString("png_thumb");

        try (InputStream in = new URL(url).openStream()) {
            return new Image(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}