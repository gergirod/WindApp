package com.example.germangirod.rxjavaexample.uis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import com.example.germangirod.rxjavaexample.uis.fragments.LocationWeatherForecastFragment;
import org.parceler.Parcels;

/**
 * Created by germangirod on 10/19/15.
 */
public class ForecastActivity extends BaseActivity {

    private FragmentTransaction fragmentTransaction;
    WeatherResponse weatherResponse;

    public static void goTo(Context context, WeatherResponse weatherResponse) {

        Intent intent = new Intent(context, ForecastActivity.class);
        intent.putExtra("weather_response", Parcels.wrap(weatherResponse));
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
        weatherResponse= Parcels.unwrap(bundle.getParcelable("weather_response"));
    }

    private void setFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_content,
                LocationWeatherForecastFragment.getInstance(weatherResponse));
        fragmentTransaction.commit();
    }
}
