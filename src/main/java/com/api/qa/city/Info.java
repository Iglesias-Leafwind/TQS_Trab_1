package com.api.qa.city;

public class Info {

    public int getOverallQuality() {
        return overallQuality;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWind() {
        return wind;
    }

    public boolean getError(){ return error; }
    private final int overallQuality;
    private final String name;
    private final String time;
    private final double humidity;
    private final double pressure;
    private final double temperature;
    private final double wind;
    private final boolean error;

    public Info(String name,String time,int quality,double humidity,double pressure,double temperature,double wind){
        this.name = name;
        this.time = time;
        this.overallQuality = quality;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        this.wind = wind;
        this.error = false;
    }
    public Info(){
        this.name = "Error: No city Found";
        this.time = "";
        this.overallQuality = 0;
        this.humidity = 0;
        this.pressure = 0;
        this.temperature = 0;
        this.wind = 0;
        this.error = true;
    }
}