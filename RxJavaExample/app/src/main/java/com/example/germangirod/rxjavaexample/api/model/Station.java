package com.example.germangirod.rxjavaexample.api.model;

/**
 * Created by germangirod on 5/15/15.
 */
public class Station {

    public String name;
    public int type;
    public int status;
    public int id;
    public Coords coords;

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Coords getCoords() {
        return coords;
    }
}
