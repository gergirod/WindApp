package com.example.germangirod.rxjavaexample.api.model;

import java.util.List;

/**
 * Created by germangirod on 5/15/15.
 */
public class Forecast {

    private int cod;
    private Double message;
    private City city;
    private int cnt;
    private List<WeatherResponse> list;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherResponse> getWeatherResponses() {
        return list;
    }

    public void setWeatherResponses(List<WeatherResponse> weatherResponses) {
        this.list = weatherResponses;
    }
}
