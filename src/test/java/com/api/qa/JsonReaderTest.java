package com.api.qa;

import com.api.qa.city.Info;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.api.qa.city.JsonReader.convertJsonInfo;
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
        Info exists = convertJsonInfo(name);
        Info notExists = convertJsonInfo("");
        assertThat(exists.getError()).isEqualTo(false);
        assertThat(notExists.getError()).isEqualTo(true);
    }
}
