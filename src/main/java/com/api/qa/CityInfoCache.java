package com.api.qa;

import org.json.JSONException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CityInfoCache {
    private HashMap<String, Info> city_airInfo;
    private int max_size;
    public CityInfoCache(int cacheSize){
        city_airInfo = new LinkedHashMap<String, Info>();
        this.max_size = cacheSize;
    }

    public boolean check(String city){
        return city_airInfo.containsKey(city);
    }
    public boolean insertInfo(String city) throws IOException, JSONException {
        if(check(city)){
            return false;
        }else{
            if(city_airInfo.size() == max_size){
                city_airInfo.remove(city_airInfo.keySet().iterator().next());
            }
            city_airInfo.put(city, JsonReader.convertJsonInfo(city));
            return true;
        }
    }
    public void insert(Info city){
        if(check(city.getName())){
        }else{
            if(city_airInfo.size() == max_size){
                city_airInfo.remove(city_airInfo.keySet().iterator().next());
            }
            city_airInfo.put(city.getName(), city);
        }
    }
    public Info getInfo(String city){
        if(check(city)){
            return city_airInfo.get(city);
        }else{
            return null;
        }
    }
    public void clear(){
        city_airInfo.clear();
    }
}