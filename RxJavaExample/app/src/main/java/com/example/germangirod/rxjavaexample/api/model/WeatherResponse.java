package com.example.germangirod.rxjavaexample.api.model;

import java.util.List;

/**
 * Created by germangirod on 5/1/15.
 */
public class WeatherResponse {

    public Coords coords;
    public Sys sys;
    public List<Weather> weather;
    public Main main;
    public Wind wind;
    public Rain rain;
    public Clouds clouds;
    public long dt;
    public int id;
    public String name;
    public int cod;
    public String dt_txt;

    public Coords getCoords() {
        return coords;
    }

    public Sys getSys() {
        return sys;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public long getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getDt_txt() {
        return dt_txt;
    }
}
