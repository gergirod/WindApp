package com.example.germangirod.rxjavaexample.data.model;

import org.parceler.Parcel;

/**
 * Created by germangirod on 5/13/15.
 */
@Parcel
public class Coords {

    public Double lon;
    public Double lat;

    public Coords(){

    }

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }
}
