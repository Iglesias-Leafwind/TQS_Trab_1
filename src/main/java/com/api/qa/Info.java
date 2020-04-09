package com.api.qa;

public class Info {

    public int getOverall_quality() {
        return overall_quality;
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

    final private int overall_quality;
    final private String name;
    final private String time;
    final private double humidity;
    final private double pressure;
    final private double temperature;
    final private double wind;
    public Info(String name,String time,int quality,double humidity,double pressure,double temperature,double wind){
        this.name = name;
        this.time = time;
        this.overall_quality = quality;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        this.wind = wind;
    }
}