package com.example.germangirod.rxjavaexample.uis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;

/**
 * Created by germangirod on 5/27/15.
 */
public class WeatherDetailActivity extends ActionBarActivity {

    public static void goTo(Context context, CurrentWeather currentWeather) {
        Intent intent = new Intent(context, WeatherDetailActivity.class);
        context.startActivity(intent);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
