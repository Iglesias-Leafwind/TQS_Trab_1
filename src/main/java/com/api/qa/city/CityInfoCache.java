package com.api.qa.city;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CityInfoCache {
    private HashMap<String, Info> cityAirInfo;
    private int maxSize;
    public CityInfoCache(int cacheSize){
        cityAirInfo = new LinkedHashMap<>();
        this.maxSize = cacheSize;
    }

    public boolean check(String city){
        return cityAirInfo.containsKey(city);
    }
    public boolean insertInfo(String city) throws IOException, JSONException {
        if(check(city)){
            return false;
        }else{
            if(cityAirInfo.size() == maxSize){
                cityAirInfo.remove(cityAirInfo.keySet().iterator().next());
            }
            cityAirInfo.put(city, JsonReader.convertJsonInfo(city));
            return true;
        }
    }
    public void insert(String cityName, Info city){
        if(!check(cityName)){
            if(cityAirInfo.size() == maxSize){
                cityAirInfo.remove(cityAirInfo.keySet().iterator().next());
            }
            cityAirInfo.put(cityName, city);
        }
    }
    public Info getInfo(String city){
        if(check(city)){
            return cityAirInfo.get(city);
        }else{
            return new Info();
        }
    }
    public void clear(){
        cityAirInfo.clear();
    }
}