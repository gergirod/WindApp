package com.example.germangirod.rxjavaexample.api.model;

/**
 * Created by germangirod on 5/15/15.
 */
public class City {

    public int id;
    public String name;
    public Coords coords;
    public String country;
    public int population;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coords getCoords() {
        return coords;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }
}
