package org.comicteam.helpers;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClipartHelper {
    public static void getCanvas(String keyword) {
        try {
            URL url = new URL("http://openclipart.org/search/json/?query=eiffel");
            try {
                URLConnection con = url.openConnection();

                con.connect();
                Scanner scanner = new Scanner(con.getInputStream());

                while (scanner.hasNext()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}