package com.example.germangirod.rxjavaexample.api;

import com.squareup.okhttp.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by germangirod on 5/1/15.
 */
public class RestClient {

    private static Api REST_Client;
    private static String ROOT = "http://api.openweathermap.org/data/2.5";

    static {
        setupRestClinet();
    }

    private RestClient() {

    }

    public static Api get() {
        return REST_Client;
    }

    private static void setupRestClinet() {

        RestAdapter builder = new RestAdapter.Builder().setEndpoint(ROOT).setClient(new OkClient(new OkHttpClient())).build();

        REST_Client = builder.create(Api.class);
    }
}
