package com.api.qa.city;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.api.qa.city.JsonReader.convertJsonInfo;

@Service
public class API {
    private static CityInfoCache cache = new CityInfoCache(5);

    private API(){}
    public static Info search(String name) throws IOException, JSONException {
        if (cache.check(name)){
            return cache.getInfo(name);
        }
        if (convertJsonInfo(name).getError()){
            return convertJsonInfo(name);
        }
        cacheInfo(name);
        return cache.getInfo(name);
    }
    public static void cacheInfo(String name) throws IOException, JSONException {
        Info cityInfo = convertJsonInfo(name);
        cache.insert(name, cityInfo);
    }

}
