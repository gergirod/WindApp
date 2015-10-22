package com.example.germangirod.rxjavaexample.data.model;

import java.util.List;

/**
 * Created by germangirod on 5/15/15.
 */
public class Forecast {

    public int cod;
    public Double message;
    public City city;
    public int cnt;
    public List<WeatherResponse> list;

    public int getCod() {
        return cod;
    }

    public Double getMessage() {
        return message;
    }

    public City getCity() {
        return city;
    }

    public int getCnt() {
        return cnt;
    }

    public List<WeatherResponse> getWeatherResponses() {
        return list;
    }
}
