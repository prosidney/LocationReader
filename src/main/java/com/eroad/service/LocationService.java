package com.eroad.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Sidney on 15-03-09.
 */
public class LocationService {

    public static String GOOGLE_KEY = "AIzaSyDyvmXO-2zIUbnDqfrBrMXxOUJU8QZgaTQ";

    public Map<String, Object> readUtf(String longi, String lat) throws IOException {
        URL obj = new URL("https://maps.googleapis.com/maps/api/timezone/json?location="+longi+","+lat+"&timestamp=1331766000&language=en&key="+GOOGLE_KEY);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return new Gson().fromJson(response.toString(), type);
    }
}
