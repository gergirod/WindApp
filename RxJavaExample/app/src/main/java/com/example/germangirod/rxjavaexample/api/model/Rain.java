package com.example.germangirod.rxjavaexample.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by germangirod on 5/13/15.
 */
public class Rain {

    @SerializedName("3h") private float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
