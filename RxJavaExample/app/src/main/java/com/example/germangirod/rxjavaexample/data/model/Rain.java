package com.example.germangirod.rxjavaexample.data.model;

import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

/**
 * Created by germangirod on 5/13/15.
 */
@Parcel
public class Rain {

    @SerializedName("3h") public float value;

    public Rain(){

    }

    public float getValue() {
        return value;
    }
}
