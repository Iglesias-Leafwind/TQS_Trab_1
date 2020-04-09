package com.api.qa;

import org.json.JSONException;

import java.io.IOException;

public class API {
    private static CityInfoCache cache;
    public static Info search(String name) {
        return cache.getInfo(name);
    }
    public static void cacheInfo(String name) throws IOException, JSONException {
        cache.insertInfo(name);
    }
    public API(){
        this.cache = new CityInfoCache(5);
    }

}
