package com.example.germangirod.rxjavaexample.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by germangirod on 5/13/15.
 */
public class Rain {

    @SerializedName("3h")
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
