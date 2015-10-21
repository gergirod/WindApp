package com.example.germangirod.rxjavaexample.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by germangirod on 5/13/15.
 */
public class Rain {

    @SerializedName("3h") public float value;

    public float getValue() {
        return value;
    }
}
