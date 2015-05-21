package com.example.germangirod.rxjavaexample.api.model;

import java.util.List;

/**
 * Created by germangirod on 5/1/15.
 */
public class WeatherResponse {

    private Coords coords;
    private Sys sys;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Rain rain;
    private List<Clouds> clouds;
    private int dt;
    private int id;
    private String name;
    private int cod;

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public List<Clouds> getClouds() {
        return clouds;
    }

    public void setClouds(List<Clouds> clouds) {
        this.clouds = clouds;
    }
}
