package com.kys.qa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    public static Info convertJsonInfo(String city_name) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://api.waqi.info/feed/" + city_name + "/?token=3de72aaf07dae32b6339922dffb3c386144bf3c5");
        json = json.getJSONObject("data");
        JSONObject jsoninfo = json.getJSONObject("iaqi");
        JSONObject jsoncity = json.getJSONObject("city");
        JSONObject jsontime = json.getJSONObject("time");
        String time = jsontime.getString("s");
        String name = jsoncity.getString("name");
        int quality = json.getInt("aqi");
        double pressure = jsoninfo.getJSONObject("p").getDouble("v");
        double temperature = jsoninfo.getJSONObject("t").getDouble("v");
        double wind = jsoninfo.getJSONObject("w").getDouble("v");
        double humidity = jsoninfo.getJSONObject("h").getDouble("v");
        return new Info(name,time,quality,humidity,pressure,temperature,wind);
    }
}