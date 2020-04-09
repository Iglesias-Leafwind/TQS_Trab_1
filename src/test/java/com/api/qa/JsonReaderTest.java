package com.api.qa;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.api.qa.city.JsonReader.readJsonFromUrl;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JsonReaderTest {

    @Test
    void getCorrectinfo() throws IOException, JSONException {
        String name = "Vila Nova de Gaia";
        JSONObject json = readJsonFromUrl("https://api.waqi.info/feed/" + name + "/?token=3de72aaf07dae32b6339922dffb3c386144bf3c5");
        assertThat(json.get("status")).isEqualTo("ok");
    }
    @Test
    void getWronginfo() throws IOException, JSONException {
        String name = "KYSPLZ";
        JSONObject json = readJsonFromUrl("https://api.waqi.info/feed/" + name + "/?token=3de72aaf07dae32b6339922dffb3c386144bf3c5");
        assertThat(json.get("status")).isEqualTo("error");
    }
    @Test
    void checkInfo() throws IOException, JSONException {
        String name = "Vila Nova de Gaia";
        JSONObject json = readJsonFromUrl("https://api.waqi.info/feed/" + name + "/?token=3de72aaf07dae32b6339922dffb3c386144bf3c5");
        json = json.getJSONObject("data");
        JSONObject jsoninfo = json.getJSONObject("iaqi");
        JSONObject jsoncity = json.getJSONObject("city");
        JSONObject jsontime = json.getJSONObject("time");
        assertThat(json.getInt("aqi")).isEqualTo(json.getInt("aqi"));
        assertThat(jsontime.getString("s")).isEqualTo(jsontime.getString("s"));
        assertThat(jsoncity.getString("name")).isEqualTo("Sobreiras-Lordelo do Ouro, Porto, Portugal");
        assertThat(jsoninfo.getJSONObject("p").getLong("v")).isEqualTo(jsoninfo.getJSONObject("p").getLong("v"));
        assertThat(jsoninfo.getJSONObject("t").getLong("v")).isEqualTo(jsoninfo.getJSONObject("t").getLong("v"));
        assertThat(jsoninfo.getJSONObject("w").getLong("v")).isEqualTo(jsoninfo.getJSONObject("w").getLong("v"));
        assertThat(jsoninfo.getJSONObject("h").getLong("v")).isEqualTo(jsoninfo.getJSONObject("h").getLong("v"));
    }
}
