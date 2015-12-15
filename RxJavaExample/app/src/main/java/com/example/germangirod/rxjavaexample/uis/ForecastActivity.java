package com.example.germangirod.rxjavaexample.uis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.uis.fragments.LocationWeatherForecastFragment;

/**
 * Created by germangirod on 10/19/15.
 */
public class ForecastActivity extends BaseActivity {

    private FragmentTransaction fragmentTransaction;
    private String cityId;
    private String windSpeed;
    private String windDegree;
    private String temperature;
    private Float degree;
    private String day;
    private String hours;

    public static void goTo(Context context, String placeId, String windSpeed, String windDegree, String temperature, float degree, String day, String hours) {

        Intent intent = new Intent(context, ForecastActivity.class);
        intent.putExtra("place_id", placeId);
        intent.putExtra("wind_speed", windSpeed);
        intent.putExtra("wind_degree", windDegree);
        intent.putExtra("temperature", temperature);
        intent.putExtra("degree", degree);
        intent.putExtra("day", day);
        intent.putExtra("hours", hours);
        context.startActivity(intent);
    }

    @Override public int getLayoutId() {
        return R.layout.forecast_activity;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getCityId();
        setFragment();
    }

    private void getCityId() {
        Bundle bundle = getIntent().getExtras();
        cityId = bundle.getString("place_id");
        windSpeed = bundle.getString("wind_speed");
        windDegree = bundle.getString("wind_degree");
        temperature = bundle.getString("temperature");
        degree = bundle.getFloat("degree");
        day = bundle.getString("day");
        hours = bundle.getString("hours");
    }

    private void setFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content,
                LocationWeatherForecastFragment.getInstance(cityId, windSpeed, windDegree, temperature, degree, day, hours));
        fragmentTransaction.commit();
    }
}
