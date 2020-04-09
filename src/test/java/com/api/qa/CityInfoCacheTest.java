package com.api.qa;

import com.api.qa.city.CityInfoCache;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CityInfoCacheTest {
    static String porto;
    static String lisboa;
    static String londres;
    static String londresING;
    static String THISISAMERICA;
    static String brasil;
    static CityInfoCache cache;
    @BeforeAll
    public static void setUp() {
        porto = "Porto";
        lisboa = "Lisboa";
        londres = "Londres";
        londresING = "London";
        THISISAMERICA = "washington";
        brasil = "brasil";
        cache = new CityInfoCache(5);
    }
    @AfterEach
    public void clear(){
        cache.clear();
    }
    @Test
    void insertOnEmpty() throws IOException, JSONException {
        assertThat(cache.insertInfo(porto)).isEqualTo(true);
    }
    @Test
    void insertOnSameInserted() throws IOException, JSONException{
        cache.insertInfo(porto);
        assertThat(cache.insertInfo(porto)).isEqualTo(false);
    }
    @Test
    void insertOnFull() throws IOException, JSONException{
        cache.insertInfo(porto);
        cache.insertInfo(lisboa);
        cache.insertInfo(londres);
        cache.insertInfo(THISISAMERICA);
        cache.insertInfo(brasil);
        cache.insertInfo(londresING);
        assertThat(cache.check(porto)).isEqualTo(false);
    }
    @Test
    void getNone(){
        assertThat(cache.getInfo("").getError()).isEqualTo(true);
    }
    @Test
    void getSomething() throws IOException, JSONException{
        cache.insertInfo(porto);
        assertThat(cache.getInfo(porto)).isNotEqualTo(false);
    }
    @Test
    void getOnEmpty(){
        assertThat(cache.getInfo(porto).getError()).isEqualTo(true);
    }
    @Test
    void checkOnEmpty(){
        assertThat(cache.check(porto)).isEqualTo(false);
    }
    @Test
    void checkSomething() throws IOException, JSONException{
        cache.insertInfo(porto);
        assertThat(cache.check(porto)).isEqualTo(true);
    }

}