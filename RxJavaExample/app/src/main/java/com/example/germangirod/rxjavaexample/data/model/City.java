package com.example.germangirod.rxjavaexample.data.model;

import org.parceler.Parcel;

/**
 * Created by germangirod on 5/15/15.
 */
@Parcel
public class City {

    public int id;
    public String name;
    public Coords coords;
    public String country;
    public int population;

    public City(){

    }

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
